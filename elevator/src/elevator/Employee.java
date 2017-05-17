package elevator;

public class Employee extends Worker {

	//sets up the rand and sets start location
	public Employee(int personId, long seed) {
		super(personId, seed);
		this.currentDestination = rand.nextInt(Building.getNumberOfFloors() - 1) + 1;
		this.call();
	}

	//should be called by run-time every tick
	//cannot change their mind while waiting
	public void changeDestination(double probability) {
		if (!waiting) {
			if (rand.nextDouble() < probability) {
				this.currentDestination = rand.nextInt(Building.getNumberOfFloors());
			}
			this.call();
		}
	}
}
