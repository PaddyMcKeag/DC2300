package test;

import static org.junit.Assert.*;

import org.junit.Test;

import elevator.Employee;
import elevator.Lift;

public class LiftTest {

	@Test
	public void test() {
		Lift lift = new Lift(6);
		Employee employee1 = new Employee(1);
		Employee employee2 = new Employee(2);
		Employee employee3 = new Employee(3);
		employee1.setCurrentFloor(1);
		employee2.setCurrentFloor(3);
		employee3.setCurrentFloor(5);
		lift.addDestination(employee3);
		lift.addDestination(employee1);
		lift.addDestination(employee2);
	}

}
