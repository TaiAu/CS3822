package controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JPanel;

import view.SudokuFrame;

public class PrintListener implements Runnable {

	private SudokuFrame sudokuFrame;

	public PrintListener(SudokuFrame sudokuFrame) {
		this.sudokuFrame = sudokuFrame;
	}

	private BufferedImage createPanelImage() {
		JPanel sudokuPanel = sudokuFrame.getSudokuPanel();
		BufferedImage image = new BufferedImage(sudokuPanel.getWidth(), sudokuPanel.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		sudokuPanel.paint(graphics);
		graphics.dispose();
		return image;
	}

	public class PrintableImage implements Printable {
		private BufferedImage image;
		private int orientation;
		private double x, y, width;

		public PrintableImage(PrinterJob printJob, BufferedImage sudokuImage) {
			PageFormat pageFormat = printJob.defaultPage();
			this.image = sudokuImage;
			this.orientation = pageFormat.getOrientation();
			this.x = pageFormat.getImageableX();
			this.y = pageFormat.getImageableY();
			this.width = pageFormat.getImageableWidth();
		}

		@Override
		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			if (pageIndex == 0) {
				int pageWidth = 0;
				int pageHeight = 0;
				if (orientation == PageFormat.PORTRAIT) {
					pageWidth = (int) Math.min(width, (double) image.getWidth());
					pageHeight = pageWidth * image.getHeight() / image.getWidth();
				} else {
					pageHeight = (int) Math.min(width, (double) image.getHeight());
					pageWidth = pageHeight * image.getWidth() / image.getHeight();
				}
				graphics.drawImage(image, (int) x, (int) y, pageWidth, pageHeight, null);
				return PAGE_EXISTS;
			} else {
				return NO_SUCH_PAGE;
			}
		}
	}
	
	@Override
	public void run() {
		final BufferedImage image = createPanelImage();

		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(new PrintableImage(printJob, image));

		if (printJob.printDialog()) {
			try {
				printJob.print();
			} catch (PrinterException printerException) {
				printerException.printStackTrace();
			}
		}
	}
	
}