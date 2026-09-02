package competitive_coding_2.fat.practice;

import utility.Logger;
import utility.Input;

public class MergeSortDLL {
  static class ListNode {
    int value;
    ListNode prev, next;

    public ListNode(int value){
      this.value = value;
      this.prev = this.next = null;
    }

    public static ListNode reverse(ListNode head){
      ListNode curr = head;
      ListNode prev = null;
      while (curr != null){
        ListNode next = curr.next;
        curr.next = prev;
        curr.prev = next;
        prev = curr;
        curr = next;
      }
      return prev;
    }

    public static ListNode merge(ListNode first, ListNode second){
      if (first == null) return second;
      if (second == null) return first;
      if (first.value <= second.value){
        first.next = merge(first.next, second);
        if (first.next != null) first.next.prev = first;
        return first;
      } else {
        second.next = merge(first, second.next);
        if (second.next != null) second.next.prev = second;
        return second;
      }
    }

    public static ListNode getMiddle(ListNode head){
      if (head == null) return null;
      ListNode slow = head, fast = head.next;
      while (fast != null && fast.next != null){
        slow = slow.next;
        fast = fast.next.next;
      }
      return slow;
    }

    public static ListNode mergeSort(ListNode head){
      if (head == null || head.next == null) return head;
      ListNode middle = getMiddle(head);
      ListNode right = middle.next;
      middle.next = null;
      right.prev = null;
      return merge(
        mergeSort(head),
        mergeSort(right)
      );
    }
  }

  static class LinkedList {
    ListNode head;
    ListNode tail;
    int size;

    public LinkedList(){
      head = tail = null;
      size = 0;
    }

    public LinkedList(int[] nums){
      this();
      for (int num: nums) add(num);
    }

    public void add(int num){
      ListNode node = new ListNode(num);
      if (head == null && tail == null) head = tail = node;
      else {
        tail.next = node;
        node.prev = tail;
        tail = node;
      }
      size++;
    }

    public int[] toArray(){
      int[] arr = new int[size];
      ListNode iter = head;
      int i = 0;
      while (iter != null){
        arr[i++] = iter.value;
        iter = iter.next;
      }
      return arr;
    }
  }

  public final static Input input = new Input();
  public static void main(String[] args) {
    int size = input.prompt("Enter size: ").readInt();
    LinkedList list = new LinkedList(
      input.prompt("Input array: ").readIntArray(size)
    );
    Logger.log().println(list.toArray());
    list.head = ListNode.mergeSort(list.head);
    Logger.debug().println(list.toArray());
  }
}
