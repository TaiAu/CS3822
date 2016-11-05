import java.util.Scanner;

/**
 * The Array class contains methods that can demonstrate functionality of the
 * Array data structure.
 */
public class Array {
  /**
   * The empty method sets the array elements to 0.
   * 
   * @param DIM
   *          the dimension of the array
   * @param table
   *          the array
   */
  public void empty(int DIM, int[] table) {
    for (int i = 0; i < DIM; i++) {
      table[i] = 0;
    }
  }

  /**
   * The print method prints the array out in Sudoku 9x9 grid format.
   * 
   * @param DIM
   *          the dimension of the array
   * @param table
   *          the array
   */
  public void print(int DIM, int[] table) {
    System.out.println();
    for (int cell = 0; cell < DIM; cell++) {
      System.out.print("[" + table[cell] + "]");
      if (cell == 8 || cell == 17 || cell == 26 || cell == 35 || cell == 44 || cell == 53 || cell == 62 || cell == 71
          || cell == 80) {
        System.out.println();
      }
    }
    System.out.println();
  }

  /**
   * The insert method inserts an element (1-9) to the array at chosen index.
   * 
   * @param table
   *          the array
   * @param scanner
   *          the scanner that takes user input
   */
  public void insert(int[] table, Scanner scanner) {
    System.out.print("Please enter a cell coordinate: ");
    int x = scanner.nextInt();
    System.out.print("Please enter value (1-9): ");
    int v = scanner.nextInt();
    if (v > 0 && v < 10) {
      table[x] = v;
    } else {
      System.out.println("Please enter value (1-9)!");
    }
  }

  /**
   * The remove method removes the element at chosen index and sets it to 0.
   * 
   * @param table
   *          the array
   * @param scanner
   *          the scanner that takes user input
   */
  public void remove(int[] table, Scanner scanner) {
    System.out.print("Please enter a cell coordinate: ");
    int x = scanner.nextInt();
    table[x] = 0;
  }
}
