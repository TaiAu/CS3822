package attempt1;


import java.util.Scanner;

/**
 * The TwoDimensionalArray class contains method that demonstrate the
 * functionality of the 2D Array data structure.
 * 
 * @author Tai
 */
public class TwoDimensionalArray {

  public void empty(int DIM, int[][] table) {
    for (int row = 0; row < DIM; row++) {
      for (int col = 0; col < DIM; col++) {
        table[row][col] = 0;
      }
    }
  }

  /**
   * The print method prints the contents of the 2D array.
   * 
   * @param DIM
   *          the dimensions of the array
   * @param table
   *          the 2D array
   */
  public void print(int DIM, int[][] table) {
    System.out.println();
    for (int row = 0; row < DIM; row++) {
      for (int col = 0; col < DIM; col++) {
        System.out.print("[" + table[row][col] + "]");
      }
      System.out.println();
    }
  }

  /**
   * The insert method inserts the user input element in the 2D array at
   * coordinate (x, y, z).
   * 
   * @param table
   *          the 2D array
   * @param scanner
   *          the scanner that takes user input
   */
  public void insert(int[][] table, Scanner scanner) {
    System.out.print("Please enter an x coordinate: ");
    int x = scanner.nextInt();

    System.out.print("Please enter an y coordinate: ");
    int y = scanner.nextInt();

    System.out.print("Please enter value (1-9): ");
    int v = scanner.nextInt();
    if (v > 0 && v < 10) {
      table[x][y] = v;
    } else {
      System.out.println("Please enter value (1-9)!");
    }
  }

  /**
   * The remove method removes the user input element from the 2D array at
   * coordinate (x, y, z).
   * 
   * @param table
   *          the 2D array
   * @param scanner
   *          the scanner that takes user input
   */
  public void remove(int[][] table, Scanner scanner) {
    System.out.print("Please enter an x coordinate: ");
    int x = scanner.nextInt();

    System.out.print("Please enter an y coordinate: ");
    int y = scanner.nextInt();

    table[x][y] = 0;
  }
}
