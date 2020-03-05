package net.mengkang.session;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Session {
	private String eqp_id;

	@Override
	public String toString() {
		return eqp_id ;
	}
}
