/**
 * The Client class models clients who arrive during the day. It extends Visitor.
 * @author Paddy McKeag
 * @version 1.0
 * @since 2017-05-19
 */

package elevator;
import java.util.Random;

public class Client extends Visitor {
	
	private int currentWaitTimer;
	
	/**
	 * Constructor for Client. They will move to a random location in the bottom 
	 * half of the building for a random amount of time. If they wait more than 10
	 * minutes for a lift, they will complain and leave. Because of this, they have
	 * priority entry to the lift.
	 * @param personId The ID of the person.
	 * @param rand The Random object used to generate random numbers for the system.
	 */
	public Client(int personId, Random rand) {
		super(personId, rand);
		this.currentWaitTimer = 0;
		this.priority = true;
		this.timeToLeave = (rand.nextInt(20) + 10) * 6;
		this.currentDestination = rand.nextInt(this.halfFloorFormula());
			this.call();
	}
	
	/**
	 * Increases the amount of time the person has been waiting by 1 tick. Also
	 * increases a variable that counts for each lift journey. If this second
	 * variable exceeds 10 minutes worth of ticks, the client complains and leaves.
	 */
	public void countWaitTimer() {
		super.countWaitTimer();
		this.currentWaitTimer++;
		if (this.currentWaitTimer == 60) { 
			this.complain();
			this.leave();
		}
	}
	
	/**
	 * The client adds a complaint, stored in the Building class.
	 */
	public void complain() {
		Building.addComplaint();
	}
	
	public void setCurrentFloor(int currentFloor) {
		super.setCurrentFloor(currentFloor);
		this.currentWaitTimer = 0;
	}
	
}
