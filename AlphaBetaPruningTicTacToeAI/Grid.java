import java.util.Random;

/**
 * The Tic-Tac-Toe AI that never loses with alpha beta search tree pruning.
 * 
 * The Game class contains a main method that starts the Tic-Tac-Toe game and
 * alpha beta search tree pruning algorithm based upon user input.
 * 
 * @author Tai (Shiu Hung Au)
 */
public class Game {

  /**
   * The main method of the Game class runs an indefinite while loop that first
   * asks the player whether he or she wants to move first: A player may enter
   * "1" if he or she wants to move first and "2" otherwise.
   * <p>
   * The Tic-Tac-Toe 2D array starts with 0s assigned to its [x][y] array slots,
   * which is represented as an empty space enclosed by square brackets: "[ ]".
   * The Tic-Tac-Toe AI gets assigned as 1 and the user 2; they are represented
   * as "X" and "O" respectively.
   * <p>
   * The program will ask the user for an x and y coordinate input from 0 to 2
   * each to determine where to place an "O". Once a user makes a coordinate
   * input, the program will update the respective coordinate cell with such,
   * unless the coordinate cell is already occupied of course.
   * <p>
   * The Tic-Tac-Toe AI will then place the best move possible after an
   * exhaustive tree search with alpha beta search tree pruning (used to
   * minimize the number of tree nodes required to be evaluated).
   * <p>
   * The process above will repeat until the player loses or draws (when all the
   * cells are taken up), in which case the game ends and re-loops. The
   * Tic-Tac-Toe program can never and will never lose.
   * 
   * @param args
   *          console line argument
   */
  public static void main(String[] args) {
    while (true) {
      Grid grid = new Grid();
      Random random = new Random();

      System.out.print("Who moves first? [1]Computer(X) [2]User(O): ");
      int turn = grid.scanner.nextInt();
      if (turn < 1 || turn > 2) {
        continue;
      }

      grid.printGrid();

      if (turn == 1) {
        Cell p = new Cell(random.nextInt(3), random.nextInt(3));
        grid.move(p, 1);
        grid.printGrid();
      }

      while (!grid.gameOver()) {
        int x = 0, y = 0;

        System.out.print("Please enter an x coordinate [0-2]: ");
        x = grid.scanner.nextInt();
        if (x < 0 || x > 2) {
          continue;
        }

        System.out.print("Please enter an y coordinate [0-2]: ");
        y = grid.scanner.nextInt();
        if (y < 0 || y > 2) {
          continue;
        }

        Cell userMove = new Cell(y, x);

        if (grid.isMoveOK(userMove)) {
          grid.move(userMove, 2);
        } else {
          System.out.println("\nCell Taken: Please try a different coordinate!");
          continue;
        }

        grid.printGrid();

        if (grid.gameOver()) {
          break;
        }

        grid.savePreviousCount();
        grid.minimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);

        for (CellsAndScores cellsAndScore : grid.listOfScores) {
          System.out.println("Cell: " + cellsAndScore.cell + " Score: " + cellsAndScore.score);
        }
        grid.move(grid.bestMove(), 1);
        grid.printTreeSize();
        grid.printGrid();
      }

      if (grid.xWins()) {
        System.out.println("You Lose!\n");
        Grid.resetGrid();
      } else if (grid.oWins()) {
        System.out.println("You win!\n");
        Grid.resetGrid();
      } else {
        System.out.println("Draw!\n");
        Grid.resetGrid();
      }
    }
  }
}
