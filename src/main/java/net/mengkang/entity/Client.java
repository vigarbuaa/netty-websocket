package net.mengkang.entity;

import lombok.Data;

/**
 * Created by zhoumengkang on 16/7/2.
 */
@Data
public class Client {
    private long id;
    private int roomId;

    public Client() {
        id = 0L;
        roomId = 0;
    }
}
