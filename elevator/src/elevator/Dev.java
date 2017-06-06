package elevator;
import java.util.Random; 

public class Dev extends Worker {

	//boolean for company to determine which devs can work together
	private boolean company;
	
	//sets up the rand and determines starting destination
	public Dev(int id, Random rand, boolean company) {
		super(id, rand);
		this.currentDestination = rand.nextInt(this.halfFloorFormula()) + this.halfFloorFormula() - 1;
		this.company = company;
		this.call();
	}
	
	
	//should be called by the run-time every tick
	//cannot change their mind while waiting for the lift
	public int determineDestination() {
		return rand.nextInt(this.halfFloorFormula()) + this.halfFloorFormula() - 1;
	}
	

	public boolean getCompany() {
		return this.company;
	}
	
}
