package net.mengkang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.mengkang.request.UnSubscribeReqPacket;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class UnSubscribeHandler extends SimpleChannelInboundHandler<UnSubscribeReqPacket> {
    public static final UnSubscribeHandler INSTANCE = new UnSubscribeHandler();
    protected static final InternalLogger logger = InternalLoggerFactory.getInstance(SubscribeHandler.class);

    private UnSubscribeHandler() {}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, UnSubscribeReqPacket msg) throws Exception {
		logger.info("UnSubscribeHandler recv: " + msg.toString());
		SessionUtil.unRegSubscribe(msg.getEqp_id(), ctx.channel());
	}
}
