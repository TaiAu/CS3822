package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;
import controller.PrintListener;
import controller.ToggleListener;
import model.PuzzleArchiver;
import model.PuzzleFactory;
import model.SudokuPuzzle;

/**
 * The ButtonPanel class contains the buttons responsible for performing the
 * Sudoku solver program's functions.
 * 
 * @author Tai
 */
public class ButtonPanel {

	protected static final Insets buttonInsets = new Insets(1, 10, 2, 10);

	private boolean isFirstPress;

	private JToggleButton solvePuzzleButton;

	private JToggleButton hintButton;
	private JToggleButton hideButton;

	private JToggleButton clearGridButton;

	private JToggleButton symmetricalPuzzleButton;
	private JToggleButton fiendishPuzzleButton;

	private JToggleButton easyRandomPuzzleButton;
	private JToggleButton mediumRandomPuzzleButton;
	private JToggleButton hardRandomPuzzleButton;
	private JToggleButton randomRandomPuzzleButton;

	private JToggleButton savePuzzleButton;
	private JToggleButton loadPuzzleButton;

	private JButton printButton;

	private JToggleButton lockGridButton;

	private JPanel panel;

	private SudokuFrame sudokuFrame;

	private SudokuPuzzle sudokuPuzzle;

	private PuzzleFactory puzzleFactory = new PuzzleFactory();

	private PuzzleArchiver puzzleArchiver = new PuzzleArchiver();

	/**
	 * The ButtonPanel constructor.
	 * 
	 * @param sudokuFrame
	 *            the sudokuFrame
	 * @param sudokuPuzzle
	 *            the sudokuPuzzle
	 */
	public ButtonPanel(SudokuFrame sudokuFrame, SudokuPuzzle sudokuPuzzle) {
		this.sudokuFrame = sudokuFrame;
		this.sudokuPuzzle = sudokuPuzzle;
		this.isFirstPress = true;
		createPartControl();
	}

	/**
	 * Creates the buttons panel, where all the buttons are contained.
	 */
	private void createPartControl() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		int button = 0;

		ToggleListener tListener = new ToggleListener();

		// =======================================================================================================================

