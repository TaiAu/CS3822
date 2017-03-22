package controller;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
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
	 * The main method of the Run class.
	 * 
	 * @param args
	 *            the console arguments
	 */
	public static void main(String[] args) {

		JWindow window = new JWindow();
		window.getContentPane().add(new JLabel("", new ImageIcon("src/Sudoku.gif"), SwingConstants.CENTER));
		window.setBounds(600, 300, 300, 300);
		window.setVisible(true);
		try {
			Thread.sleep(3800);
		} catch (InterruptedException e) {
		}
		window.dispose();

		SwingUtilities.invokeLater(new Run());
	}
}
