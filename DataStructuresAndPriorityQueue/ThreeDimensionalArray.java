package attempt1;


import java.util.Scanner;

/**
 * The ThreeDimensionalArray class contains methods that demonstrate the
 * functionality of the 3D Array data structure.
 * 
 * @author Tai
 */
public class ThreeDimensionalArray {
  public void empty(int DIM, int[][][] cube) {
    for (int x = 0; x < DIM; x++) {
      for (int y = 0; y < DIM; y++) {
        for (int z = 0; z < DIM; z++) {
          cube[x][y][z] = 0;
        }
      }
    }
  }

  /**
   * The print method prints the contents of the 3D array.
   * 
   * @param DIM
   *          the dimensions of the 3D array
   * @param table
   *          the 3D integer array
   */
  public void print(int DIM, int[][][] cube) {
    System.out.println();
    for (int x = 0; x < DIM; x++) {
      for (int y = 0; y < DIM; y++) {
        for (int z = 0; z < DIM; z++) {
          System.out.print("[" + cube[x][y][z] + "]");
        }
        System.out.println();
      }
      System.out.println();
    }
  }

  /**
   * The insert method inserts the user input element into the 3D array at
   * coordinate (x, y, z).
   * 
   * @param table
   *          the 3D array
   * @param scanner
   *          the scanner that takes user input
   */
  public void insert(int[][][] cube, Scanner scanner) {
    System.out.print("Please enter an x coordinate: ");
    int x = scanner.nextInt();

    System.out.print("Please enter an y coordinate: ");
    int y = scanner.nextInt();

    System.out.print("Please enter an z coordinate: ");
    int z = scanner.nextInt();

    System.out.print("Please enter value (1-9): ");
    int v = scanner.nextInt();
    if (v > 0 && v < 10) {
      cube[x][y][z] = v;
    } else {
      System.out.println("Please enter value (1-9)!");
    }
  }

  /**
   * The remove method removes the user input element from the 3D array at
   * coordinate (x, y, z).
   * 
   * @param table
   *          the 3D array
   * @param scanner
   *          the scanner that takes user input
   */
  public void remove(int[][][] cube, Scanner scanner) {
    System.out.print("Please enter an x coordinate: ");
    int x = scanner.nextInt();

    System.out.print("Please enter an y coordinate: ");
    int y = scanner.nextInt();

    System.out.print("Please enter an z coordinate: ");
    int z = scanner.nextInt();

    cube[x][y][z] = 0;
  }
}
