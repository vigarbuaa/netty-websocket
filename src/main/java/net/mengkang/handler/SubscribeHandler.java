package net.mengkang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.mengkang.request.SubscribeReqPacket;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class SubscribeHandler extends SimpleChannelInboundHandler<SubscribeReqPacket> {
    public static final SubscribeHandler INSTANCE = new SubscribeHandler();
    protected static final InternalLogger logger = InternalLoggerFactory.getInstance(SubscribeHandler.class);

    private SubscribeHandler() {}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, SubscribeReqPacket msg) throws Exception {
		logger.info("SubscribeHandler recv: " + msg.toString());
		SessionUtil.regSubScribe(msg.getEqp_id(), ctx.channel());
	}
}
