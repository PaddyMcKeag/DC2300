/**
 * The Worker class is an abstract class that is extended by Dev and Employee
 * to model the people that arrive at the start of the day, leave when it ends,
 * and have a chance to change floor during the day.
 * 
 * @author Paddy McKeag
 * @version 1.0
 * @since 2017-05-19
 */

package elevator;
import java.util.Random;

public abstract class Worker extends Person {

	//stops someone changing their mind if they're waiting
	protected boolean waiting;

	/**
	 * Constructor for Worker. As an abstract class, this is only 
	 * called as a superclass constructor.
	 * @param personId The ID of the person.
	 * @param rand The Random object used to generate random numbers for the system.
	 */
	public Worker(int personId, Random rand) {
		super(personId, rand);
		this.waiting = false;
	}
	
	//calls the lift and makes sure they don't change their mind while waiting
	/**
	 * {@inheritDoc}
	 */
	public void call() {
		super.call();
		this.waiting = true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setCurrentFloor(int currentFloor) {
		super.setCurrentFloor(currentFloor);
		this.waiting = false;
	}
	
	/**
	 * Returns whether or not the person is waiting for the lift.
	 */
	public boolean getWaiting() {
		return this.waiting;
	}
	
	/**
	 * Determines whether or not the person changes floor. The destination is based
	 * on another method which is different depending on the person type.
	 * @param probability The probability that the person will change floor.
	 */
	public void changeDestination(double probability) {
		if (!this.waiting) {
			if (rand.nextDouble() < probability) {
				this.currentDestination = this.determineDestination();
				if (this.currentDestination != this.currentFloor) {
					this.call();
				}
			}
			
		}
	}
	
	//stub for override
	/**
	 * Determines the destination that the person will move to.
	 */
	public int determineDestination() {
		return -1;
	}
}
