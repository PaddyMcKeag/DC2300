/**
 * The Crew class models crews who arrive during the day. It extends Visitor.
 * @author Paddy McKeag
 * @version 1.0
 * @since 2017-05-19
 */

package elevator;
import java.util.Random;

public class Crew extends Visitor {

	/**
	 * Constructor for Crew. These people take up more space in the lift and
	 * will always move to the top floor of the building for a certain amount 
	 * of time.
	 * @param personId The ID of the person.
	 * @param rand The Random object used to generate random numbers for the system.
	 */
	public Crew(int personId, Random rand) {
		super(personId, rand);
		this.size = 4;
		this.timeToLeave = (rand.nextInt(20) + 20) * 6;
		this.currentDestination = Building.getNumberOfFloors() - 1;
		call();
	}

}
