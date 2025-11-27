package leetcode;

public class Q707 {
  static class MyLinkedList {

    static class Node {
      int val;
      Node prev;
      Node next;

      Node(int val){
        this.prev = this.next = null;
        this.val = val;
      }

      void prepend(Node node){
        node.next = this;
        if (this.prev != null){
          this.prev.next = node;
          node.prev = this.prev;
        }
        this.prev = node;
      }

      void append(Node node){
        node.prev = this;
        if (this.next != null){
          this.next.prev = node;
          node.next = this.next;
        }
        this.next = node;
      }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
      head = null;
      tail = null;
      size = 0;
    }

    private Node getNodeAt(int index) {
      if (index < 0 || index >= size) return null;
      Node current;
      int i;
      if (index < size / 2){
        current = head;
        for (i = 0; i < index; i++) current = current.next;
      } else {
        current = tail;
        for (i = size - 1; i > index; i--) current = current.prev;
      }
      return current;
    }

    public int get(int index) {
      Node node = getNodeAt(index);
      if (node == null) return -1;
      return node.val;
    }

    public void addAtHead(int val) {
      if (size == 0) head = tail = new Node(val);
      else {
        Node newHead = new Node(val);
        head.prepend(newHead);
        head = newHead;
      }
      size++;
    }

    public void addAtTail(int val) {
      if (size == 0) head = tail = new Node(val);
      else {
        Node newTail = new Node(val);
        tail.append(newTail);
        tail = newTail;
      }
      size++;
    }

    public void addAtIndex(int index, int val) {
      if (index > size) return;
      if (index == 0) {
        addAtHead(val);
        return;
      }
      else if (index == size) {
        addAtTail(val);
        return;
      }
      Node node = getNodeAt(index);
      if (node == null) return;
      node.prepend(new Node(val));
      size++;
    }

    public void deleteAtIndex(int index) {
      if (index < 0 || index >= size) return;
      else if (index == 0) {
        if (head == tail) head = tail = null;
        else {
          head.next.prev = null;
          head = head.next;
        }
      }
      else if (index == size - 1){
        if (head == tail) head = tail = null;
        else {
          tail.prev.next = null;
          tail = tail.prev;
        }
      }
      else {
        Node node = getNodeAt(index);
        if (node == null) return;
        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
      }
      size--;
    }
  }
}
