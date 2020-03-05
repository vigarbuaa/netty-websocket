package net.mengkang.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.mengkang.cmd.type.Command;
import net.mengkang.entity.Packet;

@Data
@NoArgsConstructor
public class UnSubscribeResponsePacket extends Packet {
	   private boolean success;

	    private String reason;

	    @Override
	    public String getCommand() {
	        return Command.un_subscribe;
	    }
}