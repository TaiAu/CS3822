import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * The ArrayListOfHashSet class contains methods that demonstrate
 * functionalities of the ArrayList data structure.
 * 
 * @author Tai
 */
public class ArrayListOfHashSet {
  /**
   * The insert method inserts an element into the ArrayList at chosen index.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param arrayListOfHashSet
   *          the HashSet ArrayList
   */
  public void insert(Scanner scanner, ArrayList<HashSet<Integer>> arrayListOfHashSet) {
    System.out.println("\n" + arrayListOfHashSet);
    System.out.print("Please enter an index: ");
    int index = scanner.nextInt();

    HashSet<Integer> hashSet = arrayListOfHashSet.get(index);
    System.out.println("\n" + arrayListOfHashSet);

    System.out.print("Please enter a value: ");
    int userInput = scanner.nextInt();

    hashSet.add(userInput);
    arrayListOfHashSet.set(index, hashSet);
    System.out.println("\n" + arrayListOfHashSet);
  }

  /**
   * The update method updates the element in ArrayList at chosen index.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param arrayListOfHashSet
   *          the HashSet ArrayList
   */
  public void update(Scanner scanner, ArrayList<HashSet<Integer>> arrayListOfHashSet) {
    System.out.println("\n" + arrayListOfHashSet);
    HashSet<Integer> hashSet = new HashSet<Integer>();

    System.out.print("Please enter an index: ");
    int index = scanner.nextInt();
    System.out.println("\n" + arrayListOfHashSet);

    System.out.print("Please enter a value: ");
    int userInput = scanner.nextInt();

    hashSet.add(userInput);
    arrayListOfHashSet.set(index, hashSet);
    System.out.println("\n" + arrayListOfHashSet);
  }

  /**
   * The removeAllValues method removes all elements from the HashSet at chosen
   * index in the ArrayList.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param arrayListOfHashSet
   *          the HashSet ArrayList
   */
  public void removeAllValues(Scanner scanner, ArrayList<HashSet<Integer>> arrayListOfHashSet) {
    System.out.println("\n" + arrayListOfHashSet);
    HashSet<Integer> emptyHashSet = new HashSet<Integer>();

    System.out.print("Please enter an index: ");
    int x = scanner.nextInt();

    arrayListOfHashSet.set(x, emptyHashSet);
    System.out.println("\n" + arrayListOfHashSet);
  }

  /**
   * The removeHashSet method removes the HashSet at chosen index from the
   * ArrayList.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param arrayListOfHashSet
   *          the HashSet ArrayList
   */
  public void removeHashSet(Scanner scanner, ArrayList<HashSet<Integer>> arrayListOfHashSet) {
    System.out.println("\n" + arrayListOfHashSet);
    System.out.print("Please enter an index: ");
    int index = scanner.nextInt();

    HashSet<Integer> hashSet = arrayListOfHashSet.get(index);
    System.out.println("\n" + arrayListOfHashSet);

    hashSet.remove(index);
    arrayListOfHashSet.remove(index);
    System.out.println("\n" + arrayListOfHashSet);
  }

  /**
   * The removeValue method removes the chosen value from the HashSet at chosen
   * index from the ArrayList.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param arrayListOfHashSet
   *          the HashSet ArrayList
   */
  public void removeValue(Scanner scanner, ArrayList<HashSet<Integer>> arrayListOfHashSet) {
    System.out.println("\n" + arrayListOfHashSet);
    System.out.print("Please enter an index: ");
    int index = scanner.nextInt();

    System.out.print("Please enter a value: ");
    int value = scanner.nextInt();

    HashSet<Integer> hashSet = arrayListOfHashSet.get(index);
    System.out.println("\n" + arrayListOfHashSet);

    hashSet.remove(value);
    System.out.println("\n" + arrayListOfHashSet);
  }
}
