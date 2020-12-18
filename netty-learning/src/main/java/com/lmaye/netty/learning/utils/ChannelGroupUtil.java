package com.lmaye.netty.learning.utils;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * -- Channel Group Util
 *
 * @author lmay.Zhou
 * @date 2020/12/18 18:08
 * @email lmay@lmaye.com
 */
@Slf4j
@Service
public class ChannelGroupUtil {
    public static final String KEY_USER_ID = "userId";
    public static final AttributeKey<String> ATTRIBUTE_KEY_USER_ID = AttributeKey.valueOf(KEY_USER_ID);
    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentHashMap<String, Channel> CONCURRENT_HASH_MAP = new ConcurrentHashMap<>();

    /**
     * 新增 Channel
     *
     * @param channel
     */
    public void addChannel(Channel channel) {
        CHANNEL_GROUP.add(channel);
    }

    /**
     * 关闭 Channel
     *
     * @param channel
     */
    public void removeChannel(Channel channel) {
        CHANNEL_GROUP.remove(channel);
        channel.close();
    }

    /**
     * 关闭 userId的所有 channel
     *
     * @param userId
     */
    public void removeChannel(String userId) {
        if (userId != null) {
            CHANNEL_GROUP.stream()
                    .forEach((Channel channel) -> {
                        String channelUserId = channel.attr(ChannelGroupUtil.ATTRIBUTE_KEY_USER_ID).get();
                        if (userId.equals(channelUserId)) {
                            CHANNEL_GROUP.remove(channel);
                            channel.close();
                        }
                    });
        }
    }


    /**
     * 用户 注册
     * 1、设置 Channel 中 userId属性
     * 2、存放在 gUserChannelMap 中
     *
     * @param channel
     * @param userId
     */
    public void userRegister(Channel channel, String userId) {
        this.setChannelUserAttribute(channel, userId);
        CONCURRENT_HASH_MAP.put(userId, channel);
        log.info(userId + " 已连接");
    }

    /**
     * 用户 注销
     * 1、已注册channel 关闭
     * 2、未注册channel 关闭
     *
     * @param channel
     */
    public void userRemove(Channel channel) {
        String userId = this.getUserIdByChannel(channel);
        if (userId != null) {
            CONCURRENT_HASH_MAP.remove(userId);
            this.removeChannel(userId);
        } else {
            this.removeChannel(channel);
        }
        log.info(userId + " 已退出");
    }


    /**
     * 获取用户 对应的 Channel
     *
     * @param userId
     * @return
     */
    public Channel getChannelByUserId(String userId) {
        return CONCURRENT_HASH_MAP.get(userId);
    }


    /**
     * 给 Channel 设置用户名 userId 的属性
     *
     * @param channel
     * @param userId
     */
    private void setChannelUserAttribute(Channel channel, String userId) {
        channel.attr(ATTRIBUTE_KEY_USER_ID).setIfAbsent(userId);
    }

    /**
     * 获取 channel 的userId
     *
     * @param channel
     * @return
     */
    public String getUserIdByChannel(Channel channel) {
        return channel.attr(ATTRIBUTE_KEY_USER_ID).get();
    }


    public ChannelGroup getChannelGroup() {
        return CHANNEL_GROUP;
    }

    public ConcurrentHashMap<String, Channel> getUserChannelMap() {
        return CONCURRENT_HASH_MAP;
    }
}
