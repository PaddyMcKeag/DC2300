package controller;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Enhanced version of standard Swing JSlider widget
 *
 * @author Ian T. Nabney
 * @version 13/03/2005
 */


public class LabelledSlider extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6511929653284188062L;
	private String labelString;
	private JLabel label;
	private JSlider slider;

	public LabelledSlider(String text, int min, int max, int value) {
		this.setDoubleBuffered(true);
		label = new JLabel(text + value);
		labelString = new String(text);
		slider = new JSlider(min, max, value);

		// Set slider properties
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(new SliderListener());

		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(slider, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createEtchedBorder());

	}

	public void setMajorTickSpacing(int spacing) {
		slider.setMajorTickSpacing(spacing);
		repaint();
	}

	public int getValue() {
		return slider.getValue();
	}

	private class SliderListener implements ChangeListener {

		public void stateChanged(ChangeEvent e) {
			if (!slider.getValueIsAdjusting()) {
				int number = slider.getValue();
				label.setText(labelString + number);
			}
		}
	}
}
