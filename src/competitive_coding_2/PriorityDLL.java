package competitive_coding_2;
import java.util.*;

class PriorityDLL {

  static class Node {
    int data;
    int priority;
    Node next, prev;

    public Node(int data, int priority) {
      this.data = data;
      this.priority = priority;
    }
  }

  private static Node head = null;

  // Insert element based on priority (descending order)
  private static void push(int data, int priority) {
    Node node = new Node(data, priority);

    // Case 1: Empty list
    if (head == null) {
      head = node;
      return;
    }

    Node temp = head;
    Node parent = null;

    // Find insertion position
    while (temp != null && temp.priority >= priority) {
      parent = temp;
      temp = temp.next;
    }

    // Case 2: Insert at beginning
    if (parent == null) {
      node.next = head;
      head.prev = node;
      head = node;
    }
    // Case 3: Insert at end
    else if (temp == null) {
      parent.next = node;
      node.prev = parent;
    }
    // Case 4: Insert in middle
    else {
      parent.next = node;
      node.prev = parent;
      node.next = temp;
      temp.prev = node;
    }
  }

  // View highest priority element
  private static int peek() {
    return (head != null) ? head.data : -1;
  }

  // Remove and return highest priority element
  private static int pop() {
    if (head == null) {
      return -1;
    }

    int value = head.data;
    head = head.next;

    if (head != null) {
      head.prev = null;
    }

    return value;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    for (int i = 0; i < n; i++) {
      int data = sc.nextInt();
      int priority = sc.nextInt();
      push(data, priority);
    }

    System.out.println(peek());
    System.out.println(pop());
    System.out.println(pop());
    System.out.println(peek());

    sc.close();
  }
}

