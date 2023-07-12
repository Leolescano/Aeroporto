package entities;

import lombok.Getter;
import lombok.Setter;


public class BoardingGate {
	@Getter @Setter
	private int gateNumber;
	@Getter @Setter
	private boolean enabled;
	@Getter @Setter
	private Plane plane;

	public BoardingGate(int gateNumber) {
		this.gateNumber = gateNumber;
	}

	@Override
	public String toString() {
		String enabled = this.enabled ? "In use" : "Released";
		return "Gate Number " + this.gateNumber  + " Enabled: " + enabled;
	}
}
