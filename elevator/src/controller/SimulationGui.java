package controller;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;



public class SimulationGui {
	private JFrame simFrame;
	private JList<String> people;
	private ArrayList<ArrayList> building;
	private ArrayList<JList> floors; 
	
	public SimulationGui(int numberOfFloors, int runTime){
		building = new ArrayList<ArrayList>();
		floors = new ArrayList<JList>();
		simFrame = new JFrame("setup");		
	    simFrame.dispatchEvent(new WindowEvent(simFrame, WindowEvent.WINDOW_CLOSING));
		
		for(int i=0;i<numberOfFloors;i++){
		ArrayList<String> name = new ArrayList<String>();
		name.add("Floor"+i);
			building.add(name);
		}
		
		System.out.println("about to add floors");
		for(int i=building.size()-1; i>=0; i--){
			System.out.println("adding floor");
			JList<String> floor = new JList(building.get(i).toArray());
			floor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			floor.setSelectedIndex(0);
			floor.setVisibleRowCount(3); 
		    
			floors.add(floor);
			simFrame.add(new JScrollPane(floor));
		}
		System.out.print("finished adding floors");
	    for(int i=0;i<building.size();i++){
	    	
	    }
		
		simFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
		
		simFrame.setLayout(new GridLayout(7, 1));
		simFrame.pack();
		simFrame.setVisible(true);	}
	
	private void createFrame(int numberOfFloors, int runTime){
		
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
}
