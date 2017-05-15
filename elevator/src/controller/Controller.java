package controller;
import elevator.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

	private static SetupGUI gui;
	private static Lift lift;
	private static ArrayList<Person> people;
	private static Building building;
	
	private static final Random RAND = new Random();
	
	private static int runTime;
	private static int numberOfFloors;
	private static int elevatorCapacity;
	private static int numberOfEmployees;
	private static int numberOfGoggles;
	private static int numberOfMugtome;
	private static double changeFloorChance;
	private static double clientArrivalChance;
	private static final double crewArrivalChance = 0.005;
	private static int currentPersonId;
	private static long seed;
	
	public static void main(String[] args) {
		
		setUpGUI();
		setUpModel();
		startSim();
		
	}
	
	private static void setUpGUI() {
		gui = new SetupGUI();
	
		while(!gui.getModel().getRunFlag()) {
		}
		
		runTime = gui.getModel().getRunTime();
		numberOfFloors = gui.getModel().getNumberOfFloors();
		elevatorCapacity = gui.getModel().getElevatorCapacity();
		numberOfEmployees = gui.getModel().getNumberOfEmployees();
		numberOfGoggles = gui.getModel().getNumberOfGoggles();
		numberOfMugtome = gui.getModel().getNumberOfMugtome();
		changeFloorChance = gui.getModel().getChangeFloorChance();
		clientArrivalChance = gui.getModel().getClientArrivalChance();
		seed = gui.getModel().getRandomSeed();
	}
	
	private static void setUpModel() {
		lift = new Lift(elevatorCapacity);
		building = new Building(numberOfFloors);
		people = new ArrayList<Person>();
		
		currentPersonId = 0;
		
		for (int i = 0; i < numberOfEmployees; i++) {
			Employee employee = new Employee(currentPersonId, seed);
			people.add(employee);
			currentPersonId++;
		}
		
		for (int i = 0; i < numberOfGoggles; i++) {
			Dev dev = new Dev(currentPersonId++, seed, false);
			people.add(dev);
			currentPersonId++;
		}
		
		for (int i = 0; i < numberOfMugtome; i++) {
			Dev dev = new Dev(currentPersonId++, seed, true);
			people.add(dev);
			currentPersonId++;
		}
	}
	
	private static void addCrew() {
		if (RAND.nextDouble() < crewArrivalChance) {
			Crew crew = new Crew(currentPersonId, seed);
			people.add(crew);
			currentPersonId++;
		}
	}
	
	private static void addClient() {
		if (RAND.nextDouble() < clientArrivalChance) {
			Client client = new Client(currentPersonId, seed);
			people.add(client);
			currentPersonId++;
		}
	}
	
	public static ArrayList<Person> getPeople() {
		ArrayList<Person> toReturn = new ArrayList<Person>();
		for (int i = 0; i < people.size(); i++) {
			toReturn.add(people.get(i));
		}
		return toReturn;
	}
	
	public static int getLiftFloor() {
		return lift.getCurrentFloor();
	}
	
	private static void startSim() {
		int timer = 0;
		while(timer < runTime) {
			lift.tick();
			gui.tick();
			addCrew();
			addClient();
			for (Person person : people) {
				if (person instanceof Worker) {
					person.changeDestination(changeFloorChance);
				}
			}
		}
	}
	
}
