package elevator;

public abstract class Visitor extends Person {
	
	//timer to keep track of how long the visitor has been completing their task
	protected int timeInBuilding;
	//stores the time until the visitor leaves
	protected int timeToLeave;
	
	public Visitor(int personId, long seed) {
		super(personId, seed);
		timeInBuilding = 0;
	}
	
	//counts up the timer to the point where the visitor leaves
	public int addToTimer() {
		if (timeInBuilding < timeToLeave) {
			timeInBuilding++;
		} else
			this.leave();
		return timeInBuilding;
	}
}
