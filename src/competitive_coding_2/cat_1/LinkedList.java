package competitive_coding_2.cat_1;

public class LinkedList <T> {
  private static class ListNode <T> {
    T data;
    ListNode<T> next;

    ListNode(T data){
      this.data = data;
    }
  }

  ListNode<T> head;
  int size;

  public LinkedList() {
    head = null;
    size = 0;
  }

  public LinkedList(T[] elements){
    for (T element: elements) add(element);
  }

  public boolean isEmpty(){
    return size == 0;
  }

  public void add(T data){
    if (isEmpty()){
      this.head = new ListNode<>(data);
      size++;
      return;
    }
    this.head.next = new ListNode<>(data);
    size++;
  }

  public static <P> boolean detectLoop(LinkedList<P> list){
    ListNode<P> fast = list.head;
    ListNode<P> slow = list.head;
    while (fast != null && fast.next != null){
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) return true;
    }
    return false;
  }
}


