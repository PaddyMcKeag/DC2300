/**
 * The Employee class models employees who work in the building. It extends Worker.
 * @author Paddy McKeag
 * @version 1.0
 * @since 2017-05-19
 */
package elevator;
import java.util.Random;

public class Employee extends Worker {

	/**
	 * Constructor for Employee. The employee chooses a random destination 
	 * of any floor in the building.
	 * 
	 * @param personId The ID of the person.
	 * @param rand The Random object used to generate random numbers for the system.
	 */
	public Employee(int personId, Random rand) {
		super(personId, rand);
		this.currentDestination = rand.nextInt(Building.getNumberOfFloors() - 1);
		this.call();
	}

	//should be called by run-time every tick
	//cannot change their mind while waiting
	/**
	 * {@inheritDoc}
	 */
	public int determineDestination(double probability) {
		return rand.nextInt(Building.getNumberOfFloors() -1);
	}
}
