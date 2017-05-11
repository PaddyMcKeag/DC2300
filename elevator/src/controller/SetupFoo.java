package controller;

public class SetupFoo {
	private int numberOfFloors;
	private int elevatorCapacity;
	private int runTime;
	private int numberOfEmployees;
	private int numberOfGoggles;
	private int numberOfMugtome;
	private double changeFloorChance;
	private double clientArrivalChance;
	private boolean runFlag;
	
	public SetupFoo() {
		numberOfFloors = 1;
		elevatorCapacity = 4;
		runTime = 300;
		numberOfEmployees = 0;
		numberOfGoggles = 0;
		numberOfMugtome = 0;
		changeFloorChance = 0.001;
		clientArrivalChance = 0.001;
	}
	
	public boolean getRunFlag(){
		return runFlag;
	}
	
	public void setRunFlag(boolean x){
		this.runFlag = x;
	}
	
	public int getRunTime() {
		return runTime;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public int getElevatorCapacity() {
		return elevatorCapacity;
	}

	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public int getNumberOfGoggles() {
		return numberOfGoggles;
	}

	public int getNumberOfMugtome() {
		return numberOfMugtome;
	}

	public double getChangeFloorChance() {
		return changeFloorChance;
	}

	public double getClientArrivalChance() {
		return clientArrivalChance;
	}

	public void setNumberOfFloors(int x){
		this.numberOfFloors = x;
	}
	
	public void setElevatorCapacity(int x){
		this.elevatorCapacity = x;
	}

	public void setRunTime(int x){
		this.runTime = x;
	}

	public void setNumberOfEmployees(int x){
		this.numberOfEmployees = x;
	}

	public void setNumberOfGoggles(int x){
		this.numberOfGoggles = x;
	}

	public void setNumberOfMugtome(int x){
		this.numberOfMugtome = x;
	}

	public void setChangeFloorChance(double x){
		this.changeFloorChance = x;
	}

	public void setClientArrivalChance(double x){
		this.clientArrivalChance = x;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("number of floors = "+numberOfFloors+"\n");
		sb.append("elevator capacity = "+elevatorCapacity+"\n");
		sb.append("run time = "+runTime+"\n");
		sb.append("number of employees = "+numberOfEmployees+"\n");
		sb.append("number of google developers = "+numberOfGoggles+"\n");
		sb.append("number of mugtome developers = "+numberOfMugtome+"\n");
		sb.append("change floor chance = "+changeFloorChance+"\n");
		sb.append("client arrival chance = "+clientArrivalChance+"\n");
		return sb.toString();
	}

}
