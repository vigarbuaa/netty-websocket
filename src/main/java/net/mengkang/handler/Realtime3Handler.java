package net.mengkang.handler;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.mengkang.request.Realtime3;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class Realtime3Handler extends SimpleChannelInboundHandler<Realtime3> {
	public static final Realtime3Handler INSTANCE = new Realtime3Handler();
    protected static final InternalLogger logger = InternalLoggerFactory.getInstance(Realtime3Handler.class);

	private Realtime3Handler() {
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Realtime3 msg) throws Exception {
		logger.info("RealtimeHandler3 recv: " + msg.toString());
		Map<String, List<Channel>> route = SessionUtil.getRegchannelmap();
		logger.info("msg3 received: " + msg.toString());
		logger.info("msg3 json received: " + JSON.toJSONString(msg));
	}
}
