package net.mengkang.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.mengkang.cmd.type.Command;
import net.mengkang.entity.Packet;

@Data
@NoArgsConstructor
public class UnSubscribeReqPacket extends Packet {
    private String equipment_id;

    public UnSubscribeReqPacket(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    @Override
    public String getCommand() {
        return Command.un_subscribe;
    }
    @Override
    public String toString () {
      return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }}
