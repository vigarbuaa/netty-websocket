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
import net.mengkang.request.Realtime1;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class Realtime1Handler extends SimpleChannelInboundHandler<Realtime1> {
	public static final Realtime1Handler INSTANCE = new Realtime1Handler();
    protected static final InternalLogger logger = InternalLoggerFactory.getInstance(Realtime1Handler.class);

	private Realtime1Handler() {
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Realtime1 msg) throws Exception {
		logger.info("RealtimeHandler recv: " + msg.toString());
		Map<String, List<Channel>> route = SessionUtil.getRegchannelmap();
		logger.info("msg received: " + msg.toString());
		logger.info("msg json received: " + JSON.toJSONString(msg));
	}
}