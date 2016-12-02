package controller;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

import model.SudokuSolver;
import model.SudokuData;
import model.SudokuStack;
import model.SudokuPuzzle;
import view.SudokuCell;
import view.SudokuFrame;

/**
 * The Controller class responsible for changing the model to update the view
 * according to user actions.
 * 
 * @author Tai
 *
 */
public class Controller implements Runnable {

	private static final boolean test = false;

	private SudokuPuzzle sudokuPuzzle;
	private SudokuFrame sudokuFrame;
	private SudokuStack SudokuStack;
	private int stackCount;

	private Set<SudokuSolver> solveAttempts;

	/**
	 * The Controller constructor.
	 * 
	 * @param sudokuFrame
	 *            the sudoku frame
	 * @param model
	 *            the sudoku puzzle
	 */
	public Controller(SudokuFrame sudokuFrame, SudokuPuzzle sudokuPuzzle) {
		this.sudokuFrame = sudokuFrame;
		this.sudokuPuzzle = sudokuPuzzle;
		this.SudokuStack = new SudokuStack();
		this.solveAttempts = new HashSet<SudokuSolver>();
	}

	/**
	 * The guessCellNumber method guesses a particular sudoku cell's number.
	 * 
	 * @param sudokuCell
	 *            the sudoku cell
	 */
	private void guessCellNumber(SudokuCell sudokuCell) {
		SudokuCell cellCopy = sudokuCell.copy();
		SudokuData puzzleSearchCount = new SudokuData(cellCopy, sudokuPuzzle.getCells());
		int number = puzzleSearchCount.getGuess();
		puzzleSearchCount.addGuess(number);
		SudokuStack.pushStack(puzzleSearchCount);
		stackCount++;
		if (test) {
			System.out.println("stackCount: " + stackCount + ", attempt: " + number);
			System.out.println(puzzleSearchCount);
		}
		SudokuSolver solveAttempt = new SudokuSolver(stackCount, number);
		if (solveAttempts.contains(solveAttempt)) {
			SudokuStack.popStack();
			SudokuStack.popStack();
			stackCount -= 2;
			if (test) {
				System.out.println("stackCount: " + stackCount + ", attempt: " + number);
				System.out.println(puzzleSearchCount);
			}
		} else {
			solveAttempts.add(solveAttempt);
			sudokuCell.setValue(number);
			sudokuPuzzle.removePossibleValue(sudokuCell);
			while (sudokuPuzzle.isInaccurate()) {
				guessAgain();
			}
			repaintSudokuPanel();
		}
	}

	/**
	 * The solveAllCellsWithOnePossibleValue method solves all cells with one
	 * possible value only.
	 */
	private void solveAllCellsWithOnePossibleValue() {
		Point cellCoordinates = sudokuPuzzle.getSinglePossibleNumberCells();
		while (cellCoordinates != null) {
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(cellCoordinates);
			List<?> possibleValuesList = sudokuCell.getPossibleValues();
			int value = (int) possibleValuesList.get(0);
			sudokuCell.setValue(value);
			sudokuPuzzle.removePossibleValue(sudokuCell);
			sudokuCell.clearPossibleValues();
			repaintSudokuPanel();
			cellCoordinates = sudokuPuzzle.getSinglePossibleNumberCells();
		}
	}

	/**
	 * The guessAllCellsWithSingleValueOnly method guesses all cells with a
	 * single value only, similar to the method above.
	 */
	private void guessAllCellsWithSingleValueOnly() {
		Point cellCoordinates = sudokuPuzzle.getSinglePossibleNumberCells();
		while (cellCoordinates != null) {
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(cellCoordinates);
			List<?> list = sudokuCell.getPossibleValues();
			int value = (int) list.get(0);
			sudokuCell.setValue(value);
			sudokuPuzzle.removePossibleValue(sudokuCell);
			repaintSudokuPanel();
			cellCoordinates = sudokuPuzzle.getSinglePossibleNumberCells();
		}
	}

	/**
	 * The guessAgain method performs the guess again if the puzzle search count
	 * is greater than 0 and guess number is great than 0 and decrements the
	 * stack count otherwise. If the puzzle search count is null, then it
	 * returns false.
	 * 
	 * @return true or false
	 */
	private boolean guessAgain() {
		SudokuData puzzleSearchCount = SudokuStack.peekStack();
		if (puzzleSearchCount != null) {
			sudokuPuzzle.setCells(puzzleSearchCount.getSudokuCellCoordinates());
			int number = puzzleSearchCount.getGuess();
			if (test) {
				System.out.println("stackCount: " + stackCount + ", attempt: " + number);
				System.out.println(puzzleSearchCount);
			}
			if (number > 0) {
				SudokuStack.popStack();
				puzzleSearchCount.addGuess(number);
				SudokuCell sudokuCell = puzzleSearchCount.getSudokuCell();
				sudokuPuzzle.setCell(sudokuCell);
				sudokuCell.setValue(number);
				sudokuPuzzle.removePossibleValue(sudokuCell);
				repaintSudokuPanel();
				SudokuStack.pushStack(puzzleSearchCount);
				return true;
			} else {
				SudokuStack.popStack();
				stackCount--;
				if (test) {
					System.out.println("stackCount: " + stackCount + ", attempt: " + number);
					System.out.println(puzzleSearchCount);
				}
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Runs the methods above.
	 */
	@Override
	public void run() {
		stackCount = 0;
		solveAllCellsWithOnePossibleValue();

		while (sudokuPuzzle.isIncomplete()) {
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCellWithLeastNumbers();
			if (sudokuCell != null) {
				guessCellNumber(sudokuCell);
				guessAllCellsWithSingleValueOnly();
			} else if (guessAgain()) {
				guessAllCellsWithSingleValueOnly();
			} else {
				break;
			}
		}
	}

	/**
	 * Repaints the Sudoku Panel.
	 */
	private void repaintSudokuPanel() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				sudokuFrame.repaintSudokuPanel();
			}
		});
	}
}
