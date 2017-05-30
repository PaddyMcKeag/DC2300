package elevator;
import java.util.Random;

public class Crew extends Visitor {

	public Crew(int personId, Random rand) {
		super(personId, rand);
		this.size = 4;
		this.timeToLeave = (rand.nextInt(20) + 20) * 6;
		this.currentDestination = Building.getNumberOfFloors() - 1;
		call();
	}

}
