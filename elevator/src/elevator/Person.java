/**
 * The Person class is an abstract class that acts as the base class for all the other
 * types of person in the simulation. It defines the basic behaviour for a generic person,
 * which is then expanded on or overridden in the child classes.
 * 
 * @author Paddy McKeag
 * @version 1.0
 * @since 2017-05-19
 */
package elevator;

import java.util.Random;

public abstract class Person {
	
	//personId is used to differentiate between people in the GUI
	protected int personId;
	//the destination of the person	
	protected int currentDestination;
	//location of the person
	protected int currentFloor;
	//size is mainly for maintenance crews - all other people have size 1
	protected int size;
	//priority is used for clients - all other people have no priority
	protected boolean priority;		
	//determines starting place, destination (for all but crew), and whether the 
	//person moves (for employee) 
	protected Random rand;	
	//times how long they've been waiting - for average wait time and client complaints
	protected int waitTimer;
	
	
	/**
	 * The constructor for the class person. As an abstract class, this is only
	 * called as superclass constructor.
	 * 
	 * @param personId This is used to display people in the GUI.
	 * @param rand To ensure the same seed of Random is used for everything,
	 * the Random object is passed to the constructor.
	 */
	public Person(int personId, Random rand) {
		this.personId = personId;
		currentFloor = 0;
		size = 1;
		priority = false;
		waitTimer = 0;
		this.rand = rand;
	}
	
	//calls the lift if the destination is wrong
	protected void call() {
		if (currentDestination != currentFloor) {
			Lift.addDestination(this);
			System.out.println(this.personId + " has called the lift to travel from " + 
					this.currentFloor + " to " + this.currentDestination);
		}
	}
	
	/**
	 * Returns the destination of the person.
	 * @return The integer number denoting the current destination floor.
	 */
	public int getCurrentDestination() {
		return currentDestination;
	}

	/**
	 * Returns the location of the person.
	 * @return The integer number denoting the current floor.
	 */
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	/**
	 * Returns the ID of the person.
	 * @return The person ID as an integer.
	 */
	public int getPersonId() {
		return personId;
	}
	
	/**
	 * Returns the size of the person, for determining the elevator capacity.
	 * @return The size of the person (should be either 1 or 4)
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns whether the person has priority for getting in the lift.
	 * @return Boolean of priority (clients have it, others do not)
	 */
	public boolean getPriority() {
		return priority;
	}
	
	/**
	 * Called at the end of the day or when a client gets fed up waiting, this method
	 * sets the person's destination to 0 and calls the lift. Should only be called when
	 * the person is not currently in the lift.
	 */
	public void leave() {
		currentDestination = 0;
		call();
	}	
	
	/**
	 * Sets the location of the person to a floor. This also stops the person from being
	 * flagged as waiting for the lift, as this method is used when they have arrived at 
	 * their destination.
	 * @param currentFloor The floor you want to put the person on.
	 */
	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
		waitTimer = 0;
	}
	
	/**
	 * A formula that neatly halves the number of floors in the building, rounding up.
	 * @return The number of floors in the building / 2, rounded up.
	 */
	public int halfFloorFormula() {
		return Building.getNumberOfFloors() / 2 + (Building.getNumberOfFloors() % 2 == 0 ? 0 : 1);
	}
	
	/**
	 * Increases the amount of time someone has been waiting by 1 tick.
	 */
	public void countWaitTimer() {
		waitTimer++;
	}
	
	/**
	 * Returns the amount of time this person has spent waiting for lifts all day.
	 * @return
	 */
	public int getWaitTimer() {
		return waitTimer;
	}
	
}
