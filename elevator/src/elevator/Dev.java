package elevator;
import java.util.Random;

public class Dev {

	private int personId;
	//determines starting place, destination, and 
	private static final Random RAND = new Random();
	//changed by method on run-time call
	private int currentDestination;
	//moved to Person class for usability
	private int currentFloor;
	
	//sets up the rand and determines starting point
	public Dev(int id) {
		personId = id;
		currentDestination = RAND.nextInt(Building.getNumberOfFloors() / 2) + Building.getNumberOfFloors(); 
		this.call();
	}
	
	//determines whether a call is necessary - again, every tick
	public void call() {
		if (currentDestination != currentFloor) {
			Lift.addDestination(currentFloor);
		}
	}
	
	//should be called by the run-time every tick
	public void changeDestination(double probability) {
		if (RAND.nextInt(1) < probability) {
			currentDestination = RAND.nextInt(Building.getNumberOfFloors() / 2) + Building.getNumberOfFloors();  
		}
		
		this.call();
	}

	public int getCurrentDestination() {
		return currentDestination;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public int getPersonId() {
		return personId;
	}

}
