package elevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Lift {
	//current floor that the elevator is on
	private int currentFloor;
	
	//Direction is a boolean, with True meaning up and False meaning down
	private boolean currentDirection;
	
	//Array containing the people in the elevator
	private static LinkedHashMap<Person, Boolean> contains = contains = new LinkedHashMap<Person, Boolean>();
	
	//Array containing the floors that the people in the elevator want to go to
	//This is static as when an elevator is called the name will not always be known to the person class
	private ArrayList<Integer> destinations;
	
	//Lifts capacity set by user
	private int capacity;
	
	//Lift doors open/close boolean open=true, closed=false
	private boolean doorOpen;
	
	public Lift(int capacity){
		currentFloor = 0;
		currentDirection = true;
		destinations = new ArrayList<Integer>();
		this.capacity = capacity;
		doorOpen = false;
	}
	
	//returns status of the lift door
	public boolean getDoorOpen(){
		return doorOpen;
	}
	
	//Returns the lifts current floor
	public int getCurrentFloor(){
		return currentFloor;
	}
	
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
	
	//Gets passed the person when they call the lift as to know who they are and where they want to go
	public static void addDestination(Person person){
		contains.put(person, false);
	}
	
	//mainly for testing purposes, to add destinations without the need of a person 
	public void addDestination(int newDestination){
		destinations.add(newDestination);
	}
	
	//the function that will be run every tick where the elevator will decide its next move
	public void tick(){
		updateDestinations();
		int nextDestination;
		if (destinations.size() > 0){
			nextDestination = destinations.get(0);
			if (nextDestination == currentFloor){
				if (doorOpen){
					//let people on or off or close door, when door is closed destination must be deleted
					
					//Check for people whos destination is current floor then let them leave
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
		}else{
			//moves back to the ground floor if no directions
			currentDirection = false;
			moveDown();
		}
	}
	
	//Finds people who want to leave the lift
	private void leaveLift(){
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			//if the person is in the lift and there destination is the current floor do...
			if (inLift && person.currentDestination == currentFloor){
				//remove from contains as theyre not waiting for a lift
				contains.remove(person);
				//change their current floor to represent where they are now
				person.setCurrentFloor(currentFloor);
			}
		}
	}
	
	//Finds people who want to enter the lift
	private void enterLift(){
		//first checks for piority people
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			if (!inLift && person.currentFloor == currentFloor && allowedToEnter(person) && person.getPriority()){
				contains.put(person, true);
			}
		}
		//then checks for other people
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			if (!inLift && person.currentFloor == currentFloor && allowedToEnter(person)){
				contains.put(person, true);
			}
		}
	}
	
	//checks if each person can enter when at the front of the queue
	private boolean allowedToEnter(Person person){
		if (remainingCapacity() >= person.size){
			//checks for developers, as developers wont go in the lift with rivals
			if (person instanceof Dev){
				boolean sameDev = devCompare(person);
				//if devtype is not null then check if theyre the same or not
				if (sameDev){
					return true;
				}	
			}
			return true;
		}
		return false;	
	}
	
	//check for other developers and return their status
	private boolean devCompare(Person developer){
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			if (inLift){
				if (person instanceof Dev){
					if(((Dev) developer).getCompany() == ((Dev) person).getCompany()){
						//returns true if both people in lift and developer are same company
						//e.g. can both be in the lift at the same time
						return true;
					}else{
						//returns false when a competitor is in the lift
						//e.g. can not both be in the lift
						return false;
					}
				}
			}
		}
		//returns true when no other developers are in the lift too
		return true;
	}
	
	//calculates capacity remaining by checking what people are in the lift
	private int remainingCapacity(){
		int remainingCapacity = capacity;
		for (Person person : contains.keySet()){
			boolean inLift = contains.get(person);
			if (inLift){
				capacity = capacity - person.size;
			}
		}
		return remainingCapacity;
	}
	
	//Populates an array of destinations 
	private void updateDestinations(){
		ArrayList<Integer> higher = new ArrayList<Integer>();
		ArrayList<Integer> lower = new ArrayList<Integer>();
		
		//Get each person
		for (Person person : contains.keySet()){
			//check if they are in the lift, if they are add their destination, if not add their current floor
			if (contains.get(person)){
				//adds destinations of everyone in the lift to the lift destinations
				if(person.getCurrentDestination() > currentFloor){
					higher.add(person.currentDestination);
				}else{
					lower.add(person.currentDestination);
				}
			}else{
				//adds current floor as these people are not in the elevator
				if(person.getCurrentFloor() > currentFloor){
					higher.add(person.currentFloor);
				}else{
					lower.add(person.currentFloor);
				}
			}
		}
		
		//Sort higher as increasing values
		Collections.sort(higher);
		
		//Sort lower as decreasing values
		Collections.sort(lower, Collections.reverseOrder());
		
		//add destinations to destinations arraylist dependent on movement direction
		if (currentDirection){
			higher.addAll(lower);
			destinations.addAll(higher);
		}else{
			lower.addAll(higher);
			destinations.addAll(lower);
		}
		
		//removes duplicate values from destinations array
		for (int x = 1; x < destinations.size(); x++){
			int temp1 = destinations.get(x-1);
			int temp2 = destinations.get(x);
			if (temp1 == temp2){
				lower.remove(x);
				x = x - 1;
			}
		}
		
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

}
