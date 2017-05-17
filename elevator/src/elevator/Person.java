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
	//times how long they've been waiting until they leave
	protected int waitTimer;
	
	
	//constructs person with ID and destination
	public Person(int personId) {
		this.personId = personId;
		currentFloor = 0;
		size = 1;
		priority = false;
		waitTimer = 0;
		rand = new Random();
	}
	
	//calls the lift if the destination is wrong
	protected void call() {
		if (currentDestination != currentFloor) {
			Lift.addDestination(this);
			System.out.println(this.personId + " has called the lift to travel from " + 
					this.currentFloor + " to " + this.currentDestination);
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
		waitTimer = 0;
	}
	
	public int halfFloorFormula() {
		return Building.getNumberOfFloors() / 2 + (Building.getNumberOfFloors() % 2 == 0 ? 0 : 1);
	}
	
	public void countWaitTimer() {
		waitTimer++;
	}
	
	public int getWaitTimer() {
		return waitTimer;
	}
	
}
