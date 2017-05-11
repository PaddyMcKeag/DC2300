package controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import elevator.LabelledSlider;

public class SetupGUI{

	private SetupFoo model;
	private JFrame frame;
	private JLabel intro;
	private LabelledSlider floorSlider;
	private LabelledSlider elevatorCapacitySlider;
	private LabelledSlider runTimeSlider;
	private LabelledSlider employeeSlider;
	private LabelledSlider gogglesDeveloperSlider;
	private LabelledSlider mugtomeDeveloperSlider;
	private JSpinner changeFloorSpinner;
	private JSpinner clientArrivalSpinner;
	//private JFormattedTextField changeFloorChance;
	//private JFormattedTextField clientArrivalChance;
	private JButton run;

	public SetupFoo getModel(){
		return model;
	}

	public SetupGUI(){
		createFrame();
	}

	private void createFrame(){
		model = new SetupFoo();
		frame = new JFrame("setup");
		intro = new JLabel("Welcome to the elevator simulator, choose your simulation parameters.");
		floorSlider = new LabelledSlider("Number of floors: ", 1, 10, 1);
		elevatorCapacitySlider = new LabelledSlider("Elevator capacity: ", 4, 20, 4);
		runTimeSlider = new LabelledSlider("Select simulation run time in ticks (1 tick = 10 seconds): ", 300, 3000, 300);
		employeeSlider = new LabelledSlider("Number of employees: ", 0, 50, 0);
		gogglesDeveloperSlider = new LabelledSlider("Select number of goggles developers: ", 0, 20, 0);
		mugtomeDeveloperSlider = new LabelledSlider("Number of Mugtome developers: ", 0, 20, 0);
		run = new JButton("Run");

		// Set properties
		SpinnerNumberModel changeFloorModel = new SpinnerNumberModel(0.001, 0.000, 1, 0.001);
		changeFloorSpinner = new JSpinner(changeFloorModel);
		JSpinner.NumberEditor changeFloorEditor = (JSpinner.NumberEditor)changeFloorSpinner.getEditor();
		changeFloorEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		changeFloorEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		changeFloorEditor.setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0,
				0, 0, 0), "Select the chance of an employee changing floor per tick"), changeFloorEditor.getBorder()));

		SpinnerNumberModel clientArrivalModel = new SpinnerNumberModel(0.001, 0.000, 1, 0.001);
		clientArrivalSpinner = new JSpinner(clientArrivalModel);
		JSpinner.NumberEditor clientArrivalEditor = (JSpinner.NumberEditor)clientArrivalSpinner.getEditor();
		clientArrivalEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		clientArrivalEditor.setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0,
				0, 0, 0), "Select the chance that a client will arrive per tick"), clientArrivalEditor.getBorder()));

		floorSlider.setMajorTickSpacing(1);
		elevatorCapacitySlider.setMajorTickSpacing(2);
		employeeSlider.setMajorTickSpacing(5);
		runTimeSlider.setMajorTickSpacing(300);
		gogglesDeveloperSlider.setMajorTickSpacing(2);
		mugtomeDeveloperSlider.setMajorTickSpacing(2);

		//add components to frame
		frame.setLayout(new GridLayout(10, 1));
		frame.add(intro);
		frame.add(floorSlider);
		frame.add(elevatorCapacitySlider);
		frame.add(runTimeSlider);
		frame.add(employeeSlider);
		frame.add(gogglesDeveloperSlider);
		frame.add(mugtomeDeveloperSlider);
		frame.add(changeFloorSpinner);
		frame.add(clientArrivalSpinner);

		frame.add(run);
		//frame.add();


		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				model.setRunTime(runTimeSlider.getValue());
				model.setNumberOfFloors(floorSlider.getValue());
				model.setElevatorCapacity(elevatorCapacitySlider.getValue());
				model.setNumberOfEmployees(employeeSlider.getValue());
				model.setNumberOfGoggles(gogglesDeveloperSlider.getValue());
				model.setNumberOfMugtome(mugtomeDeveloperSlider.getValue());
				model.setChangeFloorChance((double) changeFloorSpinner.getValue());
				model.setClientArrivalChance((double) clientArrivalSpinner.getValue());
				model.setRunFlag(true);
				System.out.print("Model is " + model);
			}
		});


		frame.pack();
		frame.setVisible(true);
	}

}
