import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Grid class contains various methods related to the Tic-Tac-Toe board
 * including game rules, constraints, cell scores, game over scenarios, game
 * tree statistics and the minimax algorithm for alpha beta search tree pruning.
 * 
 * @author Tai
 *
 */
class Grid {

  // A list of empty cells
  List<Cell> emptyCells;

  // A grid of cells: array of arrays
  static int[][] grid = new int[3][3];

  // A scanner for user input
  Scanner scanner = new Scanner(System.in);

  // Maximum search tree depth
  int treeDepth = 9;

  // A list of cells and their total score from search tree pruning
  List<CellsAndScores> listOfScores = new ArrayList<>();

  // tree size count
  static int count = 0;

  // previous tree size count
  int previousCount = 0;

  // the size count of currently - previously evaluated tree nodes
  int differenceCount = 0;

  /**
   * The points method simply compares the positioning of the current cell to
   * neighboring X's and O's on the Tic-Tac-Toe and comes up with a score:
   * <p>
   * If there will be 3 X's horizontally, vertically or diagonally (both left to
   * right and right to left) on the next move, the score is +100 for that cell.
   * <p>
   * If there will be 3 O's horizontally, vertically or diagonally (both left to
   * right and right to left) on the next move, the score is -100 for that cell.
   * 
   * If there will be 2 X's and no O's horizontally, vertically or diagonally
   * (both left to right and right to left) on the next move, the score is +10
   * for that cell.
   * 
   * If there will be 2 O's and no X's horizontally, vertically or diagonally
   * (both left to right and right to left) on the next move, the score is -10
   * for that cell.
   * 
   * If there will be one X and no O's horizontally, vertically or diagonally
   * (both left to right and right to left) on the next move, the score is +1
   * for that cell.
   * 
   * If there will be one O and no X's horizontally, vertically or diagonally
   * (both left to right and right to left) on the next move, the score is -1
   * for that cell.
   * 
   * @param X
   *          the computer integer value (always 1)
   * @param O
   *          the player integer value (always 2)
   * @return difference: the score based upon the positioning of the current
   *         cell and its neighboring cells.
   */
  private int points(int X, int O) {
    int difference;
    if (X == 3) {
      difference = 100;
    } else if (O == 0 && X == 2) {
      difference = 10;
    } else if (O == 0 && X == 1) {
      difference = 1;
    } else if (O == 1 && X == 0) {
      difference = -1;
    } else if (O == 2 && X == 0) {
      difference = -10;
    } else if (O == 3) {
      difference = -100;
    } else {
      difference = 0;
    }
    return difference;
  }

  /**
   * The score method sums up the total score for all the tree node evaluations
   * after comparing rows, columns and diagonals (both left to right and right
   * to left) with the points method above.
   * 
   * @return score: the total score of the cell at a current game instance.
   */
  public int score() {
    int score = 0;

    // Rows
    for (int y = 0; y < 3; ++y) {
      int X = 0;
      int O = 0;
      for (int x = 0; x < 3; ++x) {
        if (grid[x][y] == 0) {
          continue;
        } else if (grid[x][y] == 1) {
          X++;
        } else if (grid[x][y] == 2) {
          O++;
        } else {
          System.out.println("Alien found!");
          break;
        }
      }
      score += points(X, O);
    }

    // Columns
    for (int x = 0; x < 3; ++x) {
      int X = 0;
      int O = 0;
      for (int y = 0; y < 3; ++y) {
        if (grid[x][y] == 0) {
          continue;
        } else if (grid[x][y] == 1) {
          X++;
        } else if (grid[x][y] == 2) {
          O++;
        } else {
          System.out.println("Alien found!");
          break;
        }
      }
      score += points(X, O);
    }

    // Resetting scores
    int X = 0;
    int O = 0;

    // Diagonal (left to right)
    for (int x = 0, y = 0; x < 3; ++x, ++y) {
      if (grid[x][y] == 0) {
        continue;
      } else if (grid[x][y] == 1) {
        X++;
      } else if (grid[x][y] == 2) {
        O++;
      } else {
        System.out.println("Alien found!");
        break;
      }
    }
    score += points(X, O);

    // Resetting scores
    X = 0;
    O = 0;

    // Diagonal (right to left)
    for (int x = 2, y = 0; x > -1; --x, ++y) {
      if (grid[x][y] == 0) {
        continue;
      } else if (grid[x][y] == 1) {
        X++;
      } else if (grid[x][y] == 2) {
        O++;
      } else {
        System.out.println("Alien found!");
        break;
      }
    }
    score += points(X, O);
    return score;
  }

