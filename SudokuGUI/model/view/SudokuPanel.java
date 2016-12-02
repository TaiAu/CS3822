package view;

import controller.SetValueListener;
import model.SudokuPuzzle;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;

/**
 * The Sudoku Panel for the View contains methods for creating a panel and
 * painting components.
 * 
 * @author Tai
 *
 */
public class SudokuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private SudokuFrame sudokuFrame;
	private SudokuPuzzle sudokuPuzzle;

	public SudokuPanel(SudokuFrame sudokuFrame, SudokuPuzzle sudokuPuzzle) {
		this.sudokuFrame = sudokuFrame;
		this.sudokuPuzzle = sudokuPuzzle;
		createPanel();
	}

	private void createPanel() {
		new JPanel();
		int dimension = sudokuPuzzle.getGUIDimension() * sudokuPuzzle.getSudokuDimension() + 1;
		setPreferredSize(new Dimension(dimension, dimension));
		addMouseListener(new SetValueListener(sudokuFrame, sudokuPuzzle));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		sudokuPuzzle.draw(g);
	}
}
