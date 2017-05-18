package elevator;

public class Crew extends Visitor {

	public Crew(int personId) {
		super(personId);
		this.size = 4;
		this.timeToLeave = (rand.nextInt(20) + 20) * 6;
		this.currentDestination = Building.getNumberOfFloors();
		call();
	}

}
