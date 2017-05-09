package elevator;

public class Dev extends Worker {

	//boolean for company to determine which devs can work together
	private boolean company;
	
	//sets up the rand and determines starting destination
	public Dev(int id, boolean company) {
		super(id);
		this.currentDestination = RAND.nextInt(Building.getNumberOfFloors() / 2) + Building.getNumberOfFloors();  
		this.company = company;
		this.call();
	}
	
	
	//should be called by the run-time every tick
	public void changeDestination(double probability) {
		if (!waiting) {
			if (RAND.nextDouble() < probability) {
				currentDestination = RAND.nextInt(Building.getNumberOfFloors() / 2) + Building.getNumberOfFloors();  
			}
			
			this.call();
		}
	}

	public boolean getCompany() {
		return company;
	}
	
}
