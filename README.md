# netty-websocket

## launch

At first, java 8 should be supported in your server.

Run `net.mengkang.WebSocketServer` in IDE, Then you can open the `client.html` in your browser for testing.

## benchmark

you can use the script `banchmark.py` in command line.

## demo

https://mengkang.net/demo/websocket/2.html

## 待加入特性
   - 检查，报文只接收文本，且必须为json
   - 根据报文的type字段，再次区别不同的handler
   - 利用netty中的schedule，定时向各分系统推送
   - 做一个输送系统模拟器，后续做自检用