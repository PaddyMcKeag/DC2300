package controller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class SetupGUI{

	private Model model;
	private JFrame frame;
	private JLabel intro;
	private JLabel placeholder;
	private LabelledSlider floorSlider;
	private LabelledSlider elevatorCapacitySlider;
	private LabelledSlider runTimeSlider;
	private LabelledSlider employeeSlider;
	private LabelledSlider gogglesDeveloperSlider;
	private LabelledSlider mugtomeDeveloperSlider;
	private LabelledSlider randomSeedSlider;
	private JSpinner changeFloorSpinner;
	private JSpinner clientArrivalSpinner;
	//private JFormattedTextField changeFloorChance;
	//private JFormattedTextField clientArrivalChance;
	private JButton run;

	public Model getModel(){
		return model;
	}

	public SetupGUI(){
		model = new Model();
		frame = new JFrame("setup");
		intro = new JLabel("Welcome to the elevator simulator, choose your simulation parameters.");
		placeholder = new JLabel("");
		floorSlider = new LabelledSlider("Number of floors: ", 3, 15, 6);
		elevatorCapacitySlider = new LabelledSlider("Elevator capacity: ", 4, 20, 4);
		runTimeSlider = new LabelledSlider("Select simulation run time in ticks (1 tick = 10 seconds): ", 300, 6000, 2880);
		employeeSlider = new LabelledSlider("Number of employees: ", 0, 50, 10);
		gogglesDeveloperSlider = new LabelledSlider("Select number of goggles developers: ", 0, 20, 5);
		mugtomeDeveloperSlider = new LabelledSlider("Number of Mugtome developers: ", 0, 20, 5);
		randomSeedSlider = new LabelledSlider("Random seed: ", 1, 10, 1);
		SpinnerNumberModel changeFloorModel = new SpinnerNumberModel(0.001, 0.000, 1, 0.001);
		changeFloorSpinner = new JSpinner(changeFloorModel);
		SpinnerNumberModel clientArrivalModel = new SpinnerNumberModel(0.002, 0.000, 1, 0.002);
		clientArrivalSpinner = new JSpinner(clientArrivalModel);
		run = new JButton("Run");

		// Set properties
		JSpinner.NumberEditor changeFloorEditor = (JSpinner.NumberEditor)changeFloorSpinner.getEditor();
		changeFloorEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		changeFloorEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		changeFloorEditor.setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0,
				0, 0, 0), "Select the chance of an employee changing floor per tick"), changeFloorEditor.getBorder()));

		JSpinner.NumberEditor clientArrivalEditor = (JSpinner.NumberEditor)clientArrivalSpinner.getEditor();
		clientArrivalEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		clientArrivalEditor.setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0,
				0, 0, 0), "Select the chance that a client will arrive per tick"), clientArrivalEditor.getBorder()));

		floorSlider.setMajorTickSpacing(1);
		elevatorCapacitySlider.setMajorTickSpacing(2);
		employeeSlider.setMajorTickSpacing(5);
		runTimeSlider.setMajorTickSpacing(500);
		gogglesDeveloperSlider.setMajorTickSpacing(2);
		mugtomeDeveloperSlider.setMajorTickSpacing(2);
		randomSeedSlider.setMajorTickSpacing(1);

		//add components to frame
		frame.setLayout(new GridLayout(6, 1));
		frame.add(intro);
		frame.add(placeholder);
		frame.add(floorSlider);
		frame.add(elevatorCapacitySlider);
		frame.add(runTimeSlider);
		frame.add(employeeSlider);
		frame.add(gogglesDeveloperSlider);
		frame.add(mugtomeDeveloperSlider);
		frame.add(randomSeedSlider);
		frame.add(changeFloorSpinner);
		frame.add(clientArrivalSpinner);
		frame.add(run);
		
		//taken from lecture material
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
		
		//update model from values of gui elements when the run button is clicked
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				model.setRunTime(runTimeSlider.getValue());
				model.setNumberOfFloors(floorSlider.getValue());
				model.setElevatorCapacity(elevatorCapacitySlider.getValue());
				model.setNumberOfEmployees(employeeSlider.getValue());
				model.setNumberOfGoggles(gogglesDeveloperSlider.getValue());
				model.setNumberOfMugtome(mugtomeDeveloperSlider.getValue());
				model.setRandomSeed(randomSeedSlider.getValue());
				model.setChangeFloorChance((double) changeFloorSpinner.getValue());
				model.setClientArrivalChance((double) clientArrivalSpinner.getValue());
				model.setRunFlag(true);
				frame.dispose();
			}
		});


		frame.pack();
		frame.setVisible(true);
	}
	
	public SetupGUI(int runTime, int numberOfFloors, int elevatorCapacity, int employee, int goggles,int mugtome, int seed, double changeFloorChance, double clientArrivalChance){
		model.setRunTime(runTime);
		model.setNumberOfFloors(numberOfFloors);
		model.setElevatorCapacity(elevatorCapacity);
		model.setNumberOfEmployees(employee);
		model.setNumberOfGoggles(goggles);
		model.setNumberOfMugtome(mugtome);
		model.setRandomSeed(seed);
		model.setChangeFloorChance(changeFloorChance);
		model.setClientArrivalChance(clientArrivalChance);
		model.setRunFlag(true);
	}

	
	//taken from lecture material
	private void exitApp(){
		//display confirmation dialog before exiting
		int response = JOptionPane.showConfirmDialog(
				frame, "Do you also wish to terminate the program?","Terminate?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION){
			System.exit(0);
		}
		//Don't quit
	}
}
