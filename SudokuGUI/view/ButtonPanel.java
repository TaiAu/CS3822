package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Controller;
import controller.ToggleListener;
import model.SudokuPuzzle;

/**
 * The ButtonPanel class contains the buttons responsible for performing the
 * Sudoku solver program's functions.
 * 
 * @author Tai
 */
public class ButtonPanel {

	protected static final Insets buttonInsets = new Insets(10, 10, 20, 10);

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

	private JToggleButton savePuzzleButton;
	private JToggleButton loadPuzzleButton;

	private JToggleButton lockGridButton;

	private JPanel panel;

	private SudokuFrame sudokuFrame;

	private SudokuPuzzle sudokuPuzzle;

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
	 * Creates the buttons panel.
	 */
	private void createPartControl() {
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		int button = 0;

		ToggleListener tListener = new ToggleListener();

		// =======================================================================================================================

		solvePuzzleButton = new JToggleButton("Solve Puzzle");
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

		clearGridButton = new JToggleButton("Clear Grid");
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

		hintButton = new JToggleButton("Show Hints");
		hintButton.addChangeListener(tListener);
		hintButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				// sudokuPuzzle.setHintValues(lockGridButton.isSelected());

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

		hideButton = new JToggleButton("Hide Hints");
		hideButton.addChangeListener(tListener);
		hideButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				// sudokuPuzzle.setHintValues(lockGridButton.isSelected());

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

		easyRandomPuzzleButton = new JToggleButton("Easy Random Puzzle");
		easyRandomPuzzleButton.addChangeListener(tListener);
		easyRandomPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (easyRandomPuzzleButton.isSelected()) {
					isFirstPress = true;

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
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77) + " ");
							} else if (i == 0 && j == 1 || i == 1 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78) + " ");
							} else if (i == 0 && j < 9 || i == 1 && j == 1 || i >= 2 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79) + " ");
							} else if (i == 1 && j < 9 || i >= 2 && j == 1) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80) + " ");
							} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
							}
						}
					}
					System.out.println(sb);

					try {
						PrintWriter saver = new PrintWriter(new FileWriter("src/RandomPuzzles.txt"));
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
					easyRandomPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, easyRandomPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		mediumRandomPuzzleButton = new JToggleButton("Medium Random Puzzle");
		mediumRandomPuzzleButton.addChangeListener(tListener);
		mediumRandomPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (mediumRandomPuzzleButton.isSelected()) {
					isFirstPress = true;

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
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77) + " ");
							} else if (i == 0 && j == 1 || i == 1 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78) + " ");
							} else if (i == 0 && j < 9 || i == 1 && j == 1 || i >= 2 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79) + " ");
							} else if (i == 1 && j < 9 || i >= 2 && j == 1) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80) + " ");
							} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
							}
						}
					}
					System.out.println(sb);

					try {
						PrintWriter saver = new PrintWriter(new FileWriter("src/RandomPuzzles.txt"));
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
					mediumRandomPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, mediumRandomPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		hardRandomPuzzleButton = new JToggleButton("Hard Random Puzzle");
		hardRandomPuzzleButton.addChangeListener(tListener);
		hardRandomPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (hardRandomPuzzleButton.isSelected()) {
					isFirstPress = true;

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
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77) + " ");
							} else if (i == 0 && j == 1 || i == 1 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78) + " ");
							} else if (i == 0 && j < 9 || i == 1 && j == 1 || i >= 2 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79) + " ");
							} else if (i == 1 && j < 9 || i >= 2 && j == 1) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80) + " ");
							} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
							}
						}
					}
					System.out.println(sb);

					try {
						PrintWriter saver = new PrintWriter(new FileWriter("src/RandomPuzzles.txt"));
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
					hardRandomPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, hardRandomPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		fiendishPuzzleButton = new JToggleButton("Fiendish Puzzle");
		fiendishPuzzleButton.addChangeListener(tListener);
		fiendishPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (fiendishPuzzleButton.isSelected()) {
					isFirstPress = true;

					sudokuPuzzle.initialiseSudokuGrid();
					sudokuFrame.repaintSudokuPanel();

					System.out.println("loading...");

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
					fiendishPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, fiendishPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		symmetricalPuzzleButton = new JToggleButton("Symmetrical Puzzle");
		symmetricalPuzzleButton.addChangeListener(tListener);
		symmetricalPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (symmetricalPuzzleButton.isSelected()) {
					isFirstPress = true;

					sudokuPuzzle.initialiseSudokuGrid();
					sudokuFrame.repaintSudokuPanel();

					System.out.println("loading...");

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
					symmetricalPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, symmetricalPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		savePuzzleButton = new JToggleButton("Save Puzzle");
		savePuzzleButton.addChangeListener(tListener);
		savePuzzleButton.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {
				if (savePuzzleButton.isSelected()) {
					System.out.println("saving...");

					StringBuilder sb = new StringBuilder();

					// print to console and save columns left to right
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {

							if (i == 0 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77) + " ");
								System.out.print(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(76, 77));
							} else if (i == 0 && j == 1 || i == 1 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78) + " ");
								System.out.print(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(77, 78));
							} else if (i == 0 && j < 9 || i == 1 && j == 1 || i >= 2 && j == 0) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79) + " ");
								System.out.print(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(78, 79));
							} else if (i == 1 && j < 9 || i >= 2 && j == 1) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80) + " ");
								System.out.print(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(79, 80));
							} else if (i == 8 && j == 8) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
								System.out
										.print(sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81)
												+ "\n");
							} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
								sb.append(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
								System.out.print(
										sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81));
							}
						}
					}

					try {
						PrintWriter saver = new PrintWriter(new FileWriter("src/savedPuzzle.txt"));
						saver.print(sb);
						saver.close();
					} catch (IOException e) {
						System.out.println("file not found!");
					}

					savePuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, savePuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		loadPuzzleButton = new JToggleButton("Load Puzzle");
		loadPuzzleButton.addChangeListener(tListener);
		loadPuzzleButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (loadPuzzleButton.isSelected()) {
					isFirstPress = true;

					sudokuPuzzle.initialiseSudokuGrid();

					System.out.println("loading...");

					Scanner scanner = null;
					try {
						scanner = new Scanner(new File("src/savedPuzzle.txt"));
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
					loadPuzzleButton.setSelected(false);
					lockGridButton.setSelected(true);
				}
			}
		});
		addComponent(panel, loadPuzzleButton, 0, button++, 1, 1, buttonInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// =======================================================================================================================

		lockGridButton = new JToggleButton("Lock Grid");
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
				savePuzzleButton, loadPuzzleButton, lockGridButton);

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
