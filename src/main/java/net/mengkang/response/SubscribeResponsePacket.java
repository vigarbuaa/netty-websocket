package net.mengkang.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.mengkang.cmd.type.Command;
import net.mengkang.entity.Packet;

@Data
@NoArgsConstructor
public class SubscribeResponsePacket extends Packet {
	   private boolean success;

	    private String reason;

	    @Override
	    public String getCommand() {
	        return Command.subscribe;
	    }
	    
	    @Override
	    public String toString () {
	      return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	    }
}