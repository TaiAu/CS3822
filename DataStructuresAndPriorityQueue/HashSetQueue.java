import java.util.HashSet;

/**
 * The Job class contains methods relevant to a Job, which contains an integer
 * HashSet and a priority number.
 *
 * @author Tai
 */
class Job {
  int priority;
  HashSet<Integer> hashSet;

  /**
   * The Job constructor sets the hashSet and priority variables to their
   * respective arguments.
   * 
   * @param priority
   *          the priority
   * @param hashSet
   *          a HashSet
   */
  public Job(int priority, HashSet<Integer> hashSet) {
    this.hashSet = hashSet;
    this.priority = priority;
  }

  /**
   * The toString method returns a textual representation of priority and
   * hashSet data.
   */
  public String toString() {
    return "\nPriority : " + priority + "\nHashSet : " + hashSet;
  }
}

/**
 * The HashSetQueue class contains methods that demonstrate the functionality of
 * the HashSet data structure.
 */
class HashSetQueue {
  private Job[] heap;
  private int heapSize, capacity;

  /**
   * The HashSetQueue method increments the capacity and creates a new Job in
   * the heap.
   * 
   * @param capacity
   *          the capacity of the priority queue.
   */
  public HashSetQueue(int capacity) {
    this.capacity = capacity++;
    heap = new Job[this.capacity];
    heapSize = 0;
  }

  /**
   * The clear method clears the heap by overwriting it with a new Job.
   */
  public void clear() {
    heap = new Job[capacity];
    heapSize = 0;
  }

  /**
   * The isEmpty method returns a boolean value indicating whether the heap is
   * empty.
   * 
   * @return <code>true</code> if empty and <code>false</code> otherwise
   */
  public boolean isEmpty() {
    return heapSize == 0;
  }

  /**
   * The isFull method returns a boolean value indicating whether the heap is
   * full.
   * 
   * @return <code>true</code> if true <code>false</code> otherwise
   */
  public boolean isFull() {
    return heapSize == capacity - 1;
  }

  /**
   * The size method returns the heap size.
   */
  public int size() {
    return heapSize;
  }

  /**
   * The print method prints out the heap.
   */
  public void print() {
    Job item;

    for (int i = 1; i <= heapSize; i++) {
      item = heap[i];
      System.out.println(item);
    }
  }

  /**
   * The insert method inserts a HashSet with respective priority into the
   * correct position in the priority queue based upon the priority.
   * 
   * @param priority
   *          the priority
   * @param hashSet
   *          a HashSet
   */
  public void insert(int priority, HashSet<Integer> hashSet) {
    Job newHashSet = new Job(priority, hashSet);

    heap[++heapSize] = newHashSet;
    int pos = heapSize;
    while (pos != 1 && newHashSet.priority > heap[pos / 2].priority) {
      heap[pos] = heap[pos / 2];
      pos /= 2;
    }
    heap[pos] = newHashSet;
  }
}
