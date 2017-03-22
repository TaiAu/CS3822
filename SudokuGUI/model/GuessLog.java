package model;

/**
 * The GuessLogEntry class responsible for continually solving partially solved
 * Sudoku instances with value guesses for the target variable cell.
 * 
 * @author Tai
 */
public class GuessLog {

	private int stackLevel;
	private int guess;

	/**
	 * The GuessLogEntry constructor.
	 * 
	 * @param stackLevel
	 *            the stack level
	 * @param guess
	 *            the guess number
	 */
	public GuessLog(int stackLevel, int guess) {
		this.stackLevel = stackLevel;
		this.guess = guess;
	}

	/**
	 * The getStackLevel method returns the stack level.
	 * 
	 * @return the stack level
	 */
	public int getStackLevel() {
		return stackLevel;
	}

	/**
	 * The getGuess method returns the guess number.
	 * 
	 * @return the guess number
	 */
	public int getGuess() {
		return guess;
	}

	/**
	 * The hashCode method creates a hashcode with the guess number and stack
	 * level for the guess.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + guess;
		result = prime * result + stackLevel;
		return result;
	}

	/**
	 * The equals method compares two objects and returns true if they are the
	 * same and false otherwise.
	 * 
	 * @param obj
	 *            the object to be compared
	 * @return <code>true </code> if the objects are the same and <code>false</code> otherwise
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GuessLog other = (GuessLog) obj;
		if (guess != other.guess)
			return false;
		if (stackLevel != other.stackLevel)
			return false;
		return true;
	}
}
