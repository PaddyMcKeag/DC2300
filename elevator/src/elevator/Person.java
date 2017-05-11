package elevator;

import java.util.Random;

public abstract class Person {
	
	//each person has an ID. can be used to differentiate between them but 
	//probably won't be used
	protected int personId;
	//where the worker wants to go changed by method on run-time call
	protected int currentDestination;
	//location of the person
	protected int currentFloor;
	//size is mainly for maintenance crews
	protected int size;
	//priority is used for clients
	protected boolean priority;		
	//determines starting place, destination, and 
	protected Random rand;	
	
	
	//constructs person with ID and destination
	public Person(int personId, long seed) {
		this.personId = personId;
		currentFloor = 0;
		size = 1;
		priority = false;
		rand = new Random(seed);
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
	
	public int getSize() {
		return size;
	}

	public boolean getPriority() {
		return priority;
	}
	
	public void leave() {
		currentDestination = 0;
		call();
	}	
	
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
}
