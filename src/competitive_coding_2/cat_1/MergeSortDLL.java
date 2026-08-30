package competitive_coding_2.cat_1;

public class MergeSortDLL {
  public static class Node {
    int value;
    Node prev, next;

    Node(int num){
      value = num;
      prev = next = null;
    }
  }

  private Node head;
  private Node tail;

  public MergeSortDLL(){
    head = tail = null;
  }

  public MergeSortDLL(int[] nums){
    for (int num: nums) insert(num);
  }

  public static Node merge(Node left, Node right){
    if (left == null) return right;
    if (right == null) return left;
    if (left.value <= right.value){
      left.next = merge(left.next, right);
      if (left.next != null) left.next.prev = left;
      return left;
    } else {
      right.next = merge(left, right.next);
      if (right.next != null) right.next.prev = right;
      return right;
    }
  }

  private static Node getMiddle(Node head){
    if (head == null) return null;
    Node slow = head;
    Node fast = head.next;
    while (fast != null && fast.next != null){
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }

  public static Node mergeSort(Node node){
    if (node == null || node.next == null) return node;

    Node middle = getMiddle(node);
    Node nextOfMiddle = middle.next;

    middle.next = null;
    nextOfMiddle.prev = null;

    Node left = mergeSort(node);
    Node right = mergeSort(nextOfMiddle);
    return merge(left, right);
  }

  public void print(){
    Node node = head;
    while (node != null){
      System.out.printf("%d ", node.value);
      node = node.next;
    }
    System.out.println();
  }

  public void insert(int num){
    Node node = new Node(num);
    if (head == null && tail == null) head = tail = node;
    else {
      tail.next = node;
      node.prev = tail;
      tail = node;
    }
  }

  public static void main(String[] args) {
    MergeSortDLL dll = new MergeSortDLL(new int[]{ 12, 11, 13, 5, 6, 7 });
    dll.print();
    dll.head = mergeSort(dll.head);
    dll.print();
  }
}
