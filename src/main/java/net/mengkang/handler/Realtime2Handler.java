package net.mengkang.handler;

import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.mengkang.request.Realtime2;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class Realtime2Handler extends SimpleChannelInboundHandler<Realtime2> {
	public static final Realtime2Handler INSTANCE = new Realtime2Handler();

	private Realtime2Handler() {
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Realtime2 msg) throws Exception {
		System.out.println("RealtimeHandler2 recv: " + msg.toString());
		Map<String, List<Channel>> route = SessionUtil.getRegchannelmap();
		System.out.println("msg2 received: " + msg.toString());
		System.out.println("msg2 json received: " + JSON.toJSONString(msg));
	}
}
