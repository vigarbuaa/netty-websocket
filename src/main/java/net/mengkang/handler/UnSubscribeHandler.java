package net.mengkang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.mengkang.request.UnSubscribeReqPacket;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class UnSubscribeHandler extends SimpleChannelInboundHandler<UnSubscribeReqPacket> {
    public static final UnSubscribeHandler INSTANCE = new UnSubscribeHandler();

    private UnSubscribeHandler() {}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, UnSubscribeReqPacket msg) throws Exception {
		System.out.println("UnSubscribeHandler recv: " + msg.toString());
		SessionUtil.unRegSubscribe(msg.getEqp_id(), ctx.channel());
	}
}
