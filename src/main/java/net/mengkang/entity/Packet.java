package net.mengkang.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author vigarbuaa
 * @date 18-12-3 下午3:31
 * 基础类包
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;


    @JSONField(serialize = false)
    public abstract String getCommand();
}