  /**
   * The printTreeSize method prints out the game tree statistics including the
   * number of tree nodes evaluated at a game tree branch and the total number
   * of tree nodes evaluated thus far.
   */
  public void printTreeSize() {
    System.out.println(
        "Trees nodes evaluated at this branch: " + (count - previousCount) + "; Total tree nodes evaluated: " + count);
  }

  /**
   * The savePreviousCount method simply assigns the count value to
   * previousCount.
   */
  public void savePreviousCount() {
    previousCount = count;
  }

  /**
   * The minimax method is the algorithm used in alpha beta search tree pruning.
   * It is a recursive algorithm for choosing the next move by seeking to
   * Minimise the maximum loss (i.e. losing the game). Alpha-beta search tree
   * pruning ensures that no other nodes need be evaluated when the best values
   * (depending on whether a player is the minimising or maximising player) have
   * been found at a game tree node.
   * <p>
   * The algorithm maintains two values: alpha and beta, both of which start at
   * their minimum score ("negative infinity" represented by Integer.MIN_VALUE
   * for the maximising player and "positive infinity" represented by
   * Integer.MAX_VALUE for the minimising player). Note that there is no such
   * concept of infinity on Java; hence, Integer.MIN_VALUE and Integer.MAX_VALUE
   * are used instead.
   * <p>
   * It is possible that when choosing a certain branch of a certain node, the
   * minimum score that the minimising player is assured of becomes less than
   * the maximum score that the maximising player is assured of (beta<=alpha).
   * In this case, the parent node should not choose this node as it will make
   * worse the score for the parent node. Therefore, the other branches of the
   * node do not have to be explored.
   * 
   * @param alpha
   *          the alpha integer value ("negative infinity" represented by
   *          Integer.MIN_VALUE)
   * @param beta
   *          the beta integer value ("positive infinity" represented by
   *          Integer.MAX_VALUE)
   * @param depth
   *          the game tree depth
   * @param turn
   *          the player's integer (1 = computer and 2 = human)
   * @return the current score of a game tree node at the current stage of alpha
   *         beta pruning
   */
  public int minimax(int alpha, int beta, int depth, int turn) {

    List<Cell> cellsAvailable = getEmptyCells();

    if (depth == treeDepth || gameOver()) {
      return score();
    }

    if (beta <= alpha) {
      if (turn == 1) {
        return Integer.MAX_VALUE;
      } else {
        return Integer.MIN_VALUE;
      }
    } else if (beta >= alpha) {
      count++;
      System.out.println("Pruning at tree depth: " + depth + "; Total tree nodes evaluated: " + count);
    }

    if (cellsAvailable.isEmpty()) {
      return 0;
    }

    if (depth == 0) {
      listOfScores.clear();
    }

    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    for (int i = 0; i < cellsAvailable.size(); ++i) {
      Cell cell = cellsAvailable.get(i);

      int currentScore = 0;

      if (turn == 1) {
        move(cell, 1);
        currentScore = minimax(alpha, beta, depth + 1, 2);
        max = Math.max(max, currentScore);

        // Alpha is the minimum of current score versus alpha
        alpha = Math.min(currentScore, alpha);

        if (depth == 0) {
          listOfScores.add(new CellsAndScores(currentScore, cell));
        }
      } else if (turn == 2) {
        move(cell, 2);
        currentScore = minimax(alpha, beta, depth + 1, 1);
        min = Math.min(min, currentScore);

        // Beta is the maximum of current score versus current beta
        beta = Math.max(currentScore, beta);
      }
      // Reset grid to 0
      grid[cell.x][cell.y] = 0;

      // Do not evaluate the rest of the branches
      if (currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) {
        break;
      }
    }
    return turn == 1 ? max : min;
  }

