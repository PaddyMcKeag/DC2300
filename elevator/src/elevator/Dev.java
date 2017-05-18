package elevator;
import java.util.Random; 

public class Dev extends Worker {

	//boolean for company to determine which devs can work together
	private boolean company;
	
	//sets up the rand and determines starting destination
	public Dev(int id, Random rand, boolean company) {
		super(id, rand);
		this.currentDestination = rand.nextInt(this.halfFloorFormula()) + this.halfFloorFormula();
		this.company = company;
		this.call();
	}
	
	
	//should be called by the run-time every tick
	//cannot change their mind while waiting for the lift
	public void changeDestination(double probability) {
		if (!waiting) {
			if (rand.nextDouble() < probability) {
				currentDestination = rand.nextInt(this.halfFloorFormula()) + this.halfFloorFormula();
				if (this.currentDestination != this.currentFloor) {
					this.call();
				}
			}
			
		}
	}
	

	public boolean getCompany() {
		return company;
	}
	
}
