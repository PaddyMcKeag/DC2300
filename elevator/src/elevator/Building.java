package elevator;

//the Building class contains a couple of miscellaneous fields
//it's the most intuitive place to store complains for the simulation
//the number of floors is static to make it easy to access, but the building
//should be constructed before the get method is ever called
public class Building {

	public static int complaints;	
	public static int numberOfFloors;
	
	public Building(int numberOfFloors) {
		Building.numberOfFloors = numberOfFloors;
	}
	
	public static int getNumberOfFloors() {
		return numberOfFloors;
	}
	
	public static void addComplaint() {
		complaints++;
	}
	
	public int getComplaints() {
		return complaints;
	}
}
