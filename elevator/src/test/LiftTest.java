package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import elevator.Building;
import elevator.Employee;
import elevator.Lift;

public class LiftTest {

//	@Test
//	public void test() {
//		//Creates lift
//		Lift lift = new Lift(6);
//		//creates building
//		Building building = new Building(8);
//		//Creates employees
//		Employee employee1 = new Employee(1, 1);
//		Employee employee2 = new Employee(2, 2);
//		Employee employee3 = new Employee(3, 3);
//		Employee employee4 = new Employee(4, 4);
//		Employee employee5 = new Employee(5, 5);
//		Employee employee6 = new Employee(6, 6);
//		Employee employee7 = new Employee(7, 7);
//		Employee employee8 = new Employee(8, 8);
//		Employee employee9 = new Employee(9, 9);
//		//Changes employees current floor
//		employee1.setCurrentFloor(1);
//		employee2.setCurrentFloor(3);
//		employee3.setCurrentFloor(5);
//		employee4.setCurrentFloor(5);
//		employee5.setCurrentFloor(5);
//		employee6.setCurrentFloor(6);
//		employee7.setCurrentFloor(2);
//		employee8.setCurrentFloor(3);
//		employee9.setCurrentFloor(1);
//		//adds employees to the lift for destinations
//		lift.addDestination(employee3);
//		lift.addDestination(employee1);
//		lift.addDestination(employee2);
//		lift.addDestination(employee4);
//		lift.addDestination(employee6);
//		lift.addDestination(employee5);
//		lift.addDestination(employee7);
//		lift.addDestination(employee9);
//		lift.addDestination(employee8);
//		//Makes the lift process the destination array and order it correctly
//		lift.tick();
//	}
	
	@Test
	public void testCreateALift(){
		//Create a lift
		Lift lift = new Lift(8);
		//check that all the variables have set correctly
		assertFalse(lift.getDoorOpen());
		assertEquals(0, lift.getCurrentFloor());
	}
	
	@Test
	public void testAddDestinations(){
		//Create Lift
		Lift lift = new Lift(8);
		//Add destinations
		lift.addDestination(1);
		lift.addDestination(3);
		lift.addDestination(8);
		lift.addDestination(6);
		lift.addDestination(4);
		//expected array value
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 3, 8, 6, 4));
		//check destinations
		assertEquals(expected, lift.getDestinations());
	}
	
	@Test
	public void testMoveUp(){
		//Test the lift moves up
		//Create Lift
		Lift lift = new Lift(10);
		//Add destinations
		lift.addDestination(1);
		lift.addDestination(2);
		lift.addDestination(7);
		lift.addDestination(5);
		lift.addDestination(4);
		//Lift tick call, makes the lift do its next action
		lift.tick();
		//Checks if lift has moved up a floor
		assertEquals(1, lift.getCurrentFloor());
	}
	
	@Test
	public void testMoveDown(){
		//Test the lift moves down
		//Create Lift
		Lift lift = new Lift(10);
		//Add destinations
		lift.addDestination(3);
		//Lift tick call, makes the lift do its next action, move up to floor 3
		lift.tick();
		lift.tick();
		lift.tick();
		//close and open lift doors
		lift.tick();
		lift.tick();
		//add a destination below
		lift.addDestination(2);
		//Lift call to make the lift move back down
		lift.tick();
		lift.tick();
		lift.tick();
		//Checks if lift has moved up a floor
		assertEquals(2, lift.getCurrentFloor());
	}
	
	@Test
	public void testOpenDoor(){
		//Test the lift doors open
		//Create Lift
		Lift lift = new Lift(10);
		//Add destinations
		lift.addDestination(0);
		//lift call, makes lift open doors if at destination
		lift.tick();
		//Check doors are now open
		assertTrue(lift.getDoorOpen());
	}
	
	@Test
	public void testCloseDoor(){
		//test the lift doors close
		//Create Lift
		Lift lift = new Lift(10);
		//Add destinations
		lift.addDestination(0);
		lift.addDestination(1);
		//lift call, makes lift open doors if at destination
		lift.tick();
		//lift lets people out
		lift.tick();
		//lift call, makes lift doors close as next destination is on different floor
		lift.tick();
		//Check doors are now closed
		assertFalse(lift.getDoorOpen());
	}
	
	@Test
	public void testMoveBacktoFloor(){
		//test the lift moves back to the bottom floor
		//Create Lift
		Lift lift = new Lift(10);
		//add destination to make lift move
		lift.addDestination(3);
		//make lift move to said floor
		lift.tick();
		assertEquals(1, lift.getCurrentFloor());
		lift.tick();
		assertEquals(2, lift.getCurrentFloor());
		lift.tick();
		assertEquals(3, lift.getCurrentFloor());
		//make lift doors open then lets people out then close
		lift.tick();
		lift.tick();
		lift.tick();
		//make lift move back down and check on each floor its moved
		lift.tick();
		assertEquals(2, lift.getCurrentFloor());
		lift.tick();
		assertEquals(1, lift.getCurrentFloor());
		lift.tick();
		assertEquals(0, lift.getCurrentFloor());
	}
	
	@Test
	public void testDontMove(){
		//test the lift does not move when it should not 
		//Create Lift
		Lift lift = new Lift(10);
		//Lift call to make it consider doing something
		lift.tick();
		//check lift hasnt moved
		assertEquals(0, lift.getCurrentFloor());
	}

}
