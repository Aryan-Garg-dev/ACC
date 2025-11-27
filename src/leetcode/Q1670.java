package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Q1670 {

  static class FrontMiddleBackQueue {
    private final List<Integer> queue;

    public FrontMiddleBackQueue() {
      queue = new ArrayList<>();
    }

    public void pushFront(int val) {
      queue.addFirst(val);
    }

    public void pushMiddle(int val) {
      int middle = queue.size() / 2;
      queue.add(middle, val);
    }

    public void pushBack(int val) {
      queue.addLast(val);
    }

    public int popFront() {
      if (queue.isEmpty()) return -1;
      return queue.removeFirst();
    }

    public int popMiddle() {
      if (queue.isEmpty()) return -1;
      int middle = (queue.size() - 1) / 2;
      return queue.remove(middle);
    }

    public int popBack() {
      if (queue.isEmpty()) return -1;
      return queue.removeLast();
    }
  }

}
