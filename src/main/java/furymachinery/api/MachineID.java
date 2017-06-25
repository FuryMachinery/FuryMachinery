package furymachinery.api;

public enum MachineID {
	CRUSHER_B(0), CRUSHER_A(1), CRUSHER_S(2), CRUSHER_U(3), GENERATORSB(4);
	public int ID;

	MachineID(int handlerID) {
		ID = handlerID;
	}

	public int getID() {
		return ID;
	}
}
