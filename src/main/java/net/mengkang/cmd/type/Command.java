package net.mengkang.cmd.type;

/**
 * @author vigarbuaa
 * @date 2020-03-05
 * 目前有三种指令
 */
public interface Command {
	String subscribe = "subscribe";
	
	String un_subscribe = "un_subscribe";

	String realtime = "realtime"; // 实时上报数据
}
