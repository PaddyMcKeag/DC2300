package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elevator.Building;
import elevator.Dev;

public class DevTest {

	private Dev dev;
	private Building building;
	
	@Before	
	public void setUp() {
		dev = new Dev(1, false);
		building = new Building(3);
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(dev);		
		System.out.println(dev.getCurrentDestination());
		assertFalse(dev.getCurrentDestination() < dev.halfFloorFormula() ||
				dev.getCurrentDestination() > Building.getNumberOfFloors());
	}
	
	@Test
	public void setCurrentFloorTest() {
		int firstLocation = dev.getCurrentFloor();
		dev.setCurrentFloor(dev.getCurrentFloor() + 1);
		int secondLocation = dev.getCurrentFloor();
		assertNotEquals(firstLocation, secondLocation);
	}
	
	@Test
	public void changeDestinationTest() {
		int firstDestination = dev.getCurrentDestination();
		dev.setCurrentFloor(dev.getCurrentDestination());
		dev.changeDestination(1.0);	
		int secondDestination = dev.getCurrentDestination();
		assertNotEquals(firstDestination, secondDestination);
	}
	
	@Test 
	public void callTest() {
		assertTrue(dev.getWaiting());
		dev.setCurrentFloor(dev.getCurrentDestination());
		assertFalse(dev.getWaiting());
		dev.changeDestination(1.0);
		assertTrue(dev.getWaiting());
	}
	
	@Test
	public void leaveTest() {
		assertNotEquals(0, dev.getCurrentDestination());
		dev.leave();
		assertTrue(dev.getWaiting());
		assertEquals(0, dev.getCurrentDestination());
	}
	
}

