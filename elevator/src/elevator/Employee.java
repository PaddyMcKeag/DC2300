package elevator;

public class Employee extends Worker {

	//sets up the rand and sets start location
	public Employee(int personId) {
		super(personId);
		this.currentDestination = RAND.nextInt(Building.getNumberOfFloors() - 1) + 1;
		this.call();
	}

	//should be called by run-time every tick
	//cannot change their mind while waiting
	public void changeDestination(double probability) {
		if (!waiting) {
			if (RAND.nextDouble() < probability) {
				this.currentDestination = RAND.nextInt(Building.getNumberOfFloors());
			}
			this.call();
		}
	}
}
