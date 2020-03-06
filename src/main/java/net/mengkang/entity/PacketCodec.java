package net.mengkang.entity;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.mengkang.cmd.type.Command;
import net.mengkang.request.SubscribeReqPacket;
import net.mengkang.request.UnSubscribeReqPacket;
import net.mengkang.serialize.impl.JSONSerializer;
import net.nengkang.serialize.Serializer;

public class PacketCodec {
    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodec INSTANCE = new PacketCodec();

    private final Map<String, Class<? extends Packet>> packetTypeMap;

    private PacketCodec() {
        packetTypeMap = new HashMap<>();
//        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.un_subscribe, UnSubscribeReqPacket.class);
        packetTypeMap.put(Command.subscribe,    SubscribeReqPacket.class);
    }

/*    public void encode(ByteBuf byteBuf, Packet packet) {
        // 1. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 2. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
    }
*/

    public Packet decode(String raw) {
    	JSONObject json_obj = JSON.parseObject(raw);
    	String command = json_obj.getString("command");
    	
        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = new  JSONSerializer();

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, raw);
        }
        return null;
    }

    private Class<? extends Packet> getRequestType(String command) {
        return packetTypeMap.get(command);
    }
}
