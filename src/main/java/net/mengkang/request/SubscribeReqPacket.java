package net.mengkang.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.mengkang.cmd.type.Command;
import net.mengkang.entity.Packet;

@Data
@NoArgsConstructor
public class SubscribeReqPacket extends Packet {
    private String equipment_id;

    public SubscribeReqPacket(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    @Override
    public String getCommand() {
        return Command.subscribe;
    }
}
