package competitive_coding_2.cat_1;

import utility.logger.Logger;

public class SortBitonicDLL {
  public static class ListNode {
    int value;
    ListNode prev;
    ListNode next;

    public ListNode(int val){
      value = val;
      prev = next = null;
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

  public static class LinkedList{
    ListNode head;
    ListNode tail;
    int size;

    public LinkedList(){
      head = tail = null;
      size = 0;
    }

    public LinkedList(int[] nums){
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
      int[] array = new int[size];
      ListNode iter = head;
      int i = 0;
      while (iter != null) {
        array[i++] = iter.value;
        iter = iter.next;
      }
      return array;
    }
  }

  public static void sortBitonicDLL(LinkedList list){
    if (list.head == null || list.head.next == null) return;

    if (list.size <= 2) return;

    // identify peak
    ListNode peak = list.head.next;
    while (peak != null){
      if (peak.value < peak.prev.value) break;
      else peak = peak.next;
    }

    if (peak == null) return;
    peak.prev.next = null;
    peak.prev = null;

    ListNode second = ListNode.reverse(peak);
    list.head = ListNode.merge(list.head, second);
  }


  public static void main(String[] args) {
    LinkedList list = new LinkedList(new int[]{ 5, 7, 9, 10, 4, 2, 1 });
    sortBitonicDLL(list);
    Logger.log().println(list.toArray());
  }
}

