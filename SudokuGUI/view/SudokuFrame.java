package view;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.SudokuPuzzle;

/**
 * The Sudoku Frame responsible for initialising the Sudoku panel.
 * 
 * @author Tai
 */
public class SudokuFrame {

	private ButtonPanel buttonPanel;
	private JFrame sudokuFrame;
	private SudokuPanel sudokuPanel;
	private SudokuPuzzle model;

	/**
	 * The SudokuFrame constructor.
	 * 
	 * @param sudokuPuzzle
	 *            the puzzle
	 */
	public SudokuFrame(SudokuPuzzle model) {
		this.model = model;
		createPartControl();
	}

	/**
	 * Creates and initialises the Sudoku and Button Panels with JFrames for
	 * each.
	 */
	private void createPartControl() {
		sudokuPanel = new SudokuPanel(this, model);
		buttonPanel = new ButtonPanel(this, model);

		sudokuFrame = new JFrame();
		sudokuFrame.setTitle("Sudoku Puzzle Solver");
		sudokuFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		sudokuFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		mainPanel.add(sudokuPanel);

		JPanel holderPanel = new JPanel();
		holderPanel.setLayout(new FlowLayout());
		holderPanel.add(buttonPanel.getPanel());
		mainPanel.add(holderPanel);

		sudokuFrame.setLayout(new FlowLayout());
		sudokuFrame.add(mainPanel);
		sudokuFrame.pack();
		sudokuFrame.setBounds(getBounds());
		sudokuFrame.setVisible(true);
	}

	/**
	 * Closes the frame.
	 */
	public void exitProcedure() {
		sudokuFrame.dispose();
		System.exit(0);
	}

	/**
	 * Gets the bounds of the graphical object.
	 * 
	 * @return the x and y bounds
	 */
	protected Rectangle getBounds() {
		Rectangle f = sudokuFrame.getBounds();
		Rectangle w = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		f.x = (w.width - f.width) / 2;
		f.y = (w.height - f.height) / 2;
		return f;
	}

	/**
	 * Gets the Frame.
	 * 
	 * @return the sudoku frame
	 */
	public JFrame getFrame() {
		return sudokuFrame;
	}

	/**
	 * Gets the Sudoku Panel.
	 * 
	 * @return the sudoku panel
	 */
	public JPanel getSudokuPanel() {
		return sudokuPanel;
	}

	/**
	 * Repaints the Sudoku Panel.
	 */
	public void repaintSudokuPanel() {
		sudokuPanel.repaint();
	}
}
