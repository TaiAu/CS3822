package attempt1;

/**
 * The DoublyLinkedList class contains methods that demonstrate functionalities
 * of the DoublyLinkedList data structure.
 * 
 * @param <T>
 *          the node element
 * @author Tai
 */
public class DoublyLinkedList<T> {

  public Node<T> first = null;
  public Node<T> last = null;

  /**
   * The Node class contains methods related to the Node.
   *
   * @param <T>
   *          the node element
   */
  static class Node<T> {
    private T data;
    Node<T> next;
    private Node<T> prev;

    public Node(T data) {
      this.data = data;
    }

    /**
     * The get method returns the node.
     * 
     * @return the node
     */
    public Node<T> get() {
      return this;
    }

    /**
     * The set method sets the node.
     * 
     * @param node
     *          a node
     * @return the node
     */
    public Node<T> set(Node<T> node) {
      return node;
    }

    /**
     * The next method returns the next node.
     * 
     * @return the next node
     */
    public Node<T> next() {
      return next;
    }

    /**
     * The setNext method sets the next node to current node.
     * 
     * @param node
     *          a node
     */
    public void setNext(Node<T> node) {
      next = node;
    }

    /**
     * The previous method returns the previous node.
     * 
     * @return the previous node
     */
    public Node<T> previous() {
      return prev;
    }

    /**
     * The setPrevious method sets the previous node to the current node.
     * 
     * @param node
     *          a node
     */
    public void setPrevious(Node<T> node) {
      prev = node;
    }

    /**
     * The displayNode method prints out the data in the Node.
     */
    public void displayNode() {
      System.out.print(data + " ");
    }

    /**
     * The toString method returns a textual representation of the data in the
     * Node.
     */
    public String toString() {
      return data.toString();
    }
  }

  /**
   * The addFirst method creates a new node and adds it to the front of the
   * DoublyLinkedList.
   * 
   * @param data
   *          the node element
   */
  public void addFirst(T data) {
    Node<T> newNode = new Node<T>(data);

    if (isEmpty()) {
      newNode.next = null;
      newNode.prev = null;
      first = newNode;
      last = newNode;

    } else {
      first.prev = newNode;
      newNode.next = first;
      newNode.prev = null;
      first = newNode;
    }
  }

  /**
   * The getAt method gets the node at target index.
   * 
   * @param index
   *          the index
   * @return the current node
   */
  public Node<T> getAt(int index) {
    Node<T> current = first;
    int i = 1;
    while (i < index) {
      current = current.next;
      i++;
    }
    return current;
  }

  /**
   * The isEmpty method returns a boolean value indicating whether the first
   * node is empty.
   * 
   * @return <code>true</code> if empty and <code>false</code> otherwise
   */
  public boolean isEmpty() {
    return (first == null);
  }

  /**
   * The displayList method prints out all the list nodes.
   */
  public void displayList() {
    Node<T> current = first;
    while (current != null) {
      current.displayNode();
      current = current.next;
    }
    System.out.println();
  }

  /**
   * The removeFirst method removes the first node from the list.
   */
  public void removeFirst() {
    if (!isEmpty()) {
      Node<T> temp = first;

      if (first.next == null) {
        first = null;
        last = null;
      } else {
        first = first.next;
        first.prev = null;
      }
      System.out.println(temp.toString() + " is popped from the list");
    }
  }

  /**
   * The removeLast method removes the last node from the list.
   */
  public void removeLast() {
    Node<T> temp = last;

    if (!isEmpty()) {

      if (first.next == null) {
        first = null;
        last = null;
      } else {
        last = last.prev;
        last.next = null;
      }
    }
    System.out.println(temp.toString() + " is popped from the list");
  }
}
