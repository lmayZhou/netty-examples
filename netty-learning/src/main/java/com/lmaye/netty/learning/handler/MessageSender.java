package com.lmaye.netty.learning.handler;

import com.alibaba.fastjson.JSON;
import com.lmaye.netty.learning.constants.DeviceEnum;
import com.lmaye.netty.learning.constants.WebSocketEnum;
import com.lmaye.netty.learning.dto.WebSocketDTO;
import com.lmaye.netty.learning.utils.ChannelGroupUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * -- Message Sender
 *
 * @author lmay.Zhou
 * @date 2020/12/18 18:21
 * @email lmay@lmaye.com
 */
@Service
public class MessageSender {
    @Autowired
    private ChannelGroupUtil channelGroupUtil;

    /**
     * 发送给 具体用户
     *
     * @param userId
     * @param webSocketDTO
     */
    public void sendMessage(String userId, WebSocketDTO webSocketDTO) {
        Channel channel = channelGroupUtil.getChannelByUserId(userId);
        this.sendMessage(channel, webSocketDTO);
    }

    public void sendMessage(String username, WebSocketEnum webSocketEnum, DeviceEnum deviceEnum, WebSocketDTO webSocketDTO) {
        String userId = username + "_" + webSocketEnum.name() + "_" + deviceEnum.name();
        this.sendMessage(userId, webSocketDTO);
    }

    public void sendMessage(Channel channel, WebSocketDTO webSocketDTO) {
        if (channel != null) {
            channel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(webSocketDTO)));
        }
    }

    /**
     * 发送给 该用户下的所有设备
     *
     * @param username
     * @param webSocketEnum
     * @param webSocketDTO
     */
    public void sendMessageToAllDevices(String username, WebSocketEnum webSocketEnum, WebSocketDTO webSocketDTO) {
        for (DeviceEnum deviceEnum : DeviceEnum.values()) {
            String userId = username + "_" + webSocketEnum.name() + "_" + deviceEnum.name();
            this.sendMessage(userId, webSocketDTO);
        }
    }


    /**
     * 转换 userId
     *
     * @param userId
     * @param webSocketEnum
     * @param deviceEnum
     * @return
     */
    public String convertUserId(String userId, WebSocketEnum webSocketEnum, DeviceEnum deviceEnum) {
        if (userId != null) {
            if (webSocketEnum != null) {
                for (WebSocketEnum we : WebSocketEnum.values()) {
                    if (!we.equals(webSocketEnum)) {
                        userId = userId.replace("_" + we.name(), "_" + webSocketEnum.name());
                    }
                }
            }

            if (deviceEnum != null) {
                for (DeviceEnum de : DeviceEnum.values()) {
                    if (!de.equals(deviceEnum)) {
                        userId = userId.replace("_" + de.name(), "_" + deviceEnum.name());
                    }
                }
            }

        }
        return userId;
    }

    public String convertUserId(Channel channel, WebSocketEnum webSocketEnum, DeviceEnum deviceEnum) {

        String userId = channelGroupUtil.getUserIdByChannel(channel);

        return convertUserId(userId, webSocketEnum, deviceEnum);
    }
}
