package com.lmaye.netty.learning.handler;

import com.alibaba.fastjson.JSON;
import com.lmaye.netty.learning.constants.WebSocketEnum;
import com.lmaye.netty.learning.dto.WebSocketDTO;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

import static com.lmaye.netty.learning.constants.ModuleEnum.HEART_CHECK;
import static com.lmaye.netty.learning.constants.ModuleEnum.USER_REGISTER;

/**
 * -- Data Channel Handler
 *
 * @author lmay.Zhou
 * @date 2020/12/18 18:18
 * @email lmay@lmaye.com
 */
@Component
@ChannelHandler.Sharable
public class DataChannelHandler extends ChannelHandlerBase {
    @Override
    public void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame msg) {
        Channel channel = channelHandlerContext.channel();
        WebSocketDTO request = JSON.parseObject(msg.text(), WebSocketDTO.class);
        switch (request.getModule()) {
            case USER_REGISTER:
                super.registerHandler(channel, request, WebSocketEnum.DATA);
                break;
            case HEART_CHECK:
                super.heartCheckHandler(channel);
                break;
            default:
                break;
        }
    }
}
