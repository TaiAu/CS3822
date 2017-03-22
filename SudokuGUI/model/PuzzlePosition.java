package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.SudokuCell;

/**
 * The PuzzlePosition class contains methods for handling the Sudoku puzzle
 * data.
 * 
 * @author Tai
 *
 */
public class PuzzlePosition {

	private List<Integer> guesses;

	private Random random;

	private SudokuCell sudokuCell;

	private SudokuCell[][] position;

	/**
	 * The PuzzlePosition constructor.
	 * 
	 * @param sudokuCell
	 *            the sudoku cell
	 * @param position
	 *            the coordinate position of the sudoku cell
	 */
	public PuzzlePosition(SudokuCell sudokuCell, SudokuCell[][] position) {
		this.guesses = new ArrayList<Integer>();
		this.sudokuCell = sudokuCell;
		this.position = position;
		this.random = new Random();
	}

	/**
	 * The addGuess method adds a solve attempt.
	 * 
	 * @param solveAttempt
	 */
	public void addGuess(int guess) {
		this.guesses.add(guess);
	}

	/**
	 * The getSudokuCell method returns the Sudoku cell.
	 * 
	 * @return the sudoku cell
	 */
	public SudokuCell getSudokuCell() {
		return sudokuCell;
	}

	/**
	 * The getPosition method returns the sudoku cell coordinates
	 * 
	 * @return the sudoku cell coordinates
	 */
	public SudokuCell[][] getPosition() {
		return position;
	}

	/**
	 * The getPossibleValues method returns the possible values of a sudoku
	 * cell.
	 * 
	 * @return the possible number values of a sudoku cell
	 */
	public List<Integer> getPossibleValues() {
		return sudokuCell.getPossibleValues();
	}

	/**
	 * The guessesLeft method returns the solve attempts remaining for a sudoku
	 * cell.
	 * 
	 * @return the solve attempts remaining for a sudoku cell
	 */
	public boolean guessesLeft() {
		return guesses.size() < sudokuCell.getPossibleValuesCount();
	}

	/**
	 * The getGuess method gets the guess number.
	 * 
	 * @return the guess number
	 */
	public int getGuess() {
		List<Object> list = new ArrayList<Object>();
		for (Object number : sudokuCell.getPossibleValues()) {
			list.add(number);
		}
		for (Object number : guesses) {
			list.remove(number);
		}
		if (list.size() >= 1) {
			int index = random.nextInt(list.size());
			return (Integer) list.get(index);
		} else {
			return 0;
		}
	}

	/**
	 * The toString method creates a string containing the sudoku cell and guess
	 * information.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Puzzle position guesses: ");
		for (int i = 0; i < guesses.size(); i++) {
			builder.append(guesses.get(i));
			if (i < (guesses.size() - 1)) {
				builder.append(", ");
			}
		}
		builder.append("; ");
		builder.append(sudokuCell);
		return builder.toString();
	}
}
