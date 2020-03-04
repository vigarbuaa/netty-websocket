package net.mengkang.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class Response {
    private int error_code; // 成功时 0 ,如果大于 0 则表示则显示error_msg
    private String error_msg;
    private Map<String,Object> data;

    public Response() {
        data = new HashMap<String,Object>();
    }

    public Response(int error_code, String error_msg) {
        this.error_code = error_code;
        this.error_msg = error_msg;
    }
}