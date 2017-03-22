package controller;

import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The ToggleListener class is responsible for registering state changes and
 * configuring the buttons to the listeners.
 * 
 * @author Tai
 */
public class ToggleListener implements ChangeListener {

	private JToggleButton[] toggleButtons;

	/**
	 * The setButtons method is responsible for adding listeners to the buttons
	 * in the ButtonPanel class.
	 * 
	 * @param buttons
	 */
	public void setToggleButtons(JToggleButton... buttons) {
		toggleButtons = new JToggleButton[buttons.length];
		for (int i = 0; i < buttons.length; i++) {
			toggleButtons[i] = buttons[i];
		}
	}

	/**
	 * The stateChanged method sets the sudoku cell toggle button that has been
	 * pressed to true and false otherwise.
	 */
	public void stateChanged(ChangeEvent event) {
		JToggleButton toggleButton = (JToggleButton) event.getSource();
		if (toggleButton.isSelected()) {
			for (JToggleButton button : toggleButtons) {
				if (!button.equals(toggleButton)) {
					button.setSelected(false);
				}
			}
		}
	}
}
