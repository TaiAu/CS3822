package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Container;
import java.awt.Component;
import java.awt.Insets;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JToggleButton;
import javax.swing.JPanel;

import controller.ToggleListener;
import controller.Controller;
import model.SudokuPuzzle;

public class ButtonPanel {

	private JPanel pane;
	private boolean isFirstPress;
	private SudokuFrame sudokuFrame;
	private SudokuPuzzle sudokuPuzzle;
	protected static final Insets buttonBorders = new Insets(8, 8, 8, 8);

	private JToggleButton solveButton;
	private JToggleButton solveCellButton;
	private JToggleButton hintButton;

	private JToggleButton lockButton;
	private JToggleButton undoButton;
	private JToggleButton clearCellButton;
	private JToggleButton clearGridButton;

	private JToggleButton easyButton;
	private JToggleButton mediumButton;
	private JToggleButton hardButton;
	private JToggleButton impossibleButton;
	private JToggleButton randomButton;
	
	private JToggleButton saveButton;
	private JToggleButton loadButton;
	
	private JToggleButton quitButton;

	private JToggleButton infoButton;

	public ButtonPanel(SudokuFrame sudokuFrame, SudokuPuzzle sudokuPuzzle) {
		this.sudokuFrame = sudokuFrame;
		this.sudokuPuzzle = sudokuPuzzle;
		this.isFirstPress = true;
		createGUIButtons();
	}

	public JPanel getPanel() {
		return pane;
	}

	private void addJComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, Insets insets, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0D, 1.0D, anchor, fill,
				insets, 0, 0);
		container.add(component, gbc);
	}

	private void createGUIButtons() {
		pane = new JPanel();
		pane.setLayout(new GridBagLayout());

		int grid = 0;

		ToggleListener toggleListener = new ToggleListener();

		solveButton = new JToggleButton("Solve Puzzle");
		solveButton.addChangeListener(toggleListener);
		solveButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (isFirstPress && solveButton.isSelected()) {
					Controller runnable = new Controller(sudokuFrame, sudokuPuzzle);
					new Thread(runnable).start();
					isFirstPress = false;
				}
			}
		});
		addJComponent(pane, solveButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		solveCellButton = new JToggleButton("Solve Cell");
		solveCellButton.addChangeListener(toggleListener);

		addJComponent(pane, solveCellButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		hintButton = new JToggleButton("Hint");
		hintButton.addChangeListener(toggleListener);

		addJComponent(pane, hintButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		undoButton = new JToggleButton("Undo");
		undoButton.addChangeListener(toggleListener);

		addJComponent(pane, undoButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		clearCellButton = new JToggleButton("Clear Cell");
		clearCellButton.addChangeListener(toggleListener);

		addJComponent(pane, clearCellButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		clearGridButton = new JToggleButton("Clear Grid");
		clearGridButton.addChangeListener(toggleListener);
		clearGridButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (clearGridButton.isSelected()) {
					isFirstPress = true;
					sudokuPuzzle.initialiseSudokuGrid();
					sudokuFrame.repaintSudokuPanel();
					clearGridButton.setSelected(false);
					lockButton.setSelected(true);
				}
			}
		});
		addJComponent(pane, clearGridButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		easyButton = new JToggleButton("Easy Puzzle");
		easyButton.addChangeListener(toggleListener);

		addJComponent(pane, easyButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		mediumButton = new JToggleButton("Medium Puzzle");
		mediumButton.addChangeListener(toggleListener);

		addJComponent(pane, mediumButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		hardButton = new JToggleButton("Hard Puzzle");
		hardButton.addChangeListener(toggleListener);

		addJComponent(pane, hardButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		impossibleButton = new JToggleButton("Impossible Puzzle");
		impossibleButton.addChangeListener(toggleListener);

		addJComponent(pane, impossibleButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		randomButton = new JToggleButton("Random Puzzle");
		randomButton.addChangeListener(toggleListener);

		addJComponent(pane, randomButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		saveButton = new JToggleButton("Save Puzzle");
		saveButton.addChangeListener(toggleListener);

		addJComponent(pane, saveButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		// TODO
		loadButton = new JToggleButton("Load Puzzle");
		loadButton.addChangeListener(toggleListener);

		addJComponent(pane, loadButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);
		
		// TODO
		infoButton = new JToggleButton("Info");
		infoButton.addChangeListener(toggleListener);

		addJComponent(pane, infoButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);
		
		// TODO
		quitButton = new JToggleButton("Quit");
		quitButton.addChangeListener(toggleListener);

		addJComponent(pane, quitButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);
		
		lockButton = new JToggleButton("Lock Grid");
		lockButton.addChangeListener(toggleListener);
		lockButton.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				sudokuPuzzle.lockInValues(lockButton.isSelected());
			}
		});
		addJComponent(pane, lockButton, 0, grid++, 1, 1, buttonBorders, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		toggleListener.configureButtons(solveButton, solveCellButton, hintButton, undoButton, clearCellButton,
				clearGridButton, saveButton, loadButton, easyButton, mediumButton, hardButton, impossibleButton,
				randomButton, infoButton, lockButton);

		lockButton.setSelected(true);
	}
}
