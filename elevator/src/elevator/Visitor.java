package elevator;
import java.util.Random;

public abstract class Visitor extends Person {
	
	//timer to keep track of how long the visitor has been completing their task
	protected int timeInBuilding;
	//stores the time until the visitor leaves
	protected int timeToLeave;
	
	public Visitor(int personId, Random rand) {
		super(personId, rand);
		this.timeInBuilding = 0;
	}
	
	//counts up the timer to the point where the visitor leaves
	public int addToTimer() {
		if (this.timeInBuilding < this.timeToLeave) {
			this.timeInBuilding++;
		} else
			this.leave();
		return this.timeInBuilding;
	}
	
	public int getTimeToLeave() { 
		return this.timeToLeave;
	}
	
}
