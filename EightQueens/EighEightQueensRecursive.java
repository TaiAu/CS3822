/**
 * The Program solves the Eight Queens Problem with recursive backtracking and
 * involves creating a chess board and placing eight queens on it such that no
 * queen threatens another queen.
 * 
 * @author Tai (Shiu Hung Au)
 */
public class EightQueensRecursive {

  private static int board[][];
  private int numQueens;

  /**
   * The Chessboard constructor creates a board with empty squares having the
   * integer 0.
   */
  public EightQueensRecursive() {
    numQueens = 0;
    board = new int[8][8];

    for (int j = 0; j < 8; j++) {
      for (int k = 0; k < 8; k++) {
        board[j][k] = 0;
      }
    }
  }

  /**
   * The getNumQueens method returns the number of queens on the board.
   * 
   * @return the number of queens
   */
  public int getNumQueens() {
    return numQueens;
  }

  /**
   * The start method starts the solve method with 0 numbers of queen on the
   * board.
   */
  public void start() {
    solve(0);
  }

  /**
   * The solve method is the recursive back tracking algorithm that returns true
   * if there is a solution, where all 8 queens are placed, and false otherwise
   * only after it goes through the recursive loop, calling the solve method
   * again to find a solution in the next level of recursion.
   * 
   * @param numQueens
   *          the number of queens on the board
   * @return <code>true</code> if there exists a solution and <code>false</code>
   *         otherwise
   */
  public boolean solve(int numQueens) {
    if (numQueens == 8) {
      System.out.println("\nAll 8 Queens are Placed!");
      this.printBoard();
      return true;
    } else {
      for (int j = 0; j < 8; j++) {
        for (int k = 0; k < 8; k++) {
          if (validMove(j, k) == 0) {
            this.placeQueen(j, k, 0);
            numQueens++;
            if (solve(numQueens)) {
              return true;
            } else {
              this.placeQueen(j, k, 1);
              numQueens--;
            }
          }
        }
      }
    }
    return false;
  }

  /**
   * The validMove method checks whether the square with (x, y) coordinate is a
   * valid move and returns a respective integer indicating such.
   * 
   * @param x
   *          the x coordinate
   * @param y
   *          the y coordinate
   * @return -1 for invalid move or 0 for valid move
   */
  public static int validMove(int x, int y) {
    // checks rows and columns
    for (int j = 0; j < 8; j++) {
      if (get(x, j) == 1) {
        return -1;
      }
      if (get(j, y) == 1) {
        return -1;
      }
    }
    // check diagonals
    for (int j = 0; j < 8; j++) {
      if (get(x - j, y - j) == 1) {
        return -1;
      }
      if (get(x - j, y + j) == 1) {
        return -1;
      }
      if (get(x + j, y - j) == 1) {
        return -1;
      }
      if (get(x + j, y + j) == 1) {
        return -1;
      }
    }
    return 0;
  }

  // places queen (1) or empty square (0) in square with coordinate (x, y)
  public int placeQueen(int x, int y, int type) {
    if (type == 0) {
      board[x][y] = 1;
      System.out.printf("Queen PLACED in [%d][%d]\n", x, y);
      numQueens++;
      return 0;
    } else if (type == 1) {
      board[x][y] = 0;
      System.out.printf("Queen REMOVED from [%d][%d]\n", x, y);
      return 0;
    }
    System.out.println("Wrong type");
    return -3;
  }

  // gets x and y coordinates and exits if out of bounds
  public static int get(int x, int y) {
    if (x < 0 || y < 0 || x > 7 || y > 7) {
      return -1;
    }
    return board[x][y];
  }

  // goes through all squares on the 8x8 board
  public void printBoard() {
    System.out.println(" _ _ _ _ _ _ _ _ ");
    for (int j = 0; j < 8; j++) {
      for (int k = 0; k < 8; k++) {
        if (EightQueensRecursive.get(j, k) == 1) {
          System.out.print("|Q");
        } else {
          System.out.print("|_");
        }
      }
      System.out.println("|");
    }
  }

  /**
   * The main method of the program that creates a new Chessboard and starts the
   * backtracking algorithm to solve the n Queens problem.
   * 
   * @param args
   *          the command line arguments
   */
  public static void main(String[] args) {
    EightQueensRecursive board = new EightQueensRecursive();
    board.start();
  }
}