		ImageIcon solveImage = new ImageIcon("src/skeletonkey.png");
		solvePuzzleButton = new JToggleButton("Solve Puzzle", solveImage);
		solvePuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		solvePuzzleButton.addChangeListener(tListener);
		solvePuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (isFirstPress && solvePuzzleButton.isSelected()) {
					Controller runnable = new Controller(sudokuFrame, sudokuPuzzle);
					new Thread(runnable).start();
					isFirstPress = false;
				}
			}
		});
		addComponent(panel, solvePuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon clearIcon = new ImageIcon("src/cleargrid.png");
		clearGridButton = new JToggleButton("Clear Grid", clearIcon);
		clearGridButton.setHorizontalAlignment(SwingConstants.LEFT);
		clearGridButton.addChangeListener(tListener);
		clearGridButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (clearGridButton.isSelected()) {
					isFirstPress = true;
					sudokuPuzzle.initialiseSudokuGrid();
					sudokuFrame.repaintSudokuPanel();
					clearGridButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, clearGridButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon hintIcon = new ImageIcon("src/lightbulb.png");
		hintButton = new JToggleButton("Show Hints", hintIcon);
		hintButton.setHorizontalAlignment(SwingConstants.LEFT);
		hintButton.addChangeListener(tListener);
		hintButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				// for all sudoku cells
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						// get next sudoku cell
						SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(j, i));
						sudokuCell.setIsHintButtonPressed(false);
						sudokuFrame.repaintSudokuPanel();
					}
				}
				hintButton.setSelected(false);
				lockGridButton.setSelected(true);
			}
		});
		addComponent(panel, hintButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		ImageIcon hideIcon = new ImageIcon("src/offlightbulb.png");
		hideButton = new JToggleButton("Hide Hints", hideIcon);
		hideButton.setHorizontalAlignment(SwingConstants.LEFT);
		hideButton.addChangeListener(tListener);
		hideButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				// for all sudoku cells
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						// get next sudoku cell
						SudokuCell sudokuCell = sudokuPuzzle.getSudokuCell(new Point(j, i));
						sudokuCell.setIsHintButtonPressed(true);
						sudokuFrame.repaintSudokuPanel();
					}
				}
				hideButton.setSelected(false);
				lockGridButton.setSelected(true);
			}
		});
		addComponent(panel, hideButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon easyIcon = new ImageIcon("src/bronzestar.png");
		easyRandomPuzzleButton = new JToggleButton("Easy New Puzzle", easyIcon);
		easyRandomPuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		easyRandomPuzzleButton.addChangeListener(tListener);
		easyRandomPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (easyRandomPuzzleButton.isSelected()) {
					isFirstPress = true;
					puzzleFactory.createEasyPuzzle(sudokuPuzzle, sudokuFrame);
					easyRandomPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, easyRandomPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================
		
		ImageIcon mediumIcon = new ImageIcon("src/silverstar.png");
		mediumRandomPuzzleButton = new JToggleButton("Medium New Puzzle", mediumIcon);
		mediumRandomPuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		mediumRandomPuzzleButton.addChangeListener(tListener);
		mediumRandomPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (mediumRandomPuzzleButton.isSelected()) {
					isFirstPress = true;
					puzzleFactory.createMediumPuzzle(sudokuPuzzle, sudokuFrame);
					mediumRandomPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, mediumRandomPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon hardIcon = new ImageIcon("src/goldstar.png");
		hardRandomPuzzleButton = new JToggleButton("Hard New Puzzle", hardIcon);
		hardRandomPuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		hardRandomPuzzleButton.addChangeListener(tListener);
		hardRandomPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (hardRandomPuzzleButton.isSelected()) {
					isFirstPress = true;
					puzzleFactory.createHardPuzzle(sudokuPuzzle, sudokuFrame);
					hardRandomPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, hardRandomPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon randomIcon = new ImageIcon("src/random.png");
		randomRandomPuzzleButton = new JToggleButton("Random Puzzle", randomIcon);
		randomRandomPuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		randomRandomPuzzleButton.addChangeListener(tListener);
		randomRandomPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (randomRandomPuzzleButton.isSelected()) {
					isFirstPress = true;
					puzzleFactory.createRandomPuzzle(sudokuPuzzle, sudokuFrame);
					randomRandomPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, randomRandomPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon fiendishIcon = new ImageIcon("src/devil.png");
		fiendishPuzzleButton = new JToggleButton(" Fiendish Puzzle", fiendishIcon);
		fiendishPuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		fiendishPuzzleButton.addChangeListener(tListener);
		fiendishPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (fiendishPuzzleButton.isSelected()) {
					isFirstPress = true;
					puzzleFactory.loadFiendishPuzzle(sudokuPuzzle, sudokuFrame);
					fiendishPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, fiendishPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon symmetryIcon = new ImageIcon("src/yinyang.png");
		symmetricalPuzzleButton = new JToggleButton("Symmetrical Puzzle", symmetryIcon);
		symmetricalPuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		symmetricalPuzzleButton.addChangeListener(tListener);
		symmetricalPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (symmetricalPuzzleButton.isSelected()) {
					isFirstPress = true;
					puzzleFactory.loadSymmetricalPuzzle(sudokuPuzzle, sudokuFrame);
					symmetricalPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, symmetricalPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon saveIcon = new ImageIcon("src/floppydisk.png");
		savePuzzleButton = new JToggleButton(" Save Puzzle", saveIcon);
		savePuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		savePuzzleButton.addChangeListener(tListener);
		savePuzzleButton.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				if (savePuzzleButton.isSelected()) {
					puzzleArchiver.savePuzzle(sudokuPuzzle);
					savePuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, savePuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon loadIcon = new ImageIcon("src/openfolder.png");
		loadPuzzleButton = new JToggleButton("Load Puzzle", loadIcon);
		loadPuzzleButton.setHorizontalAlignment(SwingConstants.LEFT);
		loadPuzzleButton.addChangeListener(tListener);
		loadPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (loadPuzzleButton.isSelected()) {
					isFirstPress = true;
					puzzleArchiver.loadPuzzle(sudokuPuzzle, sudokuFrame);
					loadPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, loadPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// ================================================================================

		ImageIcon printIcon = new ImageIcon("src/printer.png");
		printButton = new JButton("Print Puzzle", printIcon);
		printButton.setHorizontalAlignment(SwingConstants.LEFT);
		printButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				new Thread(new PrintListener(sudokuFrame)).start();
			}
		});
		addComponent(panel, printButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		ImageIcon lockIcon = new ImageIcon("src/lock.png");
		lockGridButton = new JToggleButton("Lock Grid", lockIcon);
		lockGridButton.setHorizontalAlignment(SwingConstants.LEFT);
		lockGridButton.addChangeListener(tListener);
		lockGridButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				sudokuPuzzle.setSetValues(lockGridButton.isSelected());
			}
		});
		addComponent(panel, lockGridButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		tListener.setToggleButtons(solvePuzzleButton, hintButton, hideButton, clearGridButton, symmetricalPuzzleButton,
				fiendishPuzzleButton, easyRandomPuzzleButton, mediumRandomPuzzleButton, hardRandomPuzzleButton,
				randomRandomPuzzleButton, savePuzzleButton, loadPuzzleButton, lockGridButton);

		lockGridButton.setSelected(true);
	}

	/**
	 * Adds the buttons, specifying the container, component, dimensions and
	 * orientation.
	 * 
	 * @param container
	 *            the container
	 * @param component
	 *            the grid component
	 * @param gridx
	 *            the grid x coordinates
	 * @param gridy
	 *            the grid y coordates
	 * @param gridwidth
	 *            the grid width
	 * @param gridheight
	 *            the grid height
	 * @param insets
	 *            the button insets
	 * @param anchor
	 *            where the line starts
	 * @param fill
	 *            vertical or horizontal
	 */
	private void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, Insets insets, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0D, 1.0D, anchor, fill,
				insets, 0, 0);
		container.add(component, gbc);
	}

	/**
	 * Gets the buttons panel.
	 * 
	 * @return the buttons panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Checks whether the hint button is pressed.
	 * 
	 * @param hintButton
	 *            the hint button
	 * @return true if the hint button is pressed and false otherwise
	 */
	public boolean isHintButtonPressed(JToggleButton hintButton) {
		if (hintButton.isSelected()) {
			return true;
		}
		return false;
	}
}
