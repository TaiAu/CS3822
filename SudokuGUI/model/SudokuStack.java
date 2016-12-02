package model;

import java.util.Stack;

/**
 * The SudokuStack contains puzzle stack methods.
 * 
 * @author Tai
 *
 */
public class SudokuStack {
	private Stack<SudokuData> stack;

	/**
	 * Constructor for the SudokuStack.
	 */
	public SudokuStack() {
		this.stack = new Stack<SudokuData>();
	}

	/**
	 * Pushes the stack.
	 * 
	 * @param count
	 *            the count of the stack
	 */
	public void pushStack(SudokuData count) {
		this.stack.push(count);
	}

	/**
	 * Peeks the stack.
	 * 
	 * @return Sudoku data if not empty and null otherwise
	 */
	public SudokuData peekStack() {
		if (stack.size() > 0) {
			return (SudokuData) stack.peek();
		} else {
			return null;
		}
	}

	/**
	 * Pops the stack.
	 * 
	 * @return Sudoku data if not empty and null otherwise
	 */
	public SudokuData popStack() {
		if (stack.size() > 0) {
			return (SudokuData) stack.pop();
		} else {
			return null;
		}
	}
}
