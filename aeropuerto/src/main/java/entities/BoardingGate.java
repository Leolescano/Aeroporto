package entities;

import lombok.Getter;
import lombok.Setter;


public class BoardingGate {

	@Getter @Setter
	private int gateNumber;

	@Getter @Setter
	private boolean status;

	@Getter @Setter
	private Aircraft aircraft;

	public BoardingGate(int gateNumber) {
		this.gateNumber = gateNumber;
	}

	@Override
	public String toString() {
		String status = this.status ? "In use" : "Enabled";
		return "Gate Number " + this.gateNumber  + " ==> Status: " + status;
	}
}
