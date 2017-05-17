package controller;
import elevator.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

	private static SimulationGui simGui;
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
	private static int totalWaitTime; 
	
	public static void main(String[] args) {
		
		setUpGUI();
		setUpModel();
		startSim();
		hometime();
		endSim();
		
	}
	
	private static void setUpGUI() {
		gui = new SetupGUI();
		while(!gui.getModel().getRunFlag()) {
			System.out.println("loop");
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
		System.out.println("hi");
		simGui = new SimulationGui(numberOfFloors,runTime,elevatorCapacity);
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
			simGui.simTick(people, lift, runTime);
			addCrew();
			addClient();
			peopleTick();
			timer++;
		}
	}
	
	private static void peopleTick() {
		for (Person person : people) {
			if (person.getCurrentDestination() == person.getCurrentFloor() && person instanceof Visitor) {
				((Visitor) person).addToTimer();
			} else if (person instanceof Worker) {
				((Worker) person).changeDestination(changeFloorChance);
			} 
			if (person.getCurrentDestination() != person.getCurrentDestination() && !lift.isInLift(person)) {
				person.countWaitTimer();
			}
		}
	}
	
	private static void hometime() {
		//when everyone's destination is their location, end
	}
	
	private static void endSim() {
		totalWaitTime = 0;
		for (Person person : people) {
			totalWaitTime += person.getWaitTimer();
		}
		
		System.out.println("Simulation ended.");
		System.out.println("The parameters were:");
		System.out.println("Run time = " + runTime);
		System.out.println("Number of floors = " + numberOfFloors);
		System.out.println("Elevator capacity = " + elevatorCapacity);
		System.out.println("Number of normal employees = " + numberOfEmployees);
		System.out.println("Number of Goggle developers = " + numberOfGoggles);
		System.out.println("Number of Mugtome developers = " + numberOfMugtome);
		System.out.println("Probability for workers to change floors = " + changeFloorChance);
		System.out.println("The chance for a client to arrive = " + clientArrivalChance);
		System.out.println("The chance for a maintenance crew to arrive = " + crewArrivalChance);
		System.out.println("The seed used to generate random numbers = " + seed);
		System.out.println("");
		System.out.println("This resulted in " + building.getComplaints() + " complains from clients waiting too long.");
		System.out.println("There were " + lift.getPeopleTransported() + " people transported.");
		System.out.println("The average wait time was " + ((double)totalWaitTime / lift.getPeopleTransported()));
	}
	
}
