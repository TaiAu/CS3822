package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.SudokuCell;

/**
 * The SudokuData class contains methods for handling Sudoku data.
 * 
 * @author Tai
 *
 */
public class SudokuData {

	private List<Integer> solveAttempts;
	private Random random;
	private SudokuCell sudokuCell;
	private SudokuCell[][] coordinates;

	public SudokuData(SudokuCell sudokuCell, SudokuCell[][] coordinates) {
		this.solveAttempts = new ArrayList<Integer>();
		this.sudokuCell = sudokuCell;
		this.coordinates = coordinates;
		this.random = new Random();
	}

	/**
	 * Adds a solve attempt.
	 * 
	 * @param solveAttempt
	 */
	public void addGuess(int solveAttempt) {
		this.solveAttempts.add(solveAttempt);
	}

	/**
	 * Gets the possible values of a sudoku cell.
	 * 
	 * @return the possible numbers of a sudoku cell
	 */
	public List<Integer> getPossibleValues() {
		return sudokuCell.getPossibleValues();
	}

	/**
	 * Gets a sudoku cell.
	 * 
	 * @return the sudoku cell
	 */
	public SudokuCell getSudokuCell() {
		return sudokuCell;
	}

	/**
	 * Gets the sudoku cell coordinates
	 * 
	 * @return the sudoku cell coordinates
	 */
	public SudokuCell[][] getSudokuCellCoordinates() {
		return coordinates;
	}

	/**
	 * Gets the solve attempts remaining for a sudoku cell.
	 * 
	 * @return the solve attempts remaining for a sudoku cell
	 */
	public boolean solveAttemptsRemaining() {
		return solveAttempts.size() < sudokuCell.getPossibleValuesCount();
	}

	/**
	 * Gets the guess
	 * @return
	 */
	public int getGuess() {
		List<Object> list = new ArrayList<Object>();
		for (Object number : sudokuCell.getPossibleValues()) {
			list.add(number);
		}
		for (Object number : solveAttempts) {
			list.remove(number);
		}
		if (list.size() >= 1) {
			int index = random.nextInt(list.size());
			return (int) list.get(index);
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Puzzle position guesses: ");
		for (int i = 0; i < solveAttempts.size(); i++) {
			builder.append(solveAttempts.get(i));
			if (i < (solveAttempts.size() - 1)) {
				builder.append(", ");
			}
		}
		builder.append("; ");
		builder.append(sudokuCell);
		return builder.toString();
	}
}
