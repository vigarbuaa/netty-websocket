package net.mengkang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.HashMap;
import java.util.Map;
import net.mengkang.cmd.type.Command;
import net.mengkang.entity.Packet;

@ChannelHandler.Sharable
public class WsMsgHandler extends SimpleChannelInboundHandler<Packet> {
    public static final WsMsgHandler INSTANCE = new WsMsgHandler();

//    private Map<String, SimpleChannelInboundHandler<? extends Packet>> handlerMap;
    private Map<String, SimpleChannelInboundHandler<String>> handlerMap;

    private WsMsgHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(Command.un_subscribe, SubscribeHandler.INSTANCE);
        handlerMap.put(Command.subscribe,    UnSubscribeHandler.INSTANCE);
    }

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Packet msg) throws Exception {
		System.out.println("WsMsgHandler recv: " + msg.toString());
		handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
	}
}