package elevator;

public class Crew extends Visitor {

	public Crew(int personId, long seed) {
		super(personId, seed);
		this.size = 4;
		this.timeToLeave = (rand.nextInt(20) + 20) * 6;
		this.currentDestination = Building.getNumberOfFloors() - 1;
		call();
	}

}
