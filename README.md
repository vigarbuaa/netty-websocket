# netty-websocket

## launch

At first, java 8 should be supported in your server.

Run `net.mengkang.WebSocketServer` in IDE, Then you can open the `client.html` in your browser for testing.

## benchmark

you can use the script `banchmark.py` in command line.

## test
	{"command":"subscribe","eqp_id":"xxxx01"}
	{"command":"subscribe","eqp_id":"xxxx02"}
	{"command":"un_subscribe","eqp_id":"123456"}
	{"command":"rt1","eqp_id":"xxxx01", "speed" : 101, "soc":0.3}
	{"command":"rt2","eqp_id":"xxxx01",  "mileage":2000.3}
	{"command":"rt3","eqp_id":"xxxx01",  "rotate":2.3}
	
	模拟订阅,页面显示订阅的信息。试着用websocket_client写一下。
	
## 待加入特性
   - 前端，发订阅，发取消订阅，连续发模拟消息。
   - 调整日志，不用system.out  [done]
   - realtime msg：只解json。 [done]
   - 模拟连续发送。 [done]
   
       -      要求至少有command/type/eqp_id三个字段。 [done]
                                          对json字段的预处理，要写在配置文件中。{上限，下限，单位，描述等} [先不做]
   - 检查，http只接收upgrade请求  [done]
   - 检查，websocket报文只接收文本，且必须为json [done]
   - 先登入，才允许订阅
   - 区分信息 ：　
            - 命令类: 订阅、取消订阅   done
            - 回令类: 订阅回令(订阅成功、失败、失败原因{设备不在线、订阅失败等 })、取消订阅(成、败)
            - 实时推送类，订阅成功后，实时推送 (按要求进行转发) done
            - 查询类：   查询设备列表/查询(走http) 
                                                             是否可以将springboot与netty结合
   			
   			- 内部要维护一个在线设备组, 在SessionUtil中维护的map
   				
   - 对外提供2类接口
      - http://xxxx:8888/api/
      	  - 当前在线的分系统及设备列表
      	  - 分系统及设备的详细信息(上线时间)
      - ws://xxxx:8877/ws/

      IMHandler
   - 封session，将session信息绑在channel上 (ongoing)
     - 针对eqp_id的判重功能
     
   - 做一个针对json的动态解析功能。
   - 后台存储session与channel关系，根据subscribe/unsubscribe调整session与channel关系，做转发功能。
   - 这其中要加入写redis操作。(后续必要时将其摘出来)
   - 增加console，可以从后台查看目前在线用户数量(同时提供http接口)   
   - 要求各子系统上线后一直向中控机发实时状态（须具备重连机制）
   - 根据报文的type字段，再次区别不同的handler
   - 利用netty中的schedule，定时向各分系统推送
   		- 先不加auth接口(此接口需要考虑向各子系统发key/secret)
   - 允许子系统向本系统实时推送(本系统记日志、维护缓存、接受订阅、取消订阅)
   - 做一个输送系统模拟器，后续做自检 用
   - 如果想订阅一个任务的实时信息？ 某一个任务相关的agv小车的信息? 这个乍整？ 