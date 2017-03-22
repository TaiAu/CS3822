package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.SetValueListener;
import model.SudokuPuzzle;

/**
 * The Sudoku Panel class contains methods for creating a panel and painting its
 * components.
 * 
 * @author Tai
 *
 */
public class SudokuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private SudokuFrame sudokuFrame;

	private SudokuPuzzle sudokuPuzzle;

	/**
	 * The SudokuPanel constructor.
	 * 
	 * @param frame
	 *            the Sudoku frame
	 * @param model
	 *            the Sudoku puzzle
	 */
	public SudokuPanel(SudokuFrame frame, SudokuPuzzle model) {
		this.sudokuFrame = frame;
		this.sudokuPuzzle = model;
		createPartControl();
	}

	/**
	 * Creates a panel and sets its dimensions.
	 */
	private void createPartControl() {
		new JPanel();
		int width = sudokuPuzzle.getGUIDimension() * sudokuPuzzle.getSudokuDimension() + 1;
		addMouseListener(new SetValueListener(sudokuFrame, sudokuPuzzle));
		setPreferredSize(new Dimension(width, width));
	}

	/**
	 * Paints the graphical components of the Sudoku Panel.
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		sudokuPuzzle.draw(g);
	}
}
