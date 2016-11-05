import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * The HashMapOfIntegerAndHashSet class contains methods that demonstrate
 * functionalities of the HashMap data structure.
 * 
 * @author Tai
 */
public class HashMapOfIntegerAndHashSet {

  /**
   * The insert method inserts a value element in the HashMap with chosen key.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param hashMap
   *          a HashMap
   */
  public void insert(Scanner scanner, HashMap<Integer, HashSet<Integer>> hashMap) {
    System.out.print("\nPlease enter a cell number: ");
    int key = scanner.nextInt();
    System.out.println(hashMap.get(key));

    System.out.print("Please enter a value: ");
    int value = scanner.nextInt();

    hashMap.get(key).add(value);

    System.out.println(hashMap);
  }

  /**
   * The remove method removes a value element from the HashMap with chosen key.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param hashMap
   *          a HashMap
   */
  public void remove(Scanner scanner, HashMap<Integer, HashSet<Integer>> hashMap) {
    System.out.print("\nPlease enter a cell number: ");
    int key = scanner.nextInt();
    System.out.println(hashMap.get(key));

    System.out.print("Please enter a value: ");
    int value = scanner.nextInt();

    hashMap.get(key).remove(value);

    System.out.println(hashMap);
  }

  /**
   * The update method takes the user input and overwrites the value at key in
   * the HashMap.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param hashMap
   *          a HashMap
   */
  public void update(Scanner scanner, HashMap<Integer, HashSet<Integer>> hashMap) {
    System.out.print("\nPlease enter a cell number: ");
    int key = scanner.nextInt();
    System.out.println(hashMap.get(key));

    System.out.print("Please enter a value: ");
    int value = scanner.nextInt();

    hashMap.get(key).clear();
    hashMap.get(key).add(value);

    System.out.println(hashMap);
  }

  /**
   * The delete method deletes the value at key in the HashMap.
   * 
   * @param scanner
   *          the scanner that takes user input
   * @param hashMap
   *          a HashMap
   */
  public void delete(Scanner scanner, HashMap<Integer, HashSet<Integer>> hashMap) {
    System.out.print("\nPlease enter a cell number: ");
    int key = scanner.nextInt();
    System.out.println(hashMap.get(key));

    hashMap.get(key).clear();

    System.out.println(hashMap);
  }

  /**
   * The deleteAll method deletes all values of all keys in the HashMap.
   * 
   * @param hashMap
   */
  public void deleteAll(HashMap<Integer, HashSet<Integer>> hashMap) {
    for (int i = 0; i < 81; i++) {
      hashMap.get(i).clear();
    }
    System.out.println("\n" + hashMap);
  }
}
