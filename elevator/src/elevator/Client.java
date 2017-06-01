package elevator;
import java.util.Random;

public class Client extends Visitor {
	
	private int currentWaitTimer;
	
	public Client(int personId, Random rand) {
		super(personId, rand);
		this.currentWaitTimer = 0;
		this.priority = true;
		this.timeToLeave = (rand.nextInt(20) + 10) * 6;
		this.currentDestination = rand.nextInt(this.halfFloorFormula());
		if (this.currentDestination != this.currentFloor) {
			this.call();
		}
	}
	
	public void countWaitTimer() {
		super.countWaitTimer();
		this.currentWaitTimer++;
		if (this.currentWaitTimer == 60) { 
			this.complain();
			this.leave();
		}
	}
	
	public void complain() {
		Building.addComplaint();
	}
	
	public void setCurrentFloor(int currentFloor) {
		super.setCurrentFloor(currentFloor);
		this.currentWaitTimer = 0;
	}
	
}
