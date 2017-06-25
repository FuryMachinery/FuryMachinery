package furymachinery.api;

public enum MachineType {
	GENERATOR(0), CAPACITOR(1), CONSUMNER(2);
	int id;

	MachineType(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}
	public void setID(int value){
		id = value;
	}
}
