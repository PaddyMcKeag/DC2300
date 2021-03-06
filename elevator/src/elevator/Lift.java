/**
 * The Lift class is a part of the program which functions as a lift.
 * It contains the procedures for holding people in the lift,
 * moving the lift up and down, checking the lift doors are open or closed
 * and closing or opening them, and storing a list of destinations populated 
 * from the people in the lift and those requesting the lift.
 * 
 * @author Grant Worsley
 * @version 1.0
 * @since 2017-05-09
 */
package elevator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Lift {
	//current floor that the elevator is on
	private int currentFloor;

	//Direction is a boolean, with True meaning up and False meaning down
	private boolean currentDirection;

	//Array containing the people in the elevator
	private static LinkedHashMap<Person, Boolean> contains = new LinkedHashMap<Person, Boolean>();

	//Array containing the floors that the people in the elevator want to go to
	//This is static as when an elevator is called the name will not always be known to the person class
	private ArrayList<Integer> destinations;

	//Lifts capacity set by user
	private int capacity;

	//Lift doors open/close boolean open=true, closed=false
	private boolean doorOpen;

	//Total number of people transported
	private int peopleTransported;

	/**
	 * This method is the constructor of this class.
	 * It is used to define the variables frist set when
	 * a lift is created.
	 * @param capacity This parameter is used to set the lifts capacity
	 */
	public Lift(int capacity){
		currentFloor = 0;
		currentDirection = true;
		destinations = new ArrayList<Integer>();
		this.capacity = capacity;
		doorOpen = false;
		contains.clear();
		destinations.clear();
		peopleTransported = 0;
	}

	/**
	 * This method is called to give the status of the lift door.
	 * So anyone can know if the lift doors are open or closed.
	 * @return This returns the boolean status of the lift door
	 */
	//returns status of the lift door
	public boolean getDoorOpen(){
		return doorOpen;
	}

	/**
	 * This method is called to give someone the placement of the lift.
	 * @return This returns the int of the elevators current floor
	 */
	//Returns the lifts current floor
	public int getCurrentFloor(){
		return currentFloor;
	}

	/**
	 * This method is called to give someone the array of all the destinations the
	 * elevator is going to.
	 * @return This returns an array of destinations which are numbers
	 */
	//returns an array of the destinations
	public ArrayList<Integer> getDestinations(){
		return destinations;
	}

	private void open(){
		doorOpen = true;
	}

	private void close(){
		doorOpen = false;
	}

	private void moveUp(){
		if (doorOpen){
			//door must be closed
			close();
		}else{
			//must not be trying to go above the limit of the building
			if (currentFloor < Building.getNumberOfFloors()){
				currentFloor = currentFloor + 1;
			}
		};
	}

	private void moveDown(){
		if (doorOpen){
			//door must be closed
			close();
		}else{
			//must not be trying to go below the building
			if (currentFloor > 0){
				currentFloor = currentFloor - 1;
			}
		}
	}

	/**
	 * This method allows people to be added to the lift so you can track what people are
	 * waiting for the lift and what people are in the lift, this is represented with the boolean
	 * as it is always set to false for when people are waiting for the lift.
	 * This information is used also to populate what destinations the lift
	 * must go to.
	 * @param person This is the person that is being added to the lift conatining list
	 */
	//Gets passed the person when they call the lift as to know who they are and where they want to go
	public static void addDestination(Person person){
		contains.put(person, false);
	}

	/**
	 * This method was used mainly for testing purposes. This allows
	 * destinations to be added to the lift without people being created.
	 * @param newDestination This allows destinations to be added to the lift
	 */
	//mainly for testing purposes, to add destinations without the need of a person 
	public void addDestination(int newDestination){
		destinations.add(newDestination);
	}

	/**
	 * This method represents one tick in the simulation, also known as 10 seconds.
	 * It will update its destinations to know where it needs to go to next.
	 * After it will pull the next destination and decide if it is above or below,
	 * if it is not the current floor it will move one floor in the destinations direction.
	 * If it is on the current floor it will open its doors, or let people in or out of the lift.
	 * If the doors are open and its destinations is not the current floor it will close
	 * the doors.
	 */
	//the function that will be run every tick where the elevator will decide its next move
	public void tick(){
		updateDestinations();
		int nextDestination;
		if (destinations.size() > 0){
			nextDestination = destinations.get(0);
			if (nextDestination == currentFloor){
				if (doorOpen){
					//let people on or off or close door, when door is closed destination must be deleted

					//Check for people who's destination is current floor then let them leave
					leaveLift();

					//check for people to get into lift and checks they can enter then lets them enter
					enterLift();
					
					//remove destination as everyone has left or entered
					destinations.remove(0);
				}else{
					open();
				}
			}else if(nextDestination > currentFloor){
				//move up a floor
				moveUp();
			}else if(nextDestination < currentFloor){
				//move down a floor
				moveDown();
			}
		}else if (currentFloor > 0){
			//moves back to the ground floor if no directions
			currentDirection = false;
			moveDown();
		}
	}

	//Finds people who want to leave the lift
	private void leaveLift(){
		for (Iterator<Person> iterator = contains.keySet().iterator(); iterator.hasNext();) {
			Person person = iterator.next();
			if (this.isInLift(person) && person.currentDestination == this.currentFloor) {
				iterator.remove();
				person.setCurrentFloor(this.currentFloor);
			}
		}
	}

	//Finds people who want to enter the lift
	private void enterLift(){
		//first checks for priority people
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			if (!inLift && person.currentFloor == currentFloor && allowedToEnter(person) && person.getPriority()){
				contains.put(person, true);
				peopleTransported++;
			}
		}
		//then checks for other people
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			if (!inLift && person.currentFloor == currentFloor && allowedToEnter(person)){
				contains.put(person, true);
				peopleTransported++;
			}
		}
	}


	//checks if each person can enter when at the front of the queue
	private boolean allowedToEnter(Person person){
		if (remainingCapacity() >= person.getSize()){
			//checks for developers, as developers wont go in the lift with rivals
			if (person instanceof Dev){
				boolean sameDev = devCompare(person);
				//if devtype is not null then check if theyre the same or not
				if (sameDev){
					return true;
				}	
			} else{
				return true;
			}
		}
		return false;	
	}

	//check for other developers and return their status
	private boolean devCompare(Person developer){
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			if (inLift){
				if (person instanceof Dev){
					if(((Dev) developer).getCompany() != ((Dev) person).getCompany()){
						//returns false if both people in lift and developer work for rival companies
						//e.g. can both be in the lift at the same time								
						return false;
					}
				}
			}
		}
		//returns true when no other developers are in the lift too
		return true;
	}

	//calculates capacity remaining by checking what people are in the lift
	public int remainingCapacity(){
		int remainingCapacity = capacity;
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			if (inLift){
				remainingCapacity = remainingCapacity - person.getSize();
			}
		}
		return remainingCapacity;
	}

	//Populates an array of destinations 
	private void updateDestinations(){
		ArrayList<Integer> higher = new ArrayList<Integer>();
		ArrayList<Integer> lower = new ArrayList<Integer>();
		ArrayList<Integer> current = new ArrayList<Integer>();

		//Get each person
		for (Person person : contains.keySet()){
			//check if they are in the lift, if they are add their destination, if not add their current floor
			if (contains.get(person)){
				//adds destinations of everyone in the lift to the lift destinations
				if(person.getCurrentDestination() > currentFloor){
					higher.add(person.currentDestination);
				}else if(person.getCurrentDestination() < currentFloor){
					lower.add(person.currentDestination);
				}else if(person.getCurrentDestination() == currentFloor){
					current.add(person.currentDestination);
				}
			}else{
				//adds current floor as these people are not in the elevator
				if(person.getCurrentFloor() > currentFloor){
					higher.add(person.currentFloor);
				}else if(person.getCurrentFloor() < currentFloor){
					lower.add(person.currentFloor);
				}else if(person.getCurrentFloor() == currentFloor){
					current.add(person.currentFloor);
				}
			}
		}
		
		//Sort higher as increasing values
		Collections.sort(higher);
		
		//Sort lower as decreasing values
		Collections.sort(lower, Collections.reverseOrder());
		
		//add destinations to destinations arraylist dependent on movement direction
		if (currentDirection){
			current.addAll(higher);
			current.addAll(lower);
			destinations.addAll(current);
		}else{
			current.addAll(lower);
			current.addAll(higher);
			destinations.addAll(current);
		}
		
		Set<Integer> temp = new LinkedHashSet<>();
		temp.addAll(destinations);
		destinations.clear();
		destinations.addAll(temp);
		
		//set lift direction
		if (destinations.size() > 0){
			if (destinations.get(0) > currentFloor){
				currentDirection = true;
			}else if (destinations.get(0) < currentFloor){
				currentDirection = false;
			}
		}else{
			if (currentFloor > 0){
				currentDirection = false;
			}else{
				currentDirection = true;
			}
		}
	}

	public boolean isInLift(Person person) {
		if (contains.keySet().contains(person))
			return contains.get(person);
		else
			return false;
	}

	public int getPeopleTransported() {
		return peopleTransported;
	}

}
