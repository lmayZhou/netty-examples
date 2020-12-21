package com.lmaye.netty.learning.handler;

import com.alibaba.fastjson.JSON;
import com.lmaye.netty.learning.constants.ModuleEnum;
import com.lmaye.netty.learning.constants.WebSocketEnum;
import com.lmaye.netty.learning.dto.UserRegisterDataDTO;
import com.lmaye.netty.learning.dto.WebSocketDTO;
import com.lmaye.netty.learning.utils.ChannelGroupUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * -- Channel Handler Base
 *
 * @author lmay.Zhou
 * @date 2020/12/18 18:14
 * @email lmay@lmaye.com
 */
public class ChannelHandlerBase extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Autowired
    private ChannelGroupUtil channelGroupUtil;

    @Autowired
    private MessageSender messageSender;

    /**
     * 连接开启
     *
     * @param channelHandlerContext
     */
    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
        channelGroupUtil.addChannel(channelHandlerContext.channel());
    }

    /**
     * 接受消息
     * 由子类实现
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) {
    }

    /**
     * 连接关闭
     *
     * @param channelHandlerContext
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) {
        channelGroupUtil.userRemove(channelHandlerContext.channel());
    }

    /**
     * 异常处理
     *
     * @param channelHandlerContext
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        channelGroupUtil.userRemove(channelHandlerContext.channel());
    }

    /**
     * 心跳检查处理 HEART_CHECK
     *
     * @param channel
     */
    protected void heartCheckHandler(Channel channel) {
        messageSender.sendMessage(channel, WebSocketDTO.data(ModuleEnum.HEART_CHECK, "回复心跳检查"));
    }

    /**
     * 注册用户 USER_REGISTER
     *
     * @param channel
     * @param request
     * @param webSocketEnum
     */
    protected void registerHandler(Channel channel, WebSocketDTO request, WebSocketEnum webSocketEnum) {
        UserRegisterDataDTO userRegisterDataDTO = JSON.parseObject(JSON.toJSONString(request.getData()), UserRegisterDataDTO.class);
        String userId = userRegisterDataDTO.getUserName() + "_" + webSocketEnum.name() + "_" + userRegisterDataDTO.getDevice().name();
        //关闭 userId 的其他channel
        channelGroupUtil.removeChannel(userId);
        //将该 channel绑定userId，存入UserChannelMap
        channelGroupUtil.userRegister(channel, userId);
        messageSender.sendMessage(channel,
                WebSocketDTO.data(ModuleEnum.USER_REGISTER, userId + "用户已注册"));
    }
}
