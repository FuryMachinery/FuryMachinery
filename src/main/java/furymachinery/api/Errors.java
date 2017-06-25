package furymachinery.api;

public class Errors {
	public static final int NULLSTACKERROR = 0;

	public static void send(int id, Class erroredClass) {
		switch (id) {
		case 0:
			System.err.println("An error occured while trying to add a null stack at: " + erroredClass.getName());
			break;
		case 1:
			System.err.println("An error occured while trying to make tiers useless at: " + erroredClass.getName());
			break;
		}
	}
}
