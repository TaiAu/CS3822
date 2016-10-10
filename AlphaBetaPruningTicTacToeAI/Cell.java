/**
 * The Cell class contains a constructor and methods related to the Tic-Tac-Toe
 * cells.
 * 
 * @author Tai (Shiu Hung Au)
 */
class Cell {

  int x, y;

  /**
   * The Cell constructor contains an x and y coordinate.
   * 
   * @param x
   *          the x coordinate
   * @param y
   *          the y coordinate
   */
  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * The toString method returns a String of the x and y coordinates of a cell.
   * 
   * @return the x and y coordinates
   */
  public String toString() {
    return "[" + y + ", " + x + "]";
  }
}

/**
 * The CellsAndScores class contains a constructor for the cell and its score.
 */
class CellsAndScores {
  Cell cell;
  int score;

  /**
   * The CellsAndScores constructor contains a cell and its score.
   * 
   * @param score
   *          the score given to a cell at a particular game instance
   * @param cell
   *          the cell of a Tic-Tac-Toe grid
   */
  CellsAndScores(int score, Cell cell) {
    this.cell = cell;
    this.score = score;
  }
}
