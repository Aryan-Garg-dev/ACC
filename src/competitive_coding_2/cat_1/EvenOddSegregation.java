package competitive_coding_2.cat_1;

public class EvenOddSegregation {
  public static class ListNode {
    int value;
    ListNode next;

    public ListNode(int num){
      value = num;
      next = null;
    }
  }

  public static class LinkedList {
    private ListNode head;

    public LinkedList(){
      head = null;
    }

    public LinkedList(int[] nums){
      for (int num: nums) add(num);
    }

    public void add(int num){
      if (head == null) head = new ListNode(num);
      else {
        ListNode last = head;
        while (last.next != null) last = last.next;
        last.next = new ListNode(num);
      }
    }

    public void join(LinkedList another){
      ListNode last = head;
      while (last.next != null) last = last.next;
      last.next = another.head;
    }
  }

  public static LinkedList segregateEvenOdd(LinkedList list){
    LinkedList even = new LinkedList();
    LinkedList odd = new LinkedList();

    ListNode node = list.head;
    while (node != null){
      if (node.value % 2 == 0) even.add(node.value);
      else odd.add(node.value);
      node = node.next;
    }

    even.join(odd);
    return even;
  }

  public static void printList(LinkedList list){
    ListNode node = list.head;
    while (node != null){
      System.out.printf("%d ", node.value);
      node = node.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    LinkedList list = new LinkedList(new int[]{ 1, 3, 5, 2, 4, 7, 9, 6 });
    LinkedList newList = segregateEvenOdd(list);
    printList(newList);
  }
}
