package competitive_coding_2.fat.practice;
import utility.Console;
import utility.Input;

import java.util.*;

public class SortBitonicDLL {
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

    public void sortBitonic(){
      if (head == null || head.next == null) return;
      if (size <= 2) return;
      ListNode peak = head.next;
      while (peak != null){
        if (peak.value < peak.prev.value) break;
        peak = peak.next;
      }
      if (peak == null) return;
      peak.prev.next = null;
      peak.prev = null;

      ListNode second = ListNode.reverse(peak);
      head = ListNode.merge(head, second);
    }
  }

  public static void main(String[] args) {
    Input input = new Input();
    int size = input.prompt("Enter size: ").readInt();
    int[] arr = input.prompt("Enter a bitonic array: ").readIntArray(size);
    LinkedList list = new LinkedList(arr);
    Console.log().println(list.toArray());
    list.sortBitonic();
    Console.log().println(list.toArray());
  }



}
