package furymachinery.api;

public enum EnumTransmission {
	ENERGY(EnumCable.ENERGY), GAS(EnumCable.GAS), FLUID(EnumCable.FLUID), HEAT(EnumCable.HEAT), ITEM(EnumCable.ITEM);
	EnumCable cable;
	boolean doLose;

	EnumTransmission(EnumCable cable) {
		this.cable = cable;
	}

	public EnumCable getCable() {
		return cable;
	}

	public enum EnumCable {
		ENERGY, GAS, FLUID, HEAT, ITEM;
	}

	public enum EnumType {
		IDLE, SEND, RECEIVE;
	}
}
