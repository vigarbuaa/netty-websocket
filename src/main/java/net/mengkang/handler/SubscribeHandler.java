package net.mengkang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class SubscribeHandler extends SimpleChannelInboundHandler<String> {
    public static final SubscribeHandler INSTANCE = new SubscribeHandler();

    private SubscribeHandler() {}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("SubscribeHandler recv: " + msg.toString());
		SessionUtil.regSubScribe(msg, ctx.channel());
	}
}
