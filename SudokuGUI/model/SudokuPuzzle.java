package model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import view.SudokuCell;

public class SudokuPuzzle {

	private boolean valuesSet;

	private int sudokuGUIDimension;
	private int sudokuDimension;

	private SudokuCell[][] cells;

	/**
	 * The SudokuPuzzle constructor.
	 */
	public SudokuPuzzle() {
		this.sudokuGUIDimension = 80;
		this.sudokuDimension = 9;
		this.cells = new SudokuCell[sudokuDimension][sudokuDimension];
		initialiseSudokuCells(sudokuDimension);
	}

	/**
	 * The Sudoku cell coordinates structure and initialisation. 1, 4 and 7
	 * represents the first rows or columns of each region.
	 */
	private int[][] cellCoordinates = { { 1, 4, 7, 1, 4, 7, 1, 4, 7 }, { 2, 5, 8, 2, 5, 8, 2, 5, 8 },
			{ 3, 6, 9, 3, 6, 9, 3, 6, 9 }, { 1, 4, 7, 1, 4, 7, 1, 4, 7 }, { 2, 5, 8, 2, 5, 8, 2, 5, 8 },
			{ 3, 6, 9, 3, 6, 9, 3, 6, 9 }, { 1, 4, 7, 1, 4, 7, 1, 4, 7 }, { 2, 5, 8, 2, 5, 8, 2, 5, 8 },
			{ 3, 6, 9, 3, 6, 9, 3, 6, 9 } };

	/**
	 * Initialises the sudoku cells depending on the grid dimensions.
	 * 
	 * @param sudokuDimension
	 *            the sudoku grid's dimensions
	 */
	private void initialiseSudokuCells(int sudokuDimension) {
		for (int x = 0; x < sudokuDimension; x++) {
			for (int y = 0; y < sudokuDimension; y++) {
				cells[x][y] = new SudokuCell();
				cells[x][y].setCellCoordinates(new Point(x, y));
			}
		}
	}

	/**
	 * Initialises the Sudoku grid.
	 */
	public void initialiseSudokuGrid() {
		for (int i = 0; i < sudokuDimension; i++) {
			for (int j = 0; j < sudokuDimension; j++) {
				cells[i][j].initialise(sudokuDimension);
			}
		}
	}

	/**
	 * Returns a boolean value indicating whether values are set or not.
	 * 
	 * @return true or false
	 */
	public boolean isSetValues() {
		return valuesSet;
	}

	/**
	 * Sets the set of sudoku cell values so that it cannot be changed in the
	 * GUI.
	 * 
	 * @param valuesSet
	 *            the set of values
	 */
	public void lockInValues(boolean valuesSet) {
		this.valuesSet = valuesSet;
	}

	/**
	 * Gets the sudoku cells.
	 * 
	 * @return the sudoku cells
	 */
	public SudokuCell[][] getCells() {
		SudokuCell[][] cellcopy = new SudokuCell[sudokuDimension][sudokuDimension];
		for (int i = 0; i < sudokuDimension; i++) {
			for (int j = 0; j < sudokuDimension; j++) {
				cellcopy[i][j] = cells[i][j].copy();
			}
		}
		return cellcopy;
	}

	/**
	 * Sets the sudoku cells.
	 * 
	 * @param sudokuCells
	 *            the sudoku cells
	 */
	public void setCells(SudokuCell[][] sudokuCells) {
		for (int i = 0; i < sudokuDimension; i++) {
			for (int j = 0; j < sudokuDimension; j++) {
				this.cells[i][j] = cells[i][j].copy();
			}
		}
	}

	/**
	 * Sets a sudoku cell.
	 * 
	 * @param cell
	 *            a sudoku cell
	 */
	public void setCell(SudokuCell cell) {
		Point point = cell.getCellCoordinates();
		this.cells[point.x][point.y] = cell;
	}

	/**
	 * Gets a GUI dimension.
	 * 
	 * @return the GUI dimension
	 */
	public int getGUIDimension() {
		return sudokuGUIDimension;
	}

	/**
	 * Gets the Sudoku dimension
	 * 
	 * @return the sudoku dimension
	 */
	public int getSudokuDimension() {
		return sudokuDimension;
	}

	/**
	 * Gets a set of Sudoku cell coordinates.
	 * 
	 * @param point
	 * @return
	 */
	public SudokuCell getSudokuCellCoordinates(Point point) {
		for (int i = 0; i < sudokuDimension; i++) {
			for (int j = 0; j < sudokuDimension; j++) {
				if (cells[i][j].contains(point)) {
					return cells[i][j];
				}
			}
		}
		return null;
	}

