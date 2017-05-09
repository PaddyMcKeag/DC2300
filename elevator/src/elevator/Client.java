package elevator;

public class Client extends Visitor {

	//times how long they've been waiting until they leave
	protected int waitTimer;
	
	public Client(int personId) {
		super(personId);
		this.priority = true;
		this.timeToLeave = (RAND.nextInt(20) + 10) * 6;
		this.currentDestination = RAND.nextInt((Building.getNumberOfFloors() - 1) / 2);
		waitTimer = 0;
		this.call();
	}
	
	public void resetWaitTimer() {
		waitTimer = 0;
	}
	
	public int countWaitTimer() {
		if (!(waitTimer > 600)) {
			waitTimer++;
		}
		else {
			this.complain();
			this.leave();
		}
		return waitTimer;
	}
	
	public void complain() {
		Building.addComplaint();
	}
	
}
