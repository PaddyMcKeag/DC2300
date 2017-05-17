package controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import elevator.Lift;
import elevator.Person;



public class SimulationGui {
	private JFrame simFrame;
	private ArrayList<DefaultListModel<String>> building;
	private ArrayList<JList<String>> floors;
	private JList<String> elevator;
	private JLabel runTimeLabel;
	private DefaultListModel<String> elevatorList;
	private int currentTime;
	private int runTime;
	
	public SimulationGui(int numberOfFloors, int runTime, int elevatorCapacity){
		this.runTime = runTime;
		currentTime = 0;
		building = new ArrayList<DefaultListModel<String>>();
		floors = new ArrayList<JList<String>>();
		simFrame = new JFrame("setup");		
	    simFrame.dispatchEvent(new WindowEvent(simFrame, WindowEvent.WINDOW_CLOSING));
	    runTimeLabel = new JLabel("<html>Current run time: " + currentTime + "<br> Simulation will end at: " + runTime +"<html>");
	    elevatorList = new DefaultListModel<String>();
	    elevatorList.addElement("Elevator (capacity: " + elevatorCapacity + ") (location: " + "ground" + " floor)");
		elevator = new JList<String>(elevatorList);
		
		elevator.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		elevator.setSelectedIndex(0);
		elevator.setVisibleRowCount(3); 
	    
		//creates number of floors = to input param and adds one string to use as floor label, then adds to arraylist to store 
		for(int i=0;i<numberOfFloors;i++){
			DefaultListModel<String> model = new DefaultListModel<String>();			
			building.add(model);
		}
		
		//adds floor names to model
		populateFloorNames();
		
		System.out.println("about to add floors");
		for(int i=building.size()-1; i>=0; i--){
			System.out.println("adding floor");
			JList<String> floor = new JList<String>(building.get(i));
			floor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			floor.setSelectedIndex(0);
			floor.setVisibleRowCount(3); 
		    
			floors.add(floor);
			simFrame.add(new JScrollPane(floor));
			if(i==building.size()-1){
				//adds runtime label to top right of frame
				simFrame.add(runTimeLabel);
			} else if(i==0){
				//add elevator to bottom left of frame
				simFrame.add(new JScrollPane(elevator));
			}else{
				//adds placeholder to keep floors on left side
				simFrame.add(new JLabel(""));
			}
		}
		
		
		System.out.print("finished adding floors");
		
		simFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
		
		simFrame.setLayout(new GridLayout(numberOfFloors, 2));
		simFrame.pack();
		simFrame.setVisible(true);	}
	
	public void populateFloorNamesAndLift(){
		for (int i=0; i<building.size();i++){
			if (i==0){
				building.get(i).addElement("Ground Floor");
			}else{
				building.get(i).addElement("Floor " + i);
			}
		}
		
			
	}
	
	
	//taken from lecture material
		private void exitApp(){
			//display confirmation dialog before exiting
			int response = JOptionPane.showConfirmDialog(
					simFrame, "Do you also wish to terminate the program?","Terminate?",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION){
				System.exit(0);
			}
			//Don't quit
		}
		
		public void simTick(ArrayList<Person> people,Lift lift,int currentTime){
			runTimeLabel.setText("<html>Current run time: " + currentTime + "<br> Simulation will end at: " + this.runTime +"<html>");
			
			//clears each floor and elevator
			elevatorList.clear();
			for(int i=0;i<building.size();i++){
				building.get(i).clear();
			}
			
			//adds floor names to model
			populateFloorNamesAndLift();
			
			//populates floors and elevators
			for (int i=0;i<people.size();i++){
				if (lift.isInLift(people.get(i))){
					elevatorList.addElement(Integer.toString(people.get(i).getPersonId()));
				}else{
					int currentFloor = people.get(i).getCurrentFloor();
					building.get(currentFloor).addElement(Integer.toString(people.get(i).getPersonId()));
				}
			}
		}
}
