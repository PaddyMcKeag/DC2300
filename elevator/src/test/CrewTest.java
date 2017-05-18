package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elevator.Building;
import elevator.Crew;

public class CrewTest {

	private Crew crew;
	private Building building;
	
	@Before	
	public void setUp() {
		building = new Building(8);
		crew = new Crew(1);
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(crew);		
		assertTrue(crew.getTimeToLeave() <= 240 && crew.getTimeToLeave() >= 60);
		assertEquals(crew.getCurrentDestination(), Building.getNumberOfFloors());
	}
	
	@Test
	public void setCurrentFloorTest() {
		int firstLocation = crew.getCurrentFloor();
		crew.setCurrentFloor(crew.getCurrentFloor() + 1);
		int secondLocation = crew.getCurrentFloor();
		assertNotEquals(firstLocation, secondLocation);
	}
	
	@Test 
	public void callTest() {
		crew.setCurrentFloor(crew.getCurrentDestination());
	}
	
	@Test
	public void leaveTest() {
		crew.leave();
		assertEquals(0, crew.getCurrentDestination());
	}
	
}