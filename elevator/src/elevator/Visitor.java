/**
 * The Visitor class is an abstract class that is extended by Client and Crew
 * to model the people that arrive after the day has started and leave after a
 * randomly generated amount of time.
 * 
 * @author Paddy McKeag
 * @version 1.0
 * @since 2017-05-19
 */

package elevator;
import java.util.Random;

public abstract class Visitor extends Person {
	
	//timer to keep track of how long the visitor has been completing their task
	protected int timeInBuilding;
	//stores the time until the visitor leaves
	protected int timeToLeave;
	
	/**
	 * Constructor for Visitor. As an abstract class, this is only called
	 * as a superclass constructor.
	 * @param personId
	 * @param rand
	 */
	public Visitor(int personId, Random rand) {
		super(personId, rand);
		this.timeInBuilding = 0;
	}
	
	//counts up the timer to the point where the visitor leaves
	/**
	 * Used to increment the timer that counts how long the Visitor has been
	 * in the building at their chosen floor. Once a certain amount of time has
	 * passed at that floor (determined at object creation) the Visitor will
	 * leave.
	 */
	public int addToTimer() {
		if (this.timeInBuilding < this.timeToLeave) {
			this.timeInBuilding++;
		} else
			this.leave();
		return this.timeInBuilding;
	}
	
	/**
	 * Returns the amount of time the Visitor will stay in the building.
	 */
	public int getTimeToLeave() { 
		return this.timeToLeave;
	}
	
}