  /**
   * The gameOver method returns true if X or O wins or draws.
   * 
   * @return <code>true</code> if the game is over
   */
  public boolean gameOver() {
    // Game is over is someone has won, or grid is full (draw)
    return (xWins() || oWins() || getEmptyCells().isEmpty());
  }

  /**
   * The xWins method checks the row, column and diagonal cells to see if X has
   * 3 in a row, column or diagonal.
   * 
   * @return <code>true</code> if x wins and <code>false</code> otherwise.
   */
  public boolean xWins() {
    // Row or Column win for X
    for (int i = 0; i < 3; ++i) {
      if (((grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2] && grid[i][0] == 1)
          || (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] == 1))) {
        return true;
      }
      // Diagonal win for X
      if ((grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] == 1)
          || (grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0] && grid[0][2] == 1)) {
        return true;
      }
    }
    return false;
  }

  /**
   * The oWins method checks the row, column and diagonal cells to see if O has
   * 3 in a row, column or diagonal.
   * 
   * @return <code>true</code> if o wins and <code>false</code> otherwise.
   */
  public boolean oWins() {
    // Row or Column win for O
    for (int i = 0; i < 3; ++i) {
      if ((grid[i][0] == grid[i][1] && grid[i][0] == grid[i][2] && grid[i][0] == 2)
          || (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] == 2)) {
        return true;
      }
      // Diagonal win for O
      if ((grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] == 2)
          || (grid[0][2] == grid[1][1] && grid[0][2] == grid[2][0] && grid[0][2] == 2)) {
        return true;
      }
    }
    return false;
  }

  /**
   * The getEmptyCells method adds empty cells to an ArrayList.
   * 
   * @return an ArrayList of empty cells.
   */
  public List<Cell> getEmptyCells() {
    emptyCells = new ArrayList<>();
    for (int x = 0; x < 3; ++x) {
      for (int y = 0; y < 3; ++y) {
        if (grid[x][y] == 0) {
          emptyCells.add(new Cell(x, y));
        }
      }
    }
    return emptyCells;
  }

  /**
   * The move method updates the cell with the player represented by an integer
   * 1 (Computer) or 2 (Player).
   * 
   * @param cell
   *          the [x][y] cell on the grid
   * @param player
   *          the player 1(X) or 2(O)
   */
  public void move(Cell cell, int player) {
    grid[cell.x][cell.y] = player;
  }

  /**
   * The isMoveOK method determines whether the cell is available for use.
   * 
   * @return <code>true</code> if not occupied and <code>false</code> otherwise.
   */
  public boolean isMoveOK(Cell cell) {
    return grid[cell.x][cell.y] == 0;
  }

  /**
   * The bestMove method compares the scores on the list of scores, gets the
   * best one and updates best with it. It then returns the cell with the best
   * score from the ArrayList of scores.
   * 
   * @return the best cell available given the score.
   */
  public Cell bestMove() {
    int best = 0;
    int score = -999999;

    for (int i = 0; i < listOfScores.size(); ++i) {
      if (score < listOfScores.get(i).score) {
        score = listOfScores.get(i).score;
        best = i;
      }
    }
    return listOfScores.get(best).cell;
  }

  /**
   * The printGrid method prints out the Tic-Tac-Toe grid in a visually
   * understandable manner.
   */
  public void printGrid() {
    System.out.println();
    for (int x = 0; x < 3; ++x) {
      for (int y = 0; y < 3; ++y) {
        if (grid[x][y] == 0)
          System.out.print("[ ]");
        if (grid[x][y] == 1)
          System.out.print("[X]");
        if (grid[x][y] == 2)
          System.out.print("[O]");
      }
      System.out.println("");
    }
    System.out.println();
  }

  /**
   * The resetGrid method assigns 0 to all the grid cells.
   */
  public static void resetGrid() {
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        grid[i][j] = 0;
      }
    }
  }
}
