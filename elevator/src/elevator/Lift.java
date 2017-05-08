package elevator;

import java.util.ArrayList;

public class Lift {
	//current floor that the elevator is on
	public int currentFloor;
	
	//Direction is a boolean, with True meaning up and False meaning down
	public boolean currentDirection;
	
	//Array containing the people in the elevator
	public ArrayList<Person> contains;
	
	//Array containing the floors that the people in the elevator want to go to
	//This is static as when an elevator is called the name will not always be known to the person class
	public static ArrayList<Floor> destinations = new ArrayList<Floor>();
	
	public Lift(){
		currentFloor = 0;
		currentDirection = true;
		contains = new ArrayList<Person>();
	}
	
	public void open(){
		
	}
	
	public void close(){
		
	}
	
	public void moveUp(){
		
	}
	
	public void moveDown(){
		
	}
	
	public void stopAtFloor(int floor){
		if (floor != currentFloor){
			
		}
	}

}
