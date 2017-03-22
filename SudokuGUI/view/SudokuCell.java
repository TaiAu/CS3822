package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import model.SudokuPuzzle;

/**
 * The SudokuCell class contains the methods and data for manipulating the
 * Sudoku cell.
 * 
 * @author Tai
 *
 */
public class SudokuCell {

	private boolean isInitial;
	private boolean isHintButtonPressed = false;

	private int maxValue;
	private int value;

	private Point cellLocation;

	private Rectangle bounds;

	private List<Integer> possibleValues;

	public SudokuPuzzle sudokuPuzzle;

	/**
	 * The SudokuCell constructor.
	 */
	public SudokuCell() {
		this.maxValue = 9;
		this.possibleValues = new ArrayList<Integer>(maxValue);
		initialise(maxValue);
	}

	/**
	 * Initialises the sudoku cell to value 0 and possible values 1 to 9.
	 * 
	 * @param maxValue
	 *            the maximum number of variables (9 for 9x9 Sudoku puzzles)
	 */
	public void initialise(int maxValue) {
		this.value = 0;
		this.possibleValues.clear();
		this.isInitial = false;
		add(maxValue);
	}

	/**
	 * Adds the maximum number of possible values.
	 * 
	 * @param maxValue
	 *            the maximum number of variables (9 for 9x9 Sudoku puzzles)
	 */
	private void add(int maxValue) {
		for (int i = 1; i <= maxValue; i++) {
			this.possibleValues.add(i);
		}
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value of the Sudoku cell variable
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the Sudoku cell's value
	 * 
	 * @param value
	 *            the value of a Sudoku cell variable
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Sets a Sudoku cell's isInitial value to be true or false.
	 * 
	 * @param isInitial
	 *            true or false
	 */
	public void setIsInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	/**
	 * Sets isHintButtonPressed to be true or false.
	 * 
	 * @param isHintButtonPressed
	 *            true or false
	 */
	public void setIsHintButtonPressed(boolean isHintButtonPressed) {
		this.isHintButtonPressed = isHintButtonPressed;
	}

	/**
	 * Gets the boolean isHintButtonPressed.
	 * 
	 * @return true if the hint button is pressed and false otherwise
	 */
	public boolean getIsHintButtonPressed() {
		return isHintButtonPressed;
	}

	/**
	 * Clears the possible values in a Sudoku cell.
	 */
	public void clearPossibleValues() {
		this.possibleValues.clear();
	}

	/**
	 * Gets the cell coordinates of a Sudoku cell.
	 * 
	 * @return the cell coordinates
	 */
	public Point getCellLocation() {
		return cellLocation;
	}

	/**
	 * Sets the cell location of a Sudoku cell.
	 * 
	 * @param cellLocation
	 *            the cell coordinates to be set
	 */
	public void setCellLocation(Point cellLocation) {
		this.cellLocation = cellLocation;
	}

	/**
	 * Sets the Sudoku region bounds.
	 * 
	 * @param bounds
	 *            the graphical object bounds
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	/**
	 * Returns a boolean to whether a region contains a cell coordinate.
	 * 
	 * @param point
	 *            the sudoku cell coordinates
	 * @return true if region contains cell coordinates and false otherwise
	 */
	public boolean contains(Point point) {
		return bounds.contains(point);
	}

	/**
	 * Gets the list of possible values.
	 * 
	 * @return the list of possible values
	 */
	public List<Integer> getPossibleValues() {
		return possibleValues;
	}

	/**
	 * Sets the list of possible values.
	 * 
	 * @param possibleValues
	 *            the list of possible values
	 */
	private void setPossibleValues(List<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}

	/**
	 * Gets the number of possible values.
	 * 
	 * @return the number of possible values
	 */
	public int getPossibleValuesCount() {
		return possibleValues.size();
	}

	/**
	 * Returns the boolean as to whether a value is possible for a sudoku cell.
	 * 
	 * @param possibleValue
	 *            the value to be tested
	 * @return true if the value to be tested is a possible value and false
	 *         otherwise
	 */
	public boolean isPossibleValue(int possibleValue) {
		return possibleValues.contains((Integer) possibleValue);
	}

	/**
	 * Removes the possible value from a Sudoku cell.
	 * 
	 * @param possibleValue
	 *            the ex-possible value to be removed from a Sudoku cell.
	 */
	public void removePossibleValue(int possibleValue) {
		this.possibleValues.remove((Integer) possibleValue);
	}

	/**
	 * Returns a copy of a Sudoku cell.
	 * 
	 * @return the sudoku cell copy
	 */
	public SudokuCell copy() {
		SudokuCell sudokuCell = new SudokuCell();
		sudokuCell.setIsInitial(this.isInitial);
		sudokuCell.setBounds(this.bounds);
		sudokuCell.setCellLocation(this.getCellLocation());
		sudokuCell.setPossibleValues(this.copyPossibleValues());
		sudokuCell.setValue(this.getValue());
		return sudokuCell;
	}

	/**
	 * Copies a list of possible values
	 * 
	 * @return a list copy of possible values
	 */
	private List<Integer> copyPossibleValues() {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer number : this.getPossibleValues()) {
			list.add(number);
		}
		return list;
	}

