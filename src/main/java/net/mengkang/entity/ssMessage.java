package net.mengkang.entity;

import lombok.Data;

@Data
public class ssMessage extends BaseMessage {
	//实时位置
	private double longitude;  
	private double latitude;

	//目标位置
	private double target_longitude;
	private double target_latitude;
	
	private double direction;
	private double speed;
	
	//电量
	private double soc;
	//承载情况
	private String load;
	
	private double mileage;
	
	//网络交换状态
	private String net_status;
	
	//健康状态
	private String health_status;
	
	//急停状态
	private String crashstop_status;
}
