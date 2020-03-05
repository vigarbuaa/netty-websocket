package net.mengkang.service;

import net.mengkang.dto.Response;

public class MessageService {

    public static Response sendMessage( String message) {
        Response res = new Response();
//      res.getData().put("id", client.getId());
        res.getData().put("message", message);
        res.getData().put("ts", System.currentTimeMillis());// 返回毫秒数
        return res;
    }
}