package elevator;

public class Employee extends Worker {

	public Employee(int personId) {
		super(personId);
		this.currentDestination = RAND.nextInt(Building.getNumberOfFloors());
		this.call();
	}

	public void changeDestination(double probability) {
		if (RAND.nextDouble() < probability) {
			this.currentDestination = RAND.nextInt(Building.getNumberOfFloors());
		}
		this.call();
	}
}
