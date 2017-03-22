package model;

import java.util.Stack;

/**
 * The PuzzleStack class contains puzzle stack methods.
 * 
 * @author Tai
 */
public class PuzzleStack {
	private Stack<PuzzlePosition> stack;

	/**
	 * The PuzzleStack constructor.
	 */
	public PuzzleStack() {
		this.stack = new Stack<PuzzlePosition>();
	}

	/**
	 * The pushStack method pushes the stack.
	 * 
	 * @param count
	 *            the count of the stack
	 */
	public void pushStack(PuzzlePosition position) {
		this.stack.push(position);
	}

	/**
	 * The peekStack method peeks the stack.
	 * 
	 * @return Sudoku data if not empty and null otherwise
	 */
	public PuzzlePosition peekStack() {
		if (stack.size() > 0) {
			return (PuzzlePosition) stack.peek();
		} else {
			return null;
		}
	}

	/**
	 * The popStack method pops the stack.
	 * 
	 * @return Sudoku data if not empty and null otherwise
	 */
	public PuzzlePosition popStack() {
		if (stack.size() > 0) {
			return (PuzzlePosition) stack.pop();
		} else {
			return null;
		}
	}
}
