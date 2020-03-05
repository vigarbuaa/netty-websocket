package net.mengkang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class UnSubscribeHandler extends SimpleChannelInboundHandler<String> {
    public static final UnSubscribeHandler INSTANCE = new UnSubscribeHandler();

    private UnSubscribeHandler() {}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println("UnSubscribeHandler recv: " + msg.toString());
		SessionUtil.unRegSubscribe(msg, ctx.channel());
	}
}
