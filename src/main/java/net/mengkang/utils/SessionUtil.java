package net.mengkang.utils;

import io.netty.channel.Channel;
//import io.netty.channel.group.ChannelGroup;
import net.mengkang.session.Attributes;
import net.mengkang.session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SessionUtil {
	//  eqp->channel map
    private static final Map<String, Channel> eqpIdChannelMap = new ConcurrentHashMap<>();

    // eqp_id 与 channel 的订阅关系，确定实时消息是否转发
    private static final Map<String, List<Channel>> regChannelMap = new ConcurrentHashMap<>();


    public static Map<String, List<Channel>> getRegchannelmap() {
		return regChannelMap;
	}

	public static void bindSession(Session session, Channel channel) {
        eqpIdChannelMap.put(session.getEqp_id(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }
    
    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            eqpIdChannelMap.remove(session.getEqp_id());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session + " 退出登录!");
        }
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return eqpIdChannelMap.get(userId);
    }
    
    public static void regSubScribe(String eqpId,Channel channel){
    	if(regChannelMap.containsKey(eqpId)){
    		List<Channel> channelList = regChannelMap.get(eqpId);
    		List<Channel> list2=channelList.stream().distinct().collect(Collectors.<Channel> toList());
    		regChannelMap.put(eqpId, list2);
    	}else
    	{
    		List<Channel> channelList = new ArrayList<Channel>();
    		channelList.add(channel);
    		regChannelMap.put(eqpId, channelList);
    	}
    }
    
    public static void unRegSubscribe(String eqpId,final Channel channel){
    	if (null == regChannelMap)
    		return ;
    	
    	if(regChannelMap.containsKey(eqpId)){
    		List<Channel> channelList = regChannelMap.get(eqpId);
    		channelList.removeIf( elem -> elem.equals(channel));
    		List<Channel> list2=channelList.stream().distinct().collect(Collectors.<Channel> toList());
    		regChannelMap.put(eqpId, list2);
    	}
    }
}
