package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import elevator.Building;
import elevator.Crew;
import elevator.Dev;
import elevator.Employee;
import elevator.Lift;
import java.util.Random;

public class LiftTest {
	
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
		//Checks if lift has moved down a floor
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
	
	@Test 
	public void fillLift(){
		//test the lift can not be over filled
		//Create Lift
		Random rand = new Random(); 
		Lift lift = new Lift(8);
		//Create persons to go into the lift
		Crew crew1 = new Crew(1, rand);
		Crew crew2 = new Crew(2, rand);
		Dev dev1 = new Dev(3, rand, true);
		Dev dev2 = new Dev(4, rand, true);
		//Open lift doors
		lift.tick();
		//Let people into the lift
		lift.tick();
		//Check the lift is full
		assertEquals(0, lift.remainingCapacity());
	}
	
	@Test 
	public void oppositeCompanies(){
		//test the lift can not be over filled
		//Create Lift
		Lift lift = new Lift(8);
		Random rand = new Random();
		//Create persons to go into the lift
		Dev dev1 = new Dev(1, rand, true);
		Dev dev2 = new Dev(2, rand, false);
		//Open lift doors
		lift.tick();
		//Let people into the lift
		lift.tick();
		//Check the lift is full
		assertEquals(7, lift.remainingCapacity());
	}
	
	//In this test two people get into the lift, and one exits and one stays in
	//This test verifies this by checking the list of destinations and if the lift contains the person or not
	@Test
	public void getOutOfLift(){
		//test the lift can not be over filled
		//Create Lift
		Lift lift = new Lift(8);
		Random rand = new Random();
		//Create persons to go into the lift
		Dev dev1 = new Dev(1, rand, true);
		Dev dev2 = new Dev(2, rand, true);
		//expected array value
		ArrayList<Integer> expected = new ArrayList<Integer>();
		//Open lift
		lift.tick();
		//Let people in
		lift.tick();
		//Close lift
		lift.tick();
		//Move to floor
		if (dev1.getCurrentDestination() < dev2.getCurrentDestination()){
			for (int x = 1; x < dev1.getCurrentDestination(); x++){
				lift.tick();
			}
			lift.tick();
			//Open lift
			lift.tick();
			//Let people out
			lift.tick();
			assertEquals(lift.getCurrentFloor(), dev1.getCurrentDestination());
			assertEquals(false, lift.isInLift(dev1));
			assertEquals(true, lift.isInLift(dev2));
			expected.add(dev2.getCurrentDestination());
		}else if (dev1.getCurrentDestination() > dev2.getCurrentDestination()){
			for (int x = 1; x < dev2.getCurrentDestination(); x++){
				lift.tick();
			}
			lift.tick();
			//Open lift
			lift.tick();
			//Let people out
			lift.tick();
			assertEquals(lift.getCurrentFloor(), dev2.getCurrentDestination());
			assertEquals(true, lift.isInLift(dev1));
			assertEquals(false, lift.isInLift(dev2));
			expected.add(dev1.getCurrentDestination());
		}else{
			for (int x = 1; x < dev2.getCurrentDestination(); x++){
				lift.tick();
			}
			lift.tick();
			//Open lift
			lift.tick();
			//Let people out
			lift.tick();
			assertEquals(lift.getCurrentFloor(), dev2.getCurrentDestination());
			assertEquals(false, lift.isInLift(dev1));
			assertEquals(false, lift.isInLift(dev2));
		}
		//Check destination has been removed
		assertEquals(expected, lift.getDestinations());
	}

}
