package net.mengkang;

import static io.netty.handler.codec.http.HttpHeaderNames.HOST;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderUtil;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    protected static final InternalLogger logger = InternalLoggerFactory.getInstance(WebSocketServerHandshaker.class);

	// websocket 服务的 uri
	private static final String WEBSOCKET_PATH = "/websocket";

	// 一个 ChannelGroup 代表一个直播频道
	private static Map<Integer, ChannelGroup> channelGroupMap = new ConcurrentHashMap<>();

	private WebSocketServerHandshaker handshaker;

	@Override
	public void messageReceived(ChannelHandlerContext ctx, Object msg) {
		logger.info("messageReceived from " + ctx.channel().remoteAddress() + " connect to " + ctx.channel().localAddress());
		if (msg instanceof FullHttpRequest) {
			logger.info("get http request");
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) {
			logger.info("get websocket request");
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
	
	// http中只处理 websocket upgrade请求，其它一概不处理
	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
		// Handle a bad request.
		if (!req.decoderResult().isSuccess()) {
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
			return;
		}

		if ((null==req.headers().get("Upgrade")) ||!req.headers().get("Upgrade").equals("websocket")){
			logger.info("not websocket upgrade req, reject:w");
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
			return;
		}

		// Handshake
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req),
				null, true);
		handshaker = wsFactory.newHandshaker(req);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			ChannelFuture channelFuture = handshaker.handshake(ctx.channel(), req);
			// 握手成功之后,业务逻辑
			if (channelFuture.isSuccess()) {
					logger.info(ctx.channel() + " handshake OK, websocket connect OK");
				}
//			}
		}
	}

/*	private void broadcast(ChannelHandlerContext ctx, WebSocketFrame frame) {
		if (client.getId() == 0) {
			Response response = new Response(1001, "没登录不能聊天哦");
			String msg = new JSONObject(response).toString();
			ctx.channel().write(new TextWebSocketFrame(msg));
			return;
		}

		String request = ((TextWebSocketFrame) frame).text();
		System.out.println(" 收到 " + ctx.channel() + request);

		Response response = MessageService.sendMessage(client, request);
		// String msg = new JSONObject(response).toString();
	}
*/
	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		//只接收文本
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(
					String.format("%s frame types not supported", frame.getClass().getName()));
		}
		
		logger.info("msg recv: " + ((TextWebSocketFrame)frame).text());
//		broadcast(ctx, frame);
	}

	private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
		if (res.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
			HttpHeaderUtil.setContentLength(res, res.content().readableBytes());
		}

		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!HttpHeaderUtil.isKeepAlive(req) || res.status().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		System.out.println("收到" + incoming.remoteAddress() + " 握手请求");
	}

//	@Override
//	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//		if (client != null && channelGroupMap.containsKey(client.getRoomId())) {
//			channelGroupMap.get(client.getRoomId()).remove(ctx.channel());
//		}
//	}

	private static String getWebSocketLocation(FullHttpRequest req) {
		String location = req.headers().get(HOST) + WEBSOCKET_PATH;
		return "ws://" + location;
	}
}
