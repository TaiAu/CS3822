package model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import view.SudokuCell;
import view.SudokuFrame;

public class PuzzleArchiver {

	public void savePuzzle(SudokuPuzzle sudokuPuzzle) {
		System.out.println("saving...");

		StringBuilder sb = new StringBuilder();

		// print to console and save columns left to right
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
				} else if (i == 8 && j == 8) {
					sb.append(
							sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
				} else if (i >= 2 && j < 9 || i >= 2 && j == 1) {
					sb.append(
							sudokuPuzzle.getSudokuCell(new Point(j, i)).toString().substring(80, 81) + " ");
				}
			}
		}
		System.out.println(sb);

		try {
			PrintWriter saver = new PrintWriter(new FileWriter("src/savedPuzzle.txt"));
			saver.print(sb);
			saver.close();
		} catch (IOException e) {
			System.out.println("file not found!");
		}
	}
	
	public void loadPuzzle(SudokuPuzzle sudokuPuzzle, SudokuFrame sudokuFrame) {
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
	}
	
}
