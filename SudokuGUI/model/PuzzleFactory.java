package model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import controller.Controller;
import view.SudokuCell;
import view.SudokuFrame;

/**
 * The PuzzleFactory class contains methods responsible for generating random
 * puzzles of different difficulties.
 * 
 * @author Tai
 */
public class PuzzleFactory {

	/**
	 * Creates a randomly generated Sudoku puzzle of easy difficulty.
	 * 
	 * @param sudokuPuzzle
	 *            the Sudoku puzzle
	 * @param sudokuFrame
	 *            the Sudoku frame
	 */
	public void createEasyPuzzle(SudokuPuzzle sudokuPuzzle, SudokuFrame sudokuFrame) {
		sudokuPuzzle.initialiseSudokuGrid();

		Controller runWithoutRepaint = new Controller(sudokuFrame, sudokuPuzzle);
		runWithoutRepaint.runWithoutRepaint();

		// 20 removals
		for (int i = 0; i < 20; i++) {
			Random generator = new Random();
			int x = generator.nextInt(9);
			int y = generator.nextInt(9);

			// Get Cell and set max possible values
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(x, y));
			sudokuCell.initialise(9);

			sudokuFrame.repaintSudokuPanel();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == 0 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77) + " ");
				} else if (i == 0 && j == 1 || i == 1 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78) + " ");
				} else if (i == 0 && j < 9 || i == 1 && j == 1 || i >= 2 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79) + " ");
				} else if (i == 1 && j < 9 || i >= 2 && j == 1) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80) + " ");
				} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
				}
			}
		}
		System.out.println(sb);

		try {
			PrintWriter saver = new PrintWriter(new FileWriter("src/easyPuzzles.txt"));
			saver.print(sb);
			saver.close();
		} catch (IOException e) {
			System.out.println("file not found!");
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/easyPuzzles.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("file not found!");
		}

		// for all sudoku cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// scan for next int
				int nextValue = scanner.nextInt();

				if (nextValue != 0) {
					// get next sudoku cell
					SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(j, i));
					// set values
					sudokuCell.setValue(nextValue);
					sudokuCell.setIsInitial(true);
					sudokuPuzzle.removePossibleValue(sudokuCell);
					sudokuCell.clearPossibleValues();
					sudokuFrame.repaintSudokuPanel();
				}
			}
		}
	}

	/**
	 * Creates a randomly generated Sudoku puzzle of medium difficulty.
	 * 
	 * @param sudokuPuzzle
	 *            the Sudoku puzzle
	 * @param sudokuFrame
	 *            the Sudoku frame
	 */
	public void createMediumPuzzle(SudokuPuzzle sudokuPuzzle, SudokuFrame sudokuFrame) {
		sudokuPuzzle.initialiseSudokuGrid();

		Controller runWithoutRepaint = new Controller(sudokuFrame, sudokuPuzzle);
		runWithoutRepaint.runWithoutRepaint();

		// 40 removals
		for (int i = 0; i < 40; i++) {
			Random generator = new Random();
			int x = generator.nextInt(9);
			int y = generator.nextInt(9);

			// Get Cell and set max possible values
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(x, y));
			sudokuCell.initialise(9);

			sudokuFrame.repaintSudokuPanel();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == 0 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77) + " ");
				} else if (i == 0 && j == 1 || i == 1 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78) + " ");
				} else if (i == 0 && j < 9 || i == 1 && j == 1 || i >= 2 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79) + " ");
				} else if (i == 1 && j < 9 || i >= 2 && j == 1) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80) + " ");
				} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
				}
			}
		}
		System.out.println(sb);

		try {
			PrintWriter saver = new PrintWriter(new FileWriter("src/mediumPuzzles.txt"));
			saver.print(sb);
			saver.close();
		} catch (IOException e) {
			System.out.println("file not found!");
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/mediumPuzzles.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("file not found!");
		}

		// for all sudoku cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// scan for next int
				int nextValue = scanner.nextInt();

				if (nextValue != 0) {
					// get next sudoku cell
					SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(j, i));
					// set values
					sudokuCell.setValue(nextValue);
					sudokuCell.setIsInitial(true);
					sudokuPuzzle.removePossibleValue(sudokuCell);
					sudokuCell.clearPossibleValues();
					sudokuFrame.repaintSudokuPanel();
				}
			}
		}
	}

	/**
	 * Creates a randomly generated Sudoku puzzle of hard difficulty.
	 * 
	 * @param sudokuPuzzle
	 *            the Sudoku puzzle
	 * @param sudokuFrame
	 *            the Sudoku frame
	 */
	public void createHardPuzzle(SudokuPuzzle sudokuPuzzle, SudokuFrame sudokuFrame) {
		sudokuPuzzle.initialiseSudokuGrid();

		Controller runWithoutRepaint = new Controller(sudokuFrame, sudokuPuzzle);
		runWithoutRepaint.runWithoutRepaint();

		// 60 removals
		for (int i = 0; i < 60; i++) {
			Random generator = new Random();
			int x = generator.nextInt(9);
			int y = generator.nextInt(9);

			// Get Cell and set max possible values
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(x, y));
			sudokuCell.initialise(9);

			sudokuFrame.repaintSudokuPanel();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == 0 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77) + " ");
				} else if (i == 0 && j == 1 || i == 1 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78) + " ");
				} else if (i == 0 && j < 9 || i == 1 && j == 1 || i >= 2 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79) + " ");
				} else if (i == 1 && j < 9 || i >= 2 && j == 1) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80) + " ");
				} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
				}
			}
		}
		System.out.println(sb);

		try {
			PrintWriter saver = new PrintWriter(new FileWriter("src/hardPuzzles.txt"));
			saver.print(sb);
			saver.close();
		} catch (IOException e) {
			System.out.println("file not found!");
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/hardPuzzles.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("file not found!");
		}

		// for all sudoku cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// scan for next int
				int nextValue = scanner.nextInt();

				if (nextValue != 0) {
					// get next sudoku cell
					SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(j, i));
					// set values
					sudokuCell.setValue(nextValue);
					sudokuCell.setIsInitial(true);
					sudokuPuzzle.removePossibleValue(sudokuCell);
					sudokuCell.clearPossibleValues();
					sudokuFrame.repaintSudokuPanel();
				}
			}
		}
	}

	/**
	 * Creates a randomly generated Sudoku puzzle of random difficulty.
	 * 
	 * @param sudokuPuzzle
	 *            the Sudoku puzzle
	 * @param sudokuFrame
	 *            the Sudoku frame
	 */
	public void createRandomPuzzle(SudokuPuzzle sudokuPuzzle, SudokuFrame sudokuFrame) {
		sudokuPuzzle.initialiseSudokuGrid();

		Controller runWithoutRepaint = new Controller(sudokuFrame, sudokuPuzzle);
		runWithoutRepaint.runWithoutRepaint();

		// 20-60 removals
		int RNG = ThreadLocalRandom.current().nextInt(20, 61);
		for (int i = 0; i < RNG; i++) {
			Random generator = new Random();
			int x = generator.nextInt(9);
			int y = generator.nextInt(9);

			// Get Cell and set max possible values
			SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(x, y));
			sudokuCell.initialise(9);

			sudokuFrame.repaintSudokuPanel();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (i == 0 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77) + " ");
				} else if (i == 0 && j == 1 || i == 1 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78) + " ");
				} else if (i == 0 && j < 9 || i == 1 && j == 1 || i >= 2 && j == 0) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79) + " ");
				} else if (i == 1 && j < 9 || i >= 2 && j == 1) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80) + " ");
				} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
					sb.append(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
				}
			}
		}
		System.out.println(sb);

		try {
			PrintWriter saver = new PrintWriter(new FileWriter("src/randomPuzzles.txt"));
			saver.print(sb);
			saver.close();
		} catch (IOException e) {
			System.out.println("file not found!");
		}

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/randomPuzzles.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("file not found!");
		}

		// for all sudoku cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// scan for next int
				int nextValue = scanner.nextInt();

				if (nextValue != 0) {
					// get next sudoku cell
					SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(j, i));
					// set values
					sudokuCell.setValue(nextValue);
					sudokuCell.setIsInitial(true);
					sudokuPuzzle.removePossibleValue(sudokuCell);
					sudokuCell.clearPossibleValues();
					sudokuFrame.repaintSudokuPanel();
				}
			}
		}
	}

	/**
	 * Loads a Sudoku puzzle of fiendish difficulty.
	 * 
	 * @param sudokuPuzzle
	 *            the Sudoku puzzle
	 * @param sudokuFrame
	 *            the Sudoku frame
	 */
	public void loadFiendishPuzzle(SudokuPuzzle sudokuPuzzle, SudokuFrame sudokuFrame) {
		sudokuPuzzle.initialiseSudokuGrid();
		sudokuFrame.repaintSudokuPanel();

		System.out.println("loading...");

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/extremePuzzle.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("file not found!");
		}

		// for all sudoku cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// scan for next int
				int nextValue = scanner.nextInt();

				if (nextValue != 0) {
					// get next sudoku cell
					SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(j, i));
					// set values
					sudokuCell.setValue(nextValue);
					sudokuCell.setIsInitial(true);
					sudokuPuzzle.removePossibleValue(sudokuCell);
					sudokuCell.clearPossibleValues();
					sudokuFrame.repaintSudokuPanel();
				}
			}
		}
	}

	/**
	 * Loads a symmetrical Sudoku puzzle.
	 * 
	 * @param sudokuPuzzle
	 *            the Sudoku puzzle
	 * @param sudokuFrame
	 *            the Sudoku frame
	 */
	public void loadSymmetricalPuzzle(SudokuPuzzle sudokuPuzzle, SudokuFrame sudokuFrame) {
		sudokuPuzzle.initialiseSudokuGrid();
		sudokuFrame.repaintSudokuPanel();

		System.out.println("loading...");

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("src/symmetricalPuzzle.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("file not found!");
		}

		// for all sudoku cells
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// scan for next int
				int nextValue = scanner.nextInt();

				if (nextValue != 0) {
					// get next sudoku cell
					SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(j, i));
					// set values
					sudokuCell.setValue(nextValue);
					sudokuCell.setIsInitial(true);
					sudokuPuzzle.removePossibleValue(sudokuCell);
					sudokuCell.clearPossibleValues();
					sudokuFrame.repaintSudokuPanel();
				}
			}
		}
	}

}
