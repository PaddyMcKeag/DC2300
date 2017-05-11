package test;

import static org.junit.Assert.*;

import org.junit.Test;

import elevator.Building;
import elevator.Employee;
import elevator.Lift;

public class LiftTest {

	@Test
	public void test() {
		//Creates lift
		Lift lift = new Lift(6);
		//creates building
		Building building = new Building(8);
		//Creates employees
		Employee employee1 = new Employee(1, 1);
		Employee employee2 = new Employee(2, 2);
		Employee employee3 = new Employee(3, 3);
		Employee employee4 = new Employee(4, 4);
		Employee employee5 = new Employee(5, 5);
		Employee employee6 = new Employee(6, 6);
		Employee employee7 = new Employee(7, 7);
		Employee employee8 = new Employee(8, 8);
		Employee employee9 = new Employee(9, 9);
		//Changes employees current floor
		employee1.setCurrentFloor(1);
		employee2.setCurrentFloor(3);
		employee3.setCurrentFloor(5);
		employee4.setCurrentFloor(5);
		employee5.setCurrentFloor(5);
		employee6.setCurrentFloor(6);
		employee7.setCurrentFloor(2);
		employee8.setCurrentFloor(3);
		employee9.setCurrentFloor(1);
		//adds employees to the lift for destinations
		lift.addDestination(employee3);
		lift.addDestination(employee1);
		lift.addDestination(employee2);
		lift.addDestination(employee4);
		lift.addDestination(employee6);
		lift.addDestination(employee5);
		lift.addDestination(employee7);
		lift.addDestination(employee9);
		lift.addDestination(employee8);
		//Makes the lift process the destination array and order it correctly
		lift.tick();
	}

}
