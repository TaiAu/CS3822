package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import model.SudokuPuzzle;
import view.SudokuCell;
import view.SudokuFrame;

/**
 * The SetValueListener class implements a MouseListener observer and is
 * responsible for registering mouse presses and releases.
 * 
 * @author Tai
 *
 */
public class SetValueListener implements MouseListener {

	private SudokuFrame frame;
	private SudokuPuzzle sudokuPuzzle;

	/**
	 * The SetValueListener constructor.
	 * 
	 * @param frame
	 *            the sudoku frame
	 * @param model
	 *            the sudoku puzzle
	 */
	public SetValueListener(SudokuFrame frame, SudokuPuzzle model) {
		this.frame = frame;
		this.sudokuPuzzle = model;
	}

	/**
	 * The mousePressed method is responsible for registering mouse presses.
	 * 
	 * @param event
	 *            the mouse click action
	 */
	public void mousePressed(MouseEvent event) {
		if (sudokuPuzzle.isSetValues()) {
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCellLocation(event.getPoint());
			if (sudokuCell != null) {
				int value = getValue(sudokuCell);
				if (value > 0) {
					sudokuCell.setValue(value);
					sudokuCell.setIsInitial(true);
					sudokuPuzzle.removePossibleValue(sudokuCell);
					sudokuCell.clearPossibleValues();
					frame.repaintSudokuPanel();
				}
			}
		}
	}

	/**
	 * The getValue method is responsible for getting the value of the Sudoku
	 * cell.
	 * 
	 * @param sudokuCell
	 *            the sudoku cell
	 * @return the value of the cell
	 */
	private int getValue(SudokuCell sudokuCell) {
		int value = 0;
		while (value == 0) {
			String inputValue = JOptionPane.showInputDialog(frame.getFrame(), "Please enter a value from 1 to 9", "",
					JOptionPane.PLAIN_MESSAGE);

			if (inputValue == null) { // Cancel
				return 0;
			}

			try {
				value = Integer.parseInt(inputValue);
				value = testValue(sudokuCell, value);
			} catch (NumberFormatException e) {
				value = 0;
			}
		}
		return value;
	}

	/**
	 * The testValue method is responsible for checking whether a user entered a
	 * number from 1 to 9.
	 * 
	 * @param sudokuCell
	 *            the sudoku cell
	 * @param number
	 *            the user input
	 * @return 0 if not between 1 to 9 and return the user input number
	 *         otherwise
	 */
	private int testValue(SudokuCell sudokuCell, int value) {
		if (value < 1 || value > 9) {
			value = 0;
		} else if (!sudokuCell.isPossibleValue(value)) {
			value = 0;
		}
		return value;
	}

	/**
	 * Unused generic method
	 */
	public void mouseClicked(MouseEvent event) {
	}
	
	/**
	 * Unused generic method
	 */
	public void mouseReleased(MouseEvent event) {
	}
	
	/**
	 * Unused generic method
	 */
	public void mouseEntered(MouseEvent event) {
	}

	/**
	 * Unused generic method
	 */
	public void mouseExited(MouseEvent event) {
	}
}
