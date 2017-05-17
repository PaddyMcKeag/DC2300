package elevator;

public class Dev extends Worker {

	//boolean for company to determine which devs can work together
	private boolean company;
	
	//sets up the rand and determines starting destination
	public Dev(int id, long seed, boolean company) {
		super(id, seed);
		this.currentDestination = rand.nextInt((((Building.getNumberOfFloors() - 1) * 10) / 2) 
				+ ((Building.getNumberOfFloors() * 10) / 2) + 1) / 10;  
		this.company = company;
		this.call();
	}
	
	
	//should be called by the run-time every tick
	//cannot change their mind while waiting for the lift
	public void changeDestination(double probability) {
		if (!waiting) {
			if (rand.nextDouble() < probability) {
				currentDestination = rand.nextInt(Building.getNumberOfFloors() / 2) + Building.getNumberOfFloors();  
			}
			
			this.call();
		}
	}

	public boolean getCompany() {
		return company;
	}
	
}
