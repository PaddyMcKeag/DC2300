package elevator;
import java.util.Random;

public class Employee extends Worker {

	//sets up the rand and sets start location
	public Employee(int personId, Random rand) {
		super(personId, rand);
		this.currentDestination = rand.nextInt(Building.getNumberOfFloors() - 1);
		if (this.currentDestination != this.currentFloor) {
			this.call();
		}
	}

	//should be called by run-time every tick
	//cannot change their mind while waiting
	public int determineDestination(double probability) {
		return rand.nextInt(Building.getNumberOfFloors() -1);
	}
}
