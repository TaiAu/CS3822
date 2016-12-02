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

public class SudokuCell {

	private boolean isInitial;

	private int maxNumber;
	private int value;

	private Point cellCoordinates;

	private Rectangle bounds;

	private List<Integer> possibleValues;

	public SudokuCell() {
		this.maxNumber = 9;
		this.possibleValues = new ArrayList<Integer>(maxNumber);
		initialise(maxNumber);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void initialise(int maxNumber) {
		this.value = 0;
		this.possibleValues.clear();
		this.isInitial = false;
		add(maxNumber);
	}

	private void add(int maxNumber) {
		for (int i = 1; i <= maxNumber; i++) {
			this.possibleValues.add(i);
		}
	}

	public void clearPossibleValues() {
		this.possibleValues.clear();
	}

	private void setPossibleValues(List<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}

	public int getPossibleValuesCount() {
		return possibleValues.size();
	}
	
	public void setIsInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}

	public Point getCellCoordinates() {
		return cellCoordinates;
	}

	public void setCellCoordinates(Point cellCoordinates) {
		this.cellCoordinates = cellCoordinates;
	}

	public List<Integer> getPossibleValues() {
		return possibleValues;
	}

	public boolean isPossibleValue(int possibleValue) {
		return possibleValues.contains((Integer) possibleValue);
	}

	public void removePossibleValue(int possibleValue) {
		this.possibleValues.remove((Integer) possibleValue);
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public boolean contains(Point point) {
		return bounds.contains(point);
	}

	public SudokuCell copy() {
		SudokuCell sudokuCell = new SudokuCell();
		sudokuCell.setCellCoordinates(this.getCellCoordinates());
		sudokuCell.setPossibleValues(this.copyPossibleValues());
		sudokuCell.setIsInitial(this.isInitial);
		sudokuCell.setBounds(this.bounds);
		sudokuCell.setValue(this.getValue());
		return sudokuCell;
	}

	private List<Integer> copyPossibleValues() {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer number : this.getPossibleValues()) {
			list.add(number);
		}
		return list;
	}

	@Override
	public String toString() {
		String s = bounds.toString();
		int sPos = s.indexOf("[") + 1;
		int ePos = s.indexOf("]", sPos);

		StringBuilder builder = new StringBuilder();
		builder.append("Cell located at ");
		builder.append(cellCoordinates.x);
		builder.append(",");
		builder.append(cellCoordinates.y);
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

	private void drawBottomBorder(Graphics g, int x, int y, int width) {
		g.drawLine(x, y + width - 1, x + width, y + width - 1);
		g.drawLine(x, y + width - 2, x + width, y + width - 2);
	}

	private void drawLeftBorder(Graphics g, int x, int y, int width) {
		g.drawLine(x + 1, y, x + 1, y + width);
		g.drawLine(x + 2, y, x + 2, y + width);
	}
	
	private void drawTopBorder(Graphics g, int x, int y, int width) {
		g.drawLine(x, y + 1, x + width, y + 1);
		g.drawLine(x, y + 2, x + width, y + 2);
	}

	private void drawRightBorder(Graphics g, int x, int y, int width) {
		g.drawLine(x + width - 1, y, x + width - 1, y + width);
		g.drawLine(x + width - 2, y, x + width - 2, y + width);
	}

	private BufferedImage createImage(List<String> list, double imageWidth, double imageHeight, double stringHeight) {
		int margin = 6;
		double extra = (double) margin + margin;
		BufferedImage image = new BufferedImage((int) Math.round(imageWidth + extra),
				(int) Math.round(imageHeight + extra), BufferedImage.TYPE_INT_RGB);
		Graphics gg = image.getGraphics();
		gg.setColor(Color.WHITE);
		gg.fillRect(0, 0, image.getWidth(), image.getHeight());

		gg.setColor(Color.LIGHT_GRAY);
		int x = margin;
		int y = margin / 2 + (int) Math.round(stringHeight);
		for (Object s : list) {
			gg.drawString((String) s, x, y);
			y += (int) Math.round(stringHeight);
		}
		gg.dispose();
		return image;
	}
	
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
}
