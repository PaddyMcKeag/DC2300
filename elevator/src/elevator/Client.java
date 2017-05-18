package elevator;
import java.util.Random;

public class Client extends Visitor {
	
	public Client(int personId, Random rand) {
		super(personId, rand);
		this.priority = true;
		this.timeToLeave = (rand.nextInt(20) + 10) * 6;
		this.currentDestination = rand.nextInt(this.halfFloorFormula());
		this.call();
	}
	
	public void countWaitTimer() {
		super.countWaitTimer();
		if (this.waitTimer == 60) { 
			this.complain();
			this.leave();
		}
	}
	
	public void complain() {
		Building.addComplaint();
	}
	
}
