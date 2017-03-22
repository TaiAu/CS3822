package controller;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.SwingUtilities;

import model.GuessLog;
import model.PuzzlePosition;
import model.PuzzleStack;
import model.SudokuPuzzle;
import view.SudokuCell;
import view.SudokuFrame;

/**
 * The Controller class responsible for changing the model to update the
 * view according to user actions.
 * 
 * @author Tai
 *
 */
public class Controller implements Runnable {

	private static final boolean isError = false;

	private int stackCount;
	private PuzzleStack puzzleStack;
	private Set<GuessLog> guessLog;
	private SudokuFrame frame;
	private SudokuPuzzle sudokuPuzzle;

	/**
	 * The Controller constructor.
	 * 
	 * @param sudokuFrame
	 *            the sudoku frame
	 * @param model
	 *            the sudoku puzzle
	 */
	public Controller(SudokuFrame frame, SudokuPuzzle model) {
		this.frame = frame;
		this.sudokuPuzzle = model;
		this.puzzleStack = new PuzzleStack();
		this.guessLog = new HashSet<GuessLog>();
	}

	/**
	 * Runs the Sudoku puzzle solving method(s) until completion.
	 */
	@Override
	public void run() {
		// long startTime = System.currentTimeMillis();

		// stackCount = 0;
		// singleCount = 0;
		// guessCount = 0;

		solveAllSingleValueCells();

		while (sudokuPuzzle.isIncomplete()) {
			SudokuCell sudokuCell = sudokuPuzzle.getSmallestPossibleValuesList();
			if (sudokuCell != null) {
				guessCellValue(sudokuCell);
				guessAllSingleValueCells();
			} else if (guessCellValueAgain()) {
				guessAllSingleValueCells();
			} else {
				break;
			}
		}

		// long elapsedTime = System.currentTimeMillis() - startTime;
		// new SolutionDialog(frame, singleCount, guessCount, elapsedTime);

	}

	/**
	 * Runs the Sudoku puzzle solving method(s) until completion without
	 * repainting the frame.
	 */
	public void runWithoutRepaint() {
		// long startTime = System.currentTimeMillis();

		// stackCount = 0;
		// singleCount = 0;
		// guessCount = 0;

		solveAllSingleValueCells();

		while (sudokuPuzzle.isIncomplete()) {
			SudokuCell sudokuCell = sudokuPuzzle.getSmallestPossibleValuesList();
			if (sudokuCell != null) {
				guessCellValue(sudokuCell);
				guessAllSingleValueCells();
			} else if (guessCellValueAgain()) {
				guessAllSingleValueCells();
			} else {
				break;
			}
		}

		// long elapsedTime = System.currentTimeMillis() - startTime;
		// new SolutionDialog(frame, singleCount, guessCount, elapsedTime);

	}

	/**
	 * The solveAllSingleValueCells method solves all cells with one possible
	 * value only.
	 */
	private void solveAllSingleValueCells() {
		Point cellPosition = sudokuPuzzle.getSinglePossibleValue();
		while (cellPosition != null) {
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(cellPosition);
			List<?> list = sudokuCell.getPossibleValues();
			int value = (Integer) list.get(0);
			sudokuCell.setValue(value);
			sudokuPuzzle.removePossibleValue(sudokuCell);
			sudokuCell.clearPossibleValues();
			repaintSudokuPanel();
			cellPosition = sudokuPuzzle.getSinglePossibleValue();
		}
	}

	/**
	 * The guessCellValue method guesses a particular sudoku cell's number.
	 * 
	 * @param sudokuCell
	 *            the sudoku cell
	 */
	private void guessCellValue(SudokuCell sudokuCell) {
		SudokuCell copyCell = sudokuCell.copy();
		PuzzlePosition puzzlePosition = new PuzzlePosition(copyCell, sudokuPuzzle.getCells());
		int value = puzzlePosition.getGuess();
		puzzlePosition.addGuess(value);
		puzzleStack.pushStack(puzzlePosition);
		stackCount++;
		if (isError) {
			System.out.println("stack: " + stackCount + ", guess: " + value);
			System.out.println(puzzlePosition);
		}
		GuessLog guessLogEntry = new GuessLog(stackCount, value);
		if (guessLog.contains(guessLogEntry)) {
			puzzleStack.popStack();
			puzzleStack.popStack();
			stackCount -= 2;
			if (isError) {
				System.out.println("stack: " + stackCount + ", guess: " + value);
				System.out.println(puzzlePosition);
			}
		} else {
			guessLog.add(guessLogEntry);
			sudokuCell.setValue(value);
			sudokuPuzzle.removePossibleValue(sudokuCell);
			while (sudokuPuzzle.isInaccurate()) {
				guessCellValueAgain();
			}
			repaintSudokuPanel();
		}
	}

	/**
	 * The guessCellValueAgain method performs the guess again if the puzzle
	 * search count and guess number are greater than 0 and decrements the stack
	 * count otherwise. If the puzzle search count is null, it returns false.
	 * 
	 * @return true or false
	 */
	private boolean guessCellValueAgain() {
		PuzzlePosition puzzlePosition = puzzleStack.peekStack();
		if (puzzlePosition != null) {
			sudokuPuzzle.setCells(puzzlePosition.getPosition());
			int value = puzzlePosition.getGuess();
			if (isError) {
				System.out.println("stack: " + stackCount + ", guess: " + value);
				System.out.println(puzzlePosition);
			}
			if (value > 0) {
				puzzleStack.popStack();
				puzzlePosition.addGuess(value);
				SudokuCell sudokuCell = puzzlePosition.getSudokuCell();
				sudokuPuzzle.setCell(sudokuCell);
				sudokuCell.setValue(value);
				sudokuPuzzle.removePossibleValue(sudokuCell);
				repaintSudokuPanel();
				puzzleStack.pushStack(puzzlePosition);
				return true;
			} else {
				puzzleStack.popStack();
				stackCount--;
				if (isError) {
					System.out.println("stackCount: " + stackCount + ", guess: " + value);
					System.out.println(puzzlePosition);
				}
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * The guessAllSingleValueCells method guesses all cells with a single value
	 * only.
	 */
	private void guessAllSingleValueCells() {
		Point cellPosition = sudokuPuzzle.getSinglePossibleValue();
		while (cellPosition != null) {
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(cellPosition);
			List<?> list = sudokuCell.getPossibleValues();
			int value = (Integer) list.get(0);
			sudokuCell.setValue(value);
			sudokuPuzzle.removePossibleValue(sudokuCell);
			repaintSudokuPanel();
			cellPosition = sudokuPuzzle.getSinglePossibleValue();
		}
	}

	/**
	 * Repaints the Sudoku Panel.
	 */
	private void repaintSudokuPanel() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame.repaintSudokuPanel();
			}
		});
	}
}
