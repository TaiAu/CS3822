import java.util.HashSet;

/**
 * The HashSetMagic class contains methods that demonstrate functionalities of
 * the HashSet data structure.
 * 
 * @author Tai
 */
public class hashSetMagic {

  /**
   * The displayDistinctNumbers method adds all elements in setB to setA and the
   * result of set A is all distinct numbers in both sets.
   * 
   * @param setA
   *          a HashSet
   * @param setB
   *          a HashSet
   */
  public static void displayDistinctNumbers(HashSet<Integer> setA, HashSet<Integer> setB) {
    setA.addAll(setB);
    System.out.println(setA);
  }

  /**
   * The countDistinctNumbers method prints the number of non-repeated numbers
   * in both setA and setB.
   * 
   * @param setA
   * @param setB
   */
  private static void countDistinctNumbers(HashSet<Integer> setA, HashSet<Integer> setB) {
    setA.removeAll(setB);
    System.out.println("Set A and B contain " + setA.size() + " non-repeated numbers: " + setA);
  }

  /**
   * The main method of the HashSet demonstration sets up the arbitrary data and
   * performs HashSet arithmetic.
   * 
   * @param args
   *          the console line arguments
   */
  public static void main(String[] args) {
    HashSet<Integer> setA = new HashSet<Integer>();
    HashSet<Integer> setB = new HashSet<Integer>();
    setA.add(1);
    setA.add(2);
    setA.add(3);
    setA.add(4);
    setA.add(5);
    setA.add(6);
    setA.add(7);
    setA.add(8);
    setA.add(9);
    setB.add(2);
    setB.add(3);
    setB.add(5);
    setB.add(7);

    System.out.println("Set A contains: " + setA);
    System.out.println("Set B contains: " + setB);

    countDistinctNumbers(setA, setB);

    System.out.print("Set A and B contain these distinct numbers: ");
    displayDistinctNumbers(setA, setB);
  }
}
