package elevator;

public abstract class Person {
	
	//each person has an ID. can be used to differentiate between them but 
	//probably won't be used
	protected int personId;
	//where the worker wants to go changed by method on run-time call
	protected int currentDestination;
	//location of the person
	protected int currentFloor;
	
	//constructs person with ID and destination
	public Person(int personId) {
		this.personId = personId;
		currentFloor = 0;
	}
	
	//calls the lift if the destination is wrong
	protected void call() {
		if (currentDestination != currentFloor) {
			Lift.addDestination(this);
		}
	}
	
	public int getCurrentDestination() {
		return currentDestination;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}
	
	//returns the ID of each person which is set as they are created
	public int getPersonId() {
		return personId;
	}
	
}
