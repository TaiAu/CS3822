/**
 * The Backtracking Algorithm to the Eight Queens Problem.
 * <p>
 * The Eight Queens Problem is one of placing eight queens on a chess board such
 * that no queens threaten another i.e. share a same row, column or diagonal.
 * <p>
 * My solution to this problem is to use a backtracking algorithm such that each
 * queen on each y coordinate gets a number representing its x coordinate.
 * <p>
 * 
 * @author Tai (Shiu Hung Au)
 */

public class EightQueens {

  // The solution number
  private static int solution = 0;

  // The row integer array
  private static int[] row = new int[8];

  /**
   * The gardezLaReine() method compares target with x coordinates of the prior
   * rows to determine whether the target square is being threatened and returns
   * true if so and false otherwise.
   * <p>
   * The further away the y coordinates of the prior rows get, the further away
   * the x coordinate comparison becomes by the same amount (i). To elaborate:
   * <code>target == x - i</code> compares the left diagonals,
   * <code>target == x + i</code> compares the right diagonals and
   * <code>target == x</code> compares the verticals.
   * <p>
   * Queens will never be on the same row because of the nature of data
   * structure used in this algorithm since every array element can only contain
   * one number (from -1 to 7).
   * <p>
   * y represents the y coordinate; x represents the x coordinate; i represents
   * the increment; target represents the target x coordinate in row y.
   * 
   * @param y
   *          the y coordinate of the row array
   * @return <code>true</code> if the target square is being threatened or
   *         <code>false</code> otherwise
   */
  static boolean gardezLaReine(int y) {
    int x = row[y];
    for (int i = 1; i <= y; i++) {
      int target = row[y - i];
      if (target == x - i || target == x || target == x + i) {
        return true;
      }
    }
    return false;
  }

  /**
   * The printSolution() method prints all the x and y coordinates of the
   * solution on the chess board only when all constraints are satisfied i.e.
   * there are 8 queens on the board that do not threaten each other.
   * <p>
   * The printSolution() method only prints a queen when the target x coordinate
   * matches each respective y coordinate's integer in the row array and prints
   * an empty square otherwise.
   */
  public static void printSolution() {
    System.out.println("\nSolution " + (++solution) + "\n _ _ _ _ _ _ _ _ ");
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        System.out.print((row[y] == x) ? "|Q" : "|_");
      }
      System.out.println("|");
    }
    System.out.println();
  }

  /**
   * The main method of the EightQueens backtracking algorithm:
   * 
   * The backtracking algorithm starts by filling the target row array index
   * (starting with y = 0) with -1 until a positive integer (representing the x
   * coordinate) that satisfies the constraint of the target square not being
   * threatened by another queen on the chess board has been found, in which
   * case the row array index gets updated with such.
   * <p>
   * The backtracking algorithm increments the x coordinates of each row index
   * (y coordinate) by one and checks whether the target square is threatened or
   * not. If the target square is not threatened, the integer number
   * representing its x coordinate gets updated to the row array's respective y
   * coordinate index. If the target square is threatened, the x coordinate gets
   * incremented by one again until 7 (the last x coordinate) is reached.
   * <p>
   * The backtracking algorithm decrements the row index (or y coordinate) by
   * one from the current row index if all target squares (or x coordinates from
   * the current row index) have been checked and no safe square exists. The x
   * coordinate of the decremented row index will start from the previously safe
   * x coordinate and be incremented by one. If the incremented x coordinate's
   * square is safe, the row index (or y coordinate) gets incremented and if
   * not, the x coordinate increments again until the limit (coordinate x = 7)
   * has been reached - if there is still no safe square, the algorithm
   * backtracks further and the y coordinate gets decremented.
   * <p>
   * When y has not reached 7, the row last index and y coordinate of the board,
   * it will continue the backtracking loop until it does, in which case it
   * prints the solution if there is indeed one.
   * 
   * @param args
   */
  public static void main(String[] args) {
    try {
      int y = 0;
      row[0] = -1;
      while (y >= 0) {
        do {
          row[y]++;
        } while ((row[y] < 8) && gardezLaReine(y));
        if (row[y] < 8) {
          System.out.println("Queen PLACED: " + "(" + row[y] + "," + y + ")");
          if (y < 7) {
            row[++y] = -1;
          } else {
            printSolution();
            System.out.println("Queen REMOVED: " + "(" + row[y] + "," + y + ")");
          }
        } else {
          y--;
          System.out.println("Queen REMOVED: " + "(" + row[y] + "," + y + ")");
        }
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Out of Grid Coordinates: All Solutions Found!");
    }
  }
}
