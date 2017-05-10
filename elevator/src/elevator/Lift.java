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
	
	public Lift(int capacity){
		currentFloor = 0;
		currentDirection = true;
		destinations = new ArrayList<Integer>();
		this.capacity = capacity;
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
	
	public void doStuff(){
		updateDestinations();
		System.out.println(destinations.toString());
	}
	
	//Populates an array of destinations 
	private void updateDestinations(){
		ArrayList<Integer> higher = new ArrayList<Integer>();
		ArrayList<Integer> lower = new ArrayList<Integer>();
		
		//Get each person
		for (Person person : contains.keySet()){
			//check if they are in the lift, if they are add their destination, if not add their current floor
			if (contains.get(person)){
				if(person.getCurrentDestination() > currentFloor){
					higher.add(person.currentDestination);
				}else{
					lower.add(person.currentDestination);
				}
			}else{
				if(person.getCurrentFloor() > currentFloor){
					higher.add(person.currentFloor);
				}else{
					lower.add(person.currentFloor);
				}
			}
		}
		
		//Sort higher as increasing values
		Collections.sort(higher);
		
		for (int x = 1; x < higher.size(); x++){
			int temp1 = higher.get(x-1);
			int temp2 = higher.get(x);
			if (temp1 == temp2){
				higher.remove(x);
				x = x - 1;
			}
		}
		
		//Sort lower as decreasing values
		Collections.sort(lower, Collections.reverseOrder());
		
		for (int x = 1; x < lower.size(); x++){
			int temp1 = lower.get(x-1);
			int temp2 = lower.get(x);
			if (temp1 == temp2){
				lower.remove(x);
				x = x - 1;
				
			}
		}
		
		//add destinations to destinations arraylist dependent on movement direction
		if (currentDirection){
			higher.addAll(lower);
			destinations.addAll(higher);
		}else{
			lower.addAll(higher);
			destinations.addAll(lower);
		}
	}

}
