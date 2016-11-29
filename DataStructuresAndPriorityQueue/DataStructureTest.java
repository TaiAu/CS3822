import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * The DataStructureTest class contains the main method to the data structure
 * proof of concept program and is an interactive demonstration of data
 * structures and their functionality. The data structures considered were
 * potential candidates to the final Sudoku solving algorithm's data structure.
 * Considered data structures include: Array, 2D Array, 3D Array (for 3D
 * Sudoku), The optimal data structure for the best worst case running time is
 * the DoublyLinkedList and the optimal data structure for the best average case
 * running time is the HashMap<Integer, HashSet<Integer>>.
 * 
 * @author Tai (Shiu Hung Au)
 *
 */
public class DataStructureTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			do {
				System.out.println("[1] Array");
				System.out.println("[2] 2D Array");
				System.out.println("[3] 3D Array for 3D Sudoku");
				System.out.println("[4] Priority Queue");
				System.out.println("[5] HashSet");
				System.out.println("[6] ArrayList<HashSet<Integer>>");
				System.out.println("[7] HashMap<Integer, HashSet<Integer>>");
				System.out.println("[8] Dancing Links");
				System.out.print("Please enter a number representing its respective data structure: ");

				int num = scanner.nextInt();
				switch (num) {

				// ==================== Array ====================
				case 1:
					Array oneDimensionalArray = new Array();

					System.out.print("\n=== Array Test ===\n");

					int dim = 81;
					int[] cells = new int[dim];

					oneDimensionalArray.print(dim, cells);

					char c;
					do {
						System.out.println("1. insert cell coordinate");
						System.out.println("2. remove cell coordinate");
						System.out.println("3. empty");
						System.out.println("4. print");
						System.out.print("Please enter a number: ");

						int choice = scanner.nextInt();
						switch (choice) {
						case 1:
							oneDimensionalArray.insert(cells, scanner);
							oneDimensionalArray.print(dim, cells);
							break;
						case 2:
							oneDimensionalArray.remove(cells, scanner);
							oneDimensionalArray.print(dim, cells);
							break;
						case 3:
							oneDimensionalArray.empty(dim, cells);
							oneDimensionalArray.print(dim, cells);
							break;
						case 4:
							oneDimensionalArray.print(dim, cells);
							break;
						default:
							System.out.println("\nPlease enter a valid number!");
							break;
						}
						System.out.print("Do you want to continue? (y or n): ");
						c = scanner.next().charAt(0);
					} while ((c == 'Y' || c == 'y') && (c != 'N' || c != 'n'));
					break;

				// ==================== 2D Array ====================
				case 2:
					TwoDimensionalArray twoDimensionalArray = new TwoDimensionalArray();

					System.out.println("\n=== 2D Array Test ===\n");
					System.out.print("Enter dimensions of 2D Array: ");
					int DIM = scanner.nextInt();
					int[][] grid = new int[DIM][DIM];

					twoDimensionalArray.print(DIM, grid);

					do {
						System.out.println("\n1. insert [x][y] coordinate");
						System.out.println("2. remove [x][y] coordinate");
						System.out.println("3. empty");
						System.out.println("4. print");
						System.out.print("Please enter a number: ");

						int choice = scanner.nextInt();
						switch (choice) {
						case 1:
							twoDimensionalArray.insert(grid, scanner);
							twoDimensionalArray.print(DIM, grid);
							break;
						case 2:
							twoDimensionalArray.remove(grid, scanner);
							twoDimensionalArray.print(DIM, grid);
							break;
						case 3:
							twoDimensionalArray.empty(DIM, grid);
							twoDimensionalArray.print(DIM, grid);
							break;
						case 4:
							twoDimensionalArray.print(DIM, grid);
							break;
						default:
							System.out.println("\nPlease enter a valid number!");
							break;
						}
						System.out.print("\nDo you want to continue? (y or n): ");
						c = scanner.next().charAt(0);
					} while ((c == 'Y' || c == 'y') && (c != 'N' || c != 'n'));
					break;

				// ==================== 3D Array ====================
				case 3:
					ThreeDimensionalArray threeDimensionalArray = new ThreeDimensionalArray();

					System.out.println("\n=== 3D Array Test ===\n");
					System.out.print("Enter dimensions of 3D Array: ");
					int dimensions = scanner.nextInt();
					int[][][] cube = new int[dimensions][dimensions][dimensions];

					threeDimensionalArray.print(dimensions, cube);

					do {

						System.out.println("1. insert [x][y][z] coordinate");
						System.out.println("2. remove [x][y][z] coordinate");
						System.out.println("3. empty");
						System.out.println("4. print");
						System.out.print("Please enter a number: ");

						int choice = scanner.nextInt();
						switch (choice) {
						case 1:
							threeDimensionalArray.insert(cube, scanner);
							threeDimensionalArray.print(dimensions, cube);
							break;
						case 2:
							threeDimensionalArray.remove(cube, scanner);
							threeDimensionalArray.print(dimensions, cube);
							break;
						case 3:
							threeDimensionalArray.empty(dimensions, cube);
							threeDimensionalArray.print(dimensions, cube);
							break;
						case 4:
							threeDimensionalArray.print(dimensions, cube);
							break;
						default:
							System.out.println("\nPlease enter a valid number!");
							break;
						}
						System.out.print("Do you want to continue? (y or n): ");
						c = scanner.next().charAt(0);
					} while ((c == 'Y' || c == 'y') && (c != 'N' || c != 'n'));
					break;

				// ==================== PriorityQueue ====================
				case 4:
					System.out.println("\n=== Priority Queue Test ===\n");

					PriorityQueue<Task> p = new PriorityQueue<Task>();

					System.out.println("QUEUE");
					System.out.println("The following demo shall order the priority queue by priority number: ");

					System.out.print("How many HashSets do you want to queue? ");
					int numberOfTimes = scanner.nextInt();

					for (int i = 0; i < numberOfTimes; i++) {
						System.out.print("Please enter a priority number for HashSet " + (i + 1) + ": ");
						int setSize = scanner.nextInt();
						p.add(new Task(setSize, new HashSet<Integer>()));
					}

					System.out.println("\nDEQUEUE");

					while (!p.isEmpty()) {
						System.out.println(p.remove());
					}

					System.out.println("\nIs the Priority Queue empty now? " + p.isEmpty() + "\n");

					///////////////////////////////////////

					HashSetQueue pq = new HashSetQueue(81);

					do {
						System.out.println("=== MAX HEAP PRIORITY QUEUE ===\n");
						System.out.println("1. insert");
						System.out.println("2. empty");
						System.out.println("3. check empty");
						System.out.println("4. check full");
						System.out.println("5. check size");
						System.out.println("6. print in max heap order");
						System.out.print("Please enter a number: ");

						int choice = scanner.nextInt();
						switch (choice) {
						case 1:
							System.out.print("Please enter a value: ");
							int userInput = scanner.nextInt();

							HashSet<Integer> set = new HashSet<Integer>();
							set.add(userInput);

							System.out.print("Please enter a priority: ");
							int priority = scanner.nextInt();

							pq.insert(priority, set);
							pq.print();
							break;
						case 2:
							System.out.print("\nPriority Queue Cleared!\n");
							pq.clear();
							break;
						case 3:
							System.out.print("\nEmpty Status: " + pq.isEmpty() + "\n");
							break;
						case 4:
							System.out.print("\nFull Status: " + pq.isFull() + "\n");
							break;
						case 5:
							System.out.print("\nSize: " + pq.size() + "\n");
							break;
						case 6:
							pq.print();
							break;
						default:
							System.out.println("\nPlease enter a valid number!");
							break;
						}
						System.out.print("\nDo you want to continue? (y or n): ");
						c = scanner.next().charAt(0);
					} while ((c == 'Y' || c == 'y') && (c != 'N' || c != 'n'));
					break;

				// ==================== HashSet ====================
				case 5:
					System.out.println("\n=== HashSet Test ===\n");
					do {
						hashSetMagic.main(args);

						System.out.print("\nDo you want to continue? (y or n): ");
						c = scanner.next().charAt(0);
					} while ((c == 'Y' || c == 'y') && (c != 'N' || c != 'n'));
					break;

				// ==================== ArrayList<HashSet> ====================
				case 6:
					System.out.println("\n=== ArrayList<HashSet> Test ===\n");
					ArrayListOfHashSet arrayList = new ArrayListOfHashSet();

					System.out.print("Enter size of ArrayList: ");
					int size = scanner.nextInt();

					ArrayList<HashSet<Integer>> arrayListOfHashSet = new ArrayList<HashSet<Integer>>(81);

					for (int i = 0; i < size; i++) {
						arrayListOfHashSet.add(new HashSet<Integer>());
					}

					System.out.println("\n" + arrayListOfHashSet + "\n");

					do {
						System.out.println("1. insert value into HashSet in ArrayList");
						System.out.println("2. replace value in HashSet in ArrayList");
						System.out.println("3. remove a particular value in a HashSet in ArrayList");
						System.out.println("4. remove all values in a HashSet in ArrayList");
						System.out.println("5. remove HashSet in ArrayList");
						System.out.println("6. print ArrayList");
						System.out.print("Please enter a number: ");

						int choice = scanner.nextInt();
						switch (choice) {
						case 1:
							arrayList.insert(scanner, arrayListOfHashSet);
							break;
						case 2:
							arrayList.update(scanner, arrayListOfHashSet);
							break;
						case 3:
							arrayList.removeValue(scanner, arrayListOfHashSet);
							break;
						case 4:
							arrayList.removeAllValues(scanner, arrayListOfHashSet);
							break;
						case 5:
							arrayList.removeHashSet(scanner, arrayListOfHashSet);
							break;
						case 6:
							System.out.println("\n" + Arrays.toString(arrayListOfHashSet.toArray()));
							break;
						default:
							System.out.println("\nPlease enter a valid number!");
							break;
						}
						System.out.print("\nDo you want to continue? (y or n): ");
						c = scanner.next().charAt(0);
					} while ((c == 'Y' || c == 'y') && (c != 'N' || c != 'n'));
					break;

				// ========== HashMap<Integer, HashSet<Integer>> ==========
				case 7:
					HashMapOfIntegerAndHashSet hashBrown = new HashMapOfIntegerAndHashSet();

					System.out.println("\n=== HashMap<Integer, HashSet<Integer>> Test ===\n");
					HashMap<Integer, HashSet<Integer>> hashMap = new HashMap<Integer, HashSet<Integer>>(81);

					for (int i = 0; i < 81; i++) {
						hashMap.put(i, new HashSet<Integer>());
					}

					System.out.println(hashMap + "\n");

					do {
						System.out.println("1. insert into cell");
						System.out.println("2. remove from cell");
						System.out.println("3. update cell");
						System.out.println("4. empty cell");
						System.out.println("5. empty all cells");
						System.out.println("6. print grid");
						System.out.print("Please enter a number: ");

						int choice = scanner.nextInt();
						switch (choice) {
						case 1:
							hashBrown.insert(scanner, hashMap);
							break;
						case 2:
							hashBrown.remove(scanner, hashMap);
							break;
						case 3:
							hashBrown.update(scanner, hashMap);
							break;
						case 4:
							hashBrown.delete(scanner, hashMap);
							break;
						case 5:
							hashBrown.deleteAll(hashMap);
							break;
						case 6:
							System.out.println("\n" + hashMap);
							break;
						default:
							System.out.println("\nPlease enter a valid number!");
							break;
						}
						System.out.print("\nDo you want to continue? (y or n): ");
						c = scanner.next().charAt(0);
					} while ((c == 'Y' || c == 'y') && (c != 'N' || c != 'n'));
					break;

				// ==================== Dancing Links ====================
				case 8:
					System.out.println("\n=== Dancing Links Test ===\n");

					System.out.println(
							"Dancing Links implements Professor Kunth's Algorithm X, \nwhich is a recursive, nondeterministic, depth-first, backtracking \nalgorithm that finds all solutions to the exact cover problem.\nDancing Links uses the DoublyLinkedList data structure.\n");

					do {
						System.out.println("1. see LinkedList structure");
						System.out.println("2. see Dancing Links (DoublyLinkedLists) unlinking and relinking");
						System.out.print("Please enter a number: ");

						int choice = scanner.nextInt();
						switch (choice) {

						case 1:
							LinkedList<HashSet<Integer>> ll = new LinkedList<HashSet<Integer>>();

							for (int i = 0; i < 81; i++) {
								HashSet<Integer> set = new HashSet<Integer>();
								set.add(i);
								ll.add(i, set);
							}

							System.out.println("\n" + ll);
							break;

						case 2:
							DoublyLinkedList<HashSet<Integer>> newList = new DoublyLinkedList<HashSet<Integer>>();

							for (int i = 0; i < 81; i++) {
								HashSet<Integer> set = new HashSet<Integer>();
								set.add(i);
								newList.addFirst(set);
							}

							System.out.println("\nCurrent DoublyLinkedList (each node represents a Sudoku cell):");
							newList.displayList();

							System.out.print("\nEnter LinkedList Node number to unlink (e.g. 4 = node [77]: ");
							int userInput = scanner.nextInt();

							// Gets user input
							Node<HashSet<Integer>> node = newList.getAt(userInput);
							System.out.print("Element at chosen node is: ");
							node.displayNode();
							System.out.println();

							// UNLINK

							double start = System.nanoTime();

							node.previous().setNext(node.next);
							node.next().setPrevious(node.previous());

							double stop = System.nanoTime();
							Double elapsed = stop - start;
							int elapsedInt = elapsed.intValue();

							// Node p = node.previous();
							// Node n = node.next();
							// p.setNext(n);
							// n.setPrevious(p);

							System.out.println("\nLinkedList Node " + userInput + " UNLINKED:");
							newList.displayList();
							System.out.println("Unlinking took: " + elapsed / 1000000000 + " seconds (or " + elapsedInt
									+ " nanoseconds)!");

							// REVERT UNLINK

							double startTime = System.nanoTime();

							node.previous().setNext(node);
							node.next().setPrevious(node);

							double stopTime = System.nanoTime();
							Double elapsedTime = stopTime - startTime;
							int elapsedTimeInt = elapsedTime.intValue();

							System.out.println("\nLinkedList Node " + userInput + " RELINKED:");
							newList.displayList();
							System.out.println("Relinking took: " + elapsedTime / 1000000000 + " seconds (or "
									+ elapsedTimeInt + " nanoseconds)!");
							break;

						default:
							System.out.println("\nPlease enter a valid number on the list!");
							break;
						}
						System.out.print("\nDo you want to continue? (y or n): ");
						c = scanner.next().charAt(0);
					} while ((c == 'Y' || c == 'y') && (c != 'N' || c != 'n'));
					break;

				default:
					System.out.println("\nPlease enter a valid number!");
				}
			} while (true);
		} catch (Exception e) {
			System.out.println("\nPlease enter a valid number on the list!\n");
			main(args);
		}
	}
}
