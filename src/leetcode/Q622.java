package leetcode;

public class Q622 {
  static class MyCircularQueue {
    private final int[] queue;
    private final int capacity;
    private int head;
    private int tail;
    private int size;

    public MyCircularQueue(int k) {
      queue = new int[k];
      capacity = k;
      size = 0;
      head = tail = -1;
    }

    public boolean enQueue(int value) {
      if (isFull()) return false;
      tail = (tail + 1) % capacity;
      queue[tail] = value;
      size++;
      return true;
    }

    public boolean deQueue() {
      if (isEmpty()) return false;
      head = (head + 1) % capacity;
      size--;
      return true;
    }

    public int Front() {
      return isEmpty() ? -1 : queue[head];
    }

    public int Rear() {
      return isEmpty() ? -1 : queue[tail];
    }

    public boolean isEmpty() {
      return size == 0;
    }

    public boolean isFull() {
      return size == capacity;
    }
  }
}
