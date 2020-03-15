package net.mengkang.cmd.type;

/**
 * @author vigarbuaa
 * @date 2020-03-05
 * 目前有11种指令,各子系统/设备 上报的字段不同。
 */
public interface Command {
	
	String subscribe = "subscribe"; //订阅,订阅任务、订阅设备状态
	
	String un_subscribe = "un_subscribe"; //取消订阅

	// 任务启动(多种类型的子任务，参数不同)

	// 任务暂停(任务id: stop)
	
	// 设备报警(设备id: 故障原因： 故障码： 上报时间)

	// 任务报警(任务id: 故障原因： 故障码： 上报时间)

	// 状态上报(各类子系统上传的数据)
	String realtime1 = "rt1"; // 子系统1实时上报数据
	String realtime2 = "rt2"; // 子系统2实时上报数据
	String realtime3 = "rt3"; // 子系统3实时上报数据
	String realtime4 = "rt4"; // 子系统4实时上报数据
	String realtime5 = "rt5"; // 子系统5实时上报数据
	String realtime6 = "rt6"; // 子系统6实时上报数据
}
