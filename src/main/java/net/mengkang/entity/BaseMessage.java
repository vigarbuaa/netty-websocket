package net.mengkang.entity;

import lombok.Data;

@Data
public class BaseMessage {
	//设备id
	String  equipement_id;
	
	//消息类型  各消息体把 自己所属子系统名列上，例如  zc/cs/zz/
	String  msg_type; //根据这个，进入不同的handler
}
