package net.mengkang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.mengkang.cmd.type.Command;
import net.mengkang.entity.Packet;

@ChannelHandler.Sharable
public class WsMsgHandler extends SimpleChannelInboundHandler<Packet> {
    public static final WsMsgHandler INSTANCE = new WsMsgHandler();

    private Map<String, SimpleChannelInboundHandler> handlerMap  = new ConcurrentHashMap<>();

    private WsMsgHandler() {
        handlerMap.put(Command.un_subscribe, UnSubscribeHandler.INSTANCE);
        handlerMap.put(Command.subscribe,    SubscribeHandler.INSTANCE);
        handlerMap.put(Command.realtime1,     Realtime1Handler.INSTANCE);
        handlerMap.put(Command.realtime2,     Realtime2Handler.INSTANCE);
        handlerMap.put(Command.realtime3,     Realtime3Handler.INSTANCE);
    }

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Packet msg) throws Exception {
		System.out.println("WsMsgHandler recv: " + msg.toString());
		System.out.println("WsMsgHandler recv: " + msg.getCommand());
		
		//加入反序列化逻辑
		handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
	}
}