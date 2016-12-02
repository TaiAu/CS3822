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
 * The Sudoku Frame in charge of initialising the panel.
 * 
 * @author Tai
 */
public class SudokuFrame {

	private ButtonPanel buttonPanel;
	private JFrame frame;
	private SudokuPanel sudokuPanel;
	private SudokuPuzzle sudokuPuzzle;

	/**
	 * The SudokuFrame constructor.
	 * 
	 * @param sudokuPuzzle
	 *            the puzzle
	 */
	public SudokuFrame(SudokuPuzzle sudokuPuzzle) {
		this.sudokuPuzzle = sudokuPuzzle;
		initialisePanel();
	}

	/**
	 * Gets the Frame.
	 * 
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Gets the Sudoku Panel.
	 * 
	 * @return
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

	/**
	 * The initialisePanel method creates and initialises the Sudoku and Button
	 * Panels with JFrames for each.
	 */
	private void initialisePanel() {
		buttonPanel = new ButtonPanel(this, sudokuPuzzle);
		sudokuPanel = new SudokuPanel(this, sudokuPuzzle);

		frame = new JFrame();
		frame.setTitle("Sudoku Solver");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

		JPanel mainPane = new JPanel();
		mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.X_AXIS));
		mainPane.add(sudokuPanel);

		JPanel outerPane = new JPanel();
		outerPane.setLayout(new FlowLayout());
		outerPane.add(buttonPanel.getPanel());
		mainPane.add(outerPane);

		frame.setLayout(new FlowLayout());
		frame.add(mainPane);
		frame.pack();
		frame.setBounds(getBounds());
		frame.setVisible(true);
	}

	/**
	 * Closes the frame.
	 */
	public void exitProcedure() {
		frame.dispose();
		System.exit(0);
	}

	/**
	 * Gets the bounds of the graphics object.
	 * 
	 * @return the x and y bounds
	 */
	protected Rectangle getBounds() {
		Rectangle rectangleFrame = frame.getBounds();
		Rectangle w = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		rectangleFrame.x = (w.width - rectangleFrame.width) / 2;
		rectangleFrame.y = (w.height - rectangleFrame.height) / 2;
		return rectangleFrame;
	}
}
