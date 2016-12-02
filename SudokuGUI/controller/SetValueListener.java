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

	private SudokuFrame sudokuFrame;
	private SudokuPuzzle sudokuPuzzle;

	/**
	 * The SetValueListener constructor.
	 * 
	 * @param frame
	 *            the sudoku frame
	 * @param model
	 *            the sudoku puzzle
	 */
	public SetValueListener(SudokuFrame sudokuFrame, SudokuPuzzle sudokuPuzzle) {
		this.sudokuFrame = sudokuFrame;
		this.sudokuPuzzle = sudokuPuzzle;
	}

	/**
	 * The mousePressed method is responsible for registering mouse presses.
	 * 
	 * @param event
	 *            the mouse click action
	 */
	public void mousePressed(MouseEvent event) {
		if (sudokuPuzzle.isSetValues()) {
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCellCoordinates(event.getPoint());
			if (sudokuCell != null) {
				int value = getValue(sudokuCell);
				if (value > 0) {
					sudokuCell.setValue(value);
					sudokuCell.setIsInitial(true);
					sudokuPuzzle.removePossibleValue(sudokuCell);
					sudokuCell.clearPossibleValues();
					sudokuFrame.repaintSudokuPanel();
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
		int number = 0;
		while (number == 0) {
			String userInput = JOptionPane.showInputDialog(sudokuFrame.getFrame(), "Enter a number from 1 to 9:");
			if (userInput == null) {
				return 0;
			}
			try {
				number = Integer.parseInt(userInput);
				number = checkNumberBounds(sudokuCell, number);
			} catch (NumberFormatException e) {
				number = 0;
			}
		}
		return number;
	}

	/**
	 * The checkNumber method is responsible for checking whether a user entered
	 * a number from 1 to 9.
	 * 
	 * @param sudokuCell
	 *            the sudoku cell
	 * @param number
	 *            the user input
	 * @return 0 if not between 1 to 9 and return the user input number
	 *         otherwise
	 */
	private int checkNumberBounds(SudokuCell sudokuCell, int number) {
		if (number < 1 || number > 9) {
			number = 0;
		} else if (!sudokuCell.isPossibleValue(number)) {
			number = 0;
		}
		return number;
	}

	@Override
	public void mouseReleased(MouseEvent event) {
	}

	@Override
	public void mouseClicked(MouseEvent e) throws UnsupportedOperationException {
	}

	@Override
	public void mouseEntered(MouseEvent e) throws UnsupportedOperationException {
	}

	@Override
	public void mouseExited(MouseEvent e) throws UnsupportedOperationException {
	}
}
