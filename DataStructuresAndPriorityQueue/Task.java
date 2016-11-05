import java.util.HashSet;

/**
 * The Task class implements a Comparable Task and contains methods relevant to
 * Task.
 * 
 * @author Tai
 *
 */
class Task implements Comparable<Task> {
  final int priority;
  final HashSet<Integer> hashSet;

  /**
   * The Task constructor sets priority and hashSet to its respective
   * parameters.
   * 
   * @param p
   *          the priority
   * @param h
   *          a HashSet
   */
  public Task(int p, HashSet<Integer> h) {
    priority = p;
    hashSet = h;
  }

  /**
   * The toString method returns a textual representation of priority and
   * hashSet
   */
  public String toString() {
    return "Priority " + priority + ": " + hashSet;
  }

  /**
   * the compareTo method compares one task to another and returns an integer to
   * help order the priority queue.
   */
  public int compareTo(Task other) {
    return priority < other.priority ? -1 : priority > other.priority ? 1 : 0;
  }
}