	/**
	 * Returns a string representation of the Sudoku cell's coordinates, values
	 * and possible values.
	 */
	@Override
	public String toString() {
		String s = bounds.toString();
		int sPos = s.indexOf("[") + 1;
		int ePos = s.indexOf("]", sPos);

		StringBuilder builder = new StringBuilder();
		builder.append("Cell located at ");
		builder.append(cellLocation.x);
		builder.append(",");
		builder.append(cellLocation.y);
		builder.append("; drawing coordinates ");
		builder.append(s.substring(sPos, ePos));
		builder.append("; value: ");
		builder.append(value);
		builder.append(", possible values: ");

		for (int i = 0; i < possibleValues.size(); i++) {
			builder.append(possibleValues.get(i));
			if (i < (possibleValues.size() - 1)) {
				builder.append(", ");
			}
		}
		return builder.toString();
	}

	/**
	 * Draws the Sudoku cell graphical object.
	 * 
	 * @param g
	 *            the graphical object components
	 * @param x
	 *            the Sudoku cell's x coordinate
	 * @param y
	 *            the Sudoku cell's y coordinate
	 * @param width
	 *            the width of the Sudoku cell's graphical object
	 * @param cellPosition
	 *            the Sudoku cell's position
	 */
	public void draw(Graphics g, int x, int y, int width, int cellPosition) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, width);
		g.setColor(Color.GRAY);
		g.drawRect(x, y, width, width);
		drawBorder(g, x, y, width, cellPosition);
		Font font = g.getFont();
		FontRenderContext frc = new FontRenderContext(null, true, true);
		if (value > 0) {
			String s = Integer.toString(value);

			BufferedImage image = createImage(font, frc, width, s);

			int xx = x + (width - image.getWidth()) / 2;
			int yy = y + (width - image.getHeight()) / 2;
			g.drawImage(image, xx, yy, null);

		} else {
			List<String> list = concatenatePossibleValues();

			double imageWidth = 0.0D;
			double imageHeight = 0.0D;
			double stringHeight = 0.0D;
			for (Object s : list) {
				Rectangle2D r = font.getStringBounds((String) s, frc);
				imageWidth = Math.max(imageWidth, r.getWidth());
				imageHeight += r.getHeight();
				stringHeight = Math.max(stringHeight, r.getHeight());
			}

			BufferedImage image = createImage(list, imageWidth, imageHeight, stringHeight);

			int xx = x + (width - image.getWidth()) / 2;
			int yy = y + (width - image.getHeight()) / 2;
			g.drawImage(image, xx, yy, null);
		}
	}

	/**
	 * Draws the Sudoku cell's border
	 * 
	 * @param g
	 *            the graphical object components
	 * @param x
	 *            the Sudoku cell's x coordinates
	 * @param y
	 *            the Sudoku cell's y coordinates
	 * @param width
	 *            the width of the Sudoku cell's graphical object
	 * @param cellPosition
	 *            the Sudoku cell's position
	 */
	private void drawBorder(Graphics g, int x, int y, int width, int cellPosition) {
		switch (cellPosition) {
		case 1:
			drawLeftBorder(g, x, y, width);
			drawTopBorder(g, x, y, width);
			break;
		case 2:
			drawTopBorder(g, x, y, width);
			break;
		case 3:
			drawTopBorder(g, x, y, width);
			drawRightBorder(g, x, y, width);
			break;
		case 4:
			drawLeftBorder(g, x, y, width);
			break;
		case 6:
			drawRightBorder(g, x, y, width);
			break;
		case 7:
			drawLeftBorder(g, x, y, width);
			drawBottomBorder(g, x, y, width);
			break;
		case 8:
			drawBottomBorder(g, x, y, width);
			break;
		case 9:
			drawBottomBorder(g, x, y, width);
			drawRightBorder(g, x, y, width);
			break;
		}
	}

	/**
	 * Draws the top border of the Sudoku cell.
	 * 
	 * @param g
	 *            the graphical object components
	 * @param x
	 *            the Sudoku cell's x coordinates
	 * @param y
	 *            the Sudoku cell's y coordinates
	 * @param width
	 *            the width of the Sudoku cell's graphical object
	 */
	private void drawTopBorder(Graphics g, int x, int y, int width) {
		g.drawLine(x, y + 1, x + width, y + 1);
		g.drawLine(x, y + 2, x + width, y + 2);
	}

	/**
	 * Draws the right border of the Sudoku cell.
	 * 
	 * @param g
	 *            the graphical object components
	 * @param x
	 *            the Sudoku cell's x coordinates
	 * @param y
	 *            the Sudoku cell's y coordinates
	 * @param width
	 *            the width of the Sudoku cell's graphical object
	 */
	private void drawRightBorder(Graphics g, int x, int y, int width) {
		g.drawLine(x + width - 1, y, x + width - 1, y + width);
		g.drawLine(x + width - 2, y, x + width - 2, y + width);
	}

	/**
	 * Draws the bottom border of the Sudoku cell.
	 * 
	 * @param g
	 *            the graphical object components
	 * @param x
	 *            the Sudoku cell's x coordinates
	 * @param y
	 *            the Sudoku cell's y coordinates
	 * @param width
	 *            the width of the Sudoku cell's graphical object
	 */
	private void drawBottomBorder(Graphics g, int x, int y, int width) {
		g.drawLine(x, y + width - 1, x + width, y + width - 1);
		g.drawLine(x, y + width - 2, x + width, y + width - 2);
	}

	/**
	 * Draws the left border of the Sudoku cell.
	 * 
	 * @param g
	 *            the graphical object components
	 * @param x
	 *            the Sudoku cell's x coordinates
	 * @param y
	 *            the Sudoku cell's y coordinates
	 * @param width
	 *            the width of the Sudoku cell's graphical object
	 */
	private void drawLeftBorder(Graphics g, int x, int y, int width) {
		g.drawLine(x + 1, y, x + 1, y + width);
		g.drawLine(x + 2, y, x + 2, y + width);
	}

	/**
	 * Creates an image of the Sudoku cell containing a value. A Sudoku cell
	 * containing a value can initially set or set later by the user (not
	 * initially set).
	 * 
	 * @param font
	 *            the font of the text
	 * @param frc
	 *            the pixel information required to display the text
	 * @param width
	 *            the width of the Sudoku cell graphical object
	 * @param s
	 *            the Sudoku cell's string
	 * @return the Sudoku cell image
	 */
	private BufferedImage createImage(Font font, FontRenderContext frc, int width, String s) {
		int margin = 6;
		double extra = (double) margin + margin;

		Font largeFont = font.deriveFont((float) (width * 2 / 3));
		Rectangle2D r = largeFont.getStringBounds(s, frc);

		BufferedImage image = new BufferedImage((int) Math.round(r.getWidth() + extra),
				(int) Math.round(extra - r.getY()), BufferedImage.TYPE_INT_RGB);
		Graphics gg = image.getGraphics();
		gg.setColor(Color.WHITE);
		gg.fillRect(0, 0, image.getWidth(), image.getHeight());

		if (isInitial) {
			gg.setColor(Color.MAGENTA);
		} else {
			gg.setColor(Color.PINK);
		}

		int x = margin;
		int y = -(int) Math.round(r.getY());
		gg.setFont(largeFont);
		gg.drawString(s, x, y);
		gg.dispose();
		return image;
	}

	/**
	 * Concatenates the string of possible values.
	 * 
	 * @return the list of concatenated possible values.
	 */
	private List<String> concatenatePossibleValues() {
		List<String> list = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();

		int stringIndex = 0;

		for (int index = 0; index < getPossibleValuesCount(); index++) {
			builder.append(possibleValues.get(index));
			if (stringIndex < 2) {
				builder.append(' ');
				stringIndex++;
			} else {
				list.add(builder.toString());
				builder.delete(0, builder.length());
				stringIndex = 0;
			}
		}
		if (builder.length() > 0) {
			list.add(builder.toString());
		}

		return list;
	}

	/**
	 * Creates an image of the Sudoku cell's hints. Empty cells can display
	 * hints of remaining possible values.
	 * 
	 * @param list
	 *            the string list of remaining possible values
	 * @param imageWidth
	 *            the width of the Sudoku cell image
	 * @param imageHeight
	 *            the height of the Sudoku cell image
	 * @param stringHeight
	 *            the height of the string
	 * @return the sudoku cell image
	 */
	private BufferedImage createImage(List<String> list, double imageWidth, double imageHeight, double stringHeight) {

		int margin = 6;
		double extra = (double) margin + margin;
		BufferedImage image = new BufferedImage((int) Math.round(imageWidth + extra),
				(int) Math.round(imageHeight + extra), BufferedImage.TYPE_INT_RGB);
		Graphics gg = image.getGraphics();
		gg.setColor(Color.WHITE);
		gg.fillRect(0, 0, image.getWidth(), image.getHeight());

		if (isHintButtonPressed) {
			gg.setColor(Color.WHITE);
		} else {
			gg.setColor(Color.LIGHT_GRAY);
		}

		int x = margin;
		int y = margin / 2 + (int) Math.round(stringHeight);

		for (Object s : list) {
			gg.drawString((String) s, x, y);
			y += (int) Math.round(stringHeight);
		}
		gg.dispose();
		return image;
	}
}