	/**
	 * Gets the smallest list of possible values.
	 * 
	 * @return the cells containing the smallest list of possible guessable
	 *         number values.
	 */
	public SudokuCell getSudokuCellWithLeastNumbers() {
		int minimumCount = Integer.MAX_VALUE;
		Point point = new Point(-1, -1);
		for (int x = 0; x < sudokuDimension; x++) {
			for (int y = 0; y < sudokuDimension; y++) {
				if (cells[x][y].getValue() <= 0) {
					int count = cells[x][y].getPossibleValuesCount();
					if ((count > 1) && (count < minimumCount)) {
						minimumCount = count;
						point.x = x;
						point.y = y;
					}
					if (count == 2) {
						return cells[x][y];
					}
				}
			}
		}
		if ((point.x < 0) || (point.y < 0)) {
			return null;
		} else {
			return cells[point.x][point.y];
		}
	}

	/**
	 * Gets the sudoku cell at particular position.
	 * 
	 * @param cellCoordinates
	 *            the cell coordinates
	 * @return the cells at coordinates
	 */
	public SudokuCell getSudokuCell(Point cellCoordinates) {
		return cells[cellCoordinates.x][cellCoordinates.y];
	}

	/**
	 * Sets the sudoku cell at a particular position.
	 * 
	 * @param sudokuCell
	 *            the sudoku cell
	 * @param point
	 *            the coordinates
	 */
	public void setSudokuCell(SudokuCell sudokuCell, Point coordinates) {
		cells[coordinates.x][coordinates.y] = sudokuCell;
	}

	/**
	 * Removes a possible cell value.
	 * 
	 * @param sudokuCell
	 *            the sudoku cell
	 */
	public void removePossibleValue(SudokuCell sudokuCell) {
		int value = sudokuCell.getValue();
		Point coordinates = sudokuCell.getCellCoordinates();

		for (int i = 0; i < sudokuDimension; i++) {
			cells[i][coordinates.y].removePossibleValue(value);
		}
		for (int j = 0; j < sudokuDimension; j++) {
			cells[coordinates.x][j].removePossibleValue(value);
		}

		int x = coordinates.x / 3;
		int y = coordinates.y / 3;
		for (int i = x * 3; i < (x + 1) * 3; i++) {
			for (int j = y * 3; j < (y + 1) * 3; j++) {
				cells[i][j].removePossibleValue(value);
			}
		}
	}

	/**
	 * Checks whether a sudoku grid is inaccurate or not.
	 * 
	 * @return
	 */
	public boolean isInaccurate() {
		for (int i = 0; i < sudokuDimension; i++) {
			for (int j = 0; j < sudokuDimension; j++) {
				if ((cells[i][j].getValue() <= 0) && (cells[i][j].getPossibleValuesCount() <= 0)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks whether a sudoku grid is complete or not.
	 * 
	 * @return true or false
	 */
	public boolean isIncomplete() {
		for (int i = 0; i < sudokuDimension; i++) {
			for (int j = 0; j < sudokuDimension; j++) {
				if (cells[i][j].getValue() <= 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Gets the cells with only one possible number.
	 * 
	 * @return the cell location
	 */
	public Point getSinglePossibleNumberCells() {
		for (int i = 0; i < sudokuDimension; i++) {
			for (int j = 0; j < sudokuDimension; j++) {
				if (cells[i][j].getValue() <= 0) {
					if (cells[i][j].getPossibleValuesCount() == 1) {
						return cells[i][j].getCellCoordinates();
					}
				}
			}
		}
		return null;
	}

	/**
	 * Draws the Sudoku grid graphics.
	 * 
	 * @param g
	 *            the graphics object
	 */
	public void draw(Graphics g) {
		int x = 0;
		for (int i = 0; i < sudokuDimension; i++) {
			int y = 0;
			for (int j = 0; j < sudokuDimension; j++) {
				Rectangle r = new Rectangle(x, y, sudokuGUIDimension, sudokuGUIDimension);
				cells[i][j].setBounds(r);
				cells[i][j].draw(g, x, y, sudokuGUIDimension, cellCoordinates[i][j]);
				y += sudokuGUIDimension;
			}
			x += sudokuGUIDimension;
		}
	}
}
