package elevator;

import java.util.ArrayList;
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
	
	public Lift(){
		currentFloor = 0;
		currentDirection = true;
		destinations = new ArrayList<Integer>();
	}
	
	private void open(){
		
	}
	
	private void close(){
		
	}
	
	private void moveUp(){
		
	}
	
	private void moveDown(){
		
	}
	
	private void stopAtFloor(int floor){
		if (floor != currentFloor){
			
		}
	}
	
	//Gets passed the person when they call the lift as to know who they are and where they want to go
	public static void addDestination(Person person){
		contains.put(person, false);
	}
	
	//Populates an array of destinations 
	private void updateDestinations(){
		ArrayList<Integer> otherDestinations = new ArrayList<Integer>();
		if (currentDirection){
			for (Person person : contains.keySet()){
				if (person.getCurrentDestination() > currentFloor){
					destinations.add(person.getCurrentDestination());
				}else{
					
				}
			}
		}else{
			for (Person person : contains.keySet()){
				if (person.getCurrentDestination() < currentFloor){
					destinations.add(person.getCurrentDestination());
				}
			}
		}
	}

}
