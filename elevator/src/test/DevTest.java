package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elevator.Building;
import elevator.Dev;
import java.util.Random;

public class DevTest {

	private Random rand;
	private Dev dev;
	private Building building;
	
	@Before	
	public void setUp() {
		rand = new Random();
		building = new Building(6);
		dev = new Dev(1, rand, false);
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(dev);		
		assertFalse(dev.getCurrentDestination() < dev.halfFloorFormula() ||
				dev.getCurrentDestination() >= Building.getNumberOfFloors());
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
		if (dev.getWaiting()) {
			assertNotEquals(firstDestination, secondDestination);
		} else
			assertEquals(firstDestination, secondDestination);
	}
	
	@Test 
	public void callTest() {
		assertTrue(dev.getWaiting());
		dev.setCurrentFloor(dev.getCurrentDestination());
		assertFalse(dev.getWaiting());
		dev.changeDestination(1.0);
		if (dev.getCurrentDestination() != dev.getCurrentFloor()) {
			assertTrue(dev.getWaiting());
		} 
		else 
			assertFalse(dev.getWaiting());
	}
	
	@Test
	public void leaveTest() {
		assertNotEquals(0, dev.getCurrentDestination());
		dev.leave();
		assertTrue(dev.getWaiting());
		assertEquals(0, dev.getCurrentDestination());
	}
	
}

