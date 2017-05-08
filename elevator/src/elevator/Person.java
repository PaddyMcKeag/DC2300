package elevator;

public interface Person {
	
	//calls static method in Lift that adds the person's current floor to the list of
	//destinations
	public void call();
	
	//returns the person's desired destination. If not current floor, they call the elevator?
	public Floor getDestination();
	
}
