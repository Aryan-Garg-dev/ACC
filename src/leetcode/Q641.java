package leetcode;

public class Q641 {
  static class MyCircularDeque {
    private final int[] dequeue;
    private final int capacity;
    private int front, rear, size;

    public MyCircularDeque(int k) {
      dequeue = new int[k];
      capacity = k;
      size = 0;
      front = 0;
      rear = -1;
    }

    public boolean insertFront(int value) {
      if (isFull()) return false;
      if (isEmpty()) front = rear = 0;
      else front = (front - 1 + capacity) % capacity;
      dequeue[front] = value;
      size++;
      return true;
    }

    public boolean insertLast(int value) {
      if (isFull()) return false;
      if (isEmpty()) front = rear = 0;
      else rear = (rear + 1) % capacity;
      dequeue[rear] = value;
      size++;
      return true;
    }

    public boolean deleteFront() {
      if (isEmpty()) return false;
      front = (front + 1) % capacity;
      size--;
      return true;
    }

    public boolean deleteLast() {
      if (isEmpty()) return false;
      rear = (rear - 1 + capacity) % capacity;
      size--;
      return true;
    }

    public int getFront() {
      return isEmpty() ? -1 : dequeue[front];
    }

    public int getRear() {
      return isEmpty() ? -1 : dequeue[rear];
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean isFull() {
      return size == capacity;
    }
  }
}
