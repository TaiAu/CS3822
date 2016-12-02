package model;

public class SudokuSolver {

  private int stackLevel;
  private int guess;

  public SudokuSolver(int stackLevel, int guess) {
    this.stackLevel = stackLevel;
    this.guess = guess;
  }

  public int getStackLevel() {
    return stackLevel;
  }

  public int getGuess() {
    return guess;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + guess;
    result = prime * result + stackLevel;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SudokuSolver other = (SudokuSolver) obj;
    if (guess != other.guess)
      return false;
    if (stackLevel != other.stackLevel)
      return false;
    return true;
  }
}