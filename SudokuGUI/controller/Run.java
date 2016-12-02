package controller;

import javax.swing.SwingUtilities;

import model.SudokuPuzzle;
import view.SudokuFrame;

/**
 * Run this class to run the Sudoku solver program.
 * 
 * @author Tai
 */
public class Run implements Runnable {

	/**
	 * The run method creates a new Sudoku Frame.
	 */
	public void run() {
		new SudokuFrame(new SudokuPuzzle());
	}

	/**
	 * The main method of the class.
	 * 
	 * @param args
	 *            the console arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Run());
	}
}
