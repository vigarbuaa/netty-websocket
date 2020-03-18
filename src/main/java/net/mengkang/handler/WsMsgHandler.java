package net.mengkang.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;

import net.mengkang.cmd.type.Command;
import net.mengkang.entity.Packet;
import net.mengkang.utils.SessionUtil;

@ChannelHandler.Sharable
public class WsMsgHandler extends SimpleChannelInboundHandler<Packet> {
    public static final WsMsgHandler INSTANCE = new WsMsgHandler();
    protected static final InternalLogger logger = InternalLoggerFactory.getInstance(SubscribeHandler.class);

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
		logger.info("WsMsgHandler recv: " + msg.toString());
		logger.info("WsMsgHandler recv: " + msg.getCommand());
		
		// 处理订阅
		Map<String, List<Channel>> regChannelMap = new ConcurrentHashMap<>();
		regChannelMap = SessionUtil.getRegchannelmap();
		if (null== regChannelMap){
			//pass
		}else{
			for (String elem : regChannelMap.keySet()){
				logger.info("================debug info======================");
				logger.info(elem);
				List<Channel> channel_group = regChannelMap.get(elem);
				
				for( Channel channel_elem : channel_group){
					System.out.println(channel_elem.toString());
					if(channel_elem.isActive()){
						channel_elem.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(msg))).addListener(new ChannelFutureListener() {
							public void operationComplete(ChannelFuture future) throws Exception {
								if (future.isDone() && future.isSuccess()) {
									logger.info("send Success succeed");
								} else {
									logger.info("send Failed!! Failed!!" + future.cause().getMessage());
								}
							}});
						
						logger.info("channel msg send OK");
					}else{
						logger.info("channel is not active ! please check!");
					}
				}
			}
		}
		
		//加入反序列化逻辑
		handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
	}
}