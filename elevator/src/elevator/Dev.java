/**
 * The Dev class models developers who work in the building. It extends Worker.
 * @author Paddy McKeag
 * @version 1.0
 * @since 2017-05-19
 */

package elevator;
import java.util.Random; 

public class Dev extends Worker {

	//boolean for company to determine which devs can work together
	private boolean company;
	
	/**
	 * Constructor for Dev. The dev will be constructing with a random destination in
	 * the top half of the building, and then call the lift.
	 * @param id The ID of the person.
	 * @param rand The Random object used to generate random number for the system.
	 * @param company The company this specific Dev works for. 
	 */
	public Dev(int id, Random rand, boolean company) {
		super(id, rand);
		this.currentDestination = rand.nextInt(this.halfFloorFormula()) + (Building.getNumberOfFloors() / 2);
		this.company = company;
		this.call();
	}
	
	
	//should be called by the run-time every tick
	//cannot change their mind while waiting for the lift
	/**
	 * {@inheritDoc}
	 */
	public int determineDestination() {
		return rand.nextInt(this.halfFloorFormula()) + this.halfFloorFormula() - 1;
	}
	
	/**
	 * Returns the company that the Dev works for.
	 * @return
	 */
	public boolean getCompany() {
		return this.company;
	}
	
}
