package net.mengkang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.mengkang.request.SubscribeReqPacket;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class SubscribeHandler extends SimpleChannelInboundHandler<SubscribeReqPacket> {
    public static final SubscribeHandler INSTANCE = new SubscribeHandler();

    private SubscribeHandler() {}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, SubscribeReqPacket msg) throws Exception {
		System.out.println("SubscribeHandler recv: " + msg.toString());
		SessionUtil.regSubScribe(msg.getEqp_id(), ctx.channel());
	}
}
