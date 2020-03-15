package net.mengkang.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.mengkang.cmd.type.Command;
import net.mengkang.entity.Packet;

@Data
@NoArgsConstructor
public class Realtime2 extends Packet {
    private String eqp_id;
    private float mileage;

    @Override
    public String getCommand() {
        return Command.realtime2;
    }
    
    @Override
    public String toString () {
      return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
