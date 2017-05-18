package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import elevator.Building;
import elevator.Client;

public class ClientTest {

	private Client client;
	private Building building;
	
	@Before	
	public void setUp() {
		client = new Client(1);
		building = new Building(8);
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(client);		
		assertTrue(client.getTimeToLeave() <= 180 && client.getTimeToLeave() >= 30);
	}
	
	@Test
	public void setCurrentFloorTest() {
		int firstLocation = client.getCurrentFloor();
		client.setCurrentFloor(client.getCurrentFloor() + 1);
		int secondLocation = client.getCurrentFloor();
		assertNotEquals(firstLocation, secondLocation);
	}
	
	@Test 
	public void callTest() {
		client.setCurrentFloor(client.getCurrentDestination());
	}
	
	@Test
	public void leaveTest() {
		client.leave();
		assertEquals(0, client.getCurrentDestination());
	}
	
	@Test
	public void complainTest() {
		assertEquals(client.getWaitTimer(), 0);
		for (int i = 0; i < 59; i++) {
			client.countWaitTimer();
			assertEquals(building.getComplaints(), 0);
		}
		client.countWaitTimer();
		assertEquals(building.getComplaints(), 1);
		assertEquals(client.getCurrentDestination(), 0);
	}
}