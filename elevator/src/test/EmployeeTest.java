package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import elevator.Employee;
import elevator.Building;
import java.util.Random;

public class EmployeeTest {

	private Employee employee;
	private Building building;
	private Random rand;
	
	@Before	
	public void setUp() {
		Random rand = new Random();
		employee = new Employee(1, rand);
		building = new Building(8);
	}
	
	@Test
	public void constructorTest() {
		assertNotNull(employee);		
	}
	
	@Test
	public void setCurrentFloorTest() {
		int firstLocation = employee.getCurrentFloor();
		employee.setCurrentFloor(employee.getCurrentFloor() + 1);
		int secondLocation = employee.getCurrentFloor();
		assertNotEquals(firstLocation, secondLocation);
	}
	
	@Test
	public void changeDestinationTest() {
		int firstDestination = employee.getCurrentDestination();
		employee.setCurrentFloor(employee.getCurrentDestination());
		employee.changeDestination(1.0);	
		int secondDestination = employee.getCurrentDestination();
		if (employee.getCurrentDestination() != employee.getCurrentFloor()) 
			assertNotEquals(firstDestination, secondDestination);
		else
			assertEquals(firstDestination, secondDestination);
	}
	
	@Test 
	public void callTest() {
		assertTrue(employee.getWaiting());
		employee.setCurrentFloor(employee.getCurrentDestination());
		assertFalse(employee.getWaiting());
		employee.changeDestination(1.0);
		if (employee.getCurrentDestination() != employee.getCurrentFloor()) {
			assertTrue(employee.getWaiting());
		} else
			assertFalse(employee.getWaiting());
	}
	
	@Test
	public void leaveTest() {
		assertNotEquals(0, employee.getCurrentDestination());
		employee.leave();
		assertTrue(employee.getWaiting());
		assertEquals(0, employee.getCurrentDestination());
	}
	
}
