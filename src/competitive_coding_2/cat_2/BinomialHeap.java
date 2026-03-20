package competitive_coding_2.cat_2;

import java.util.*;

class BinomialHeap {

  PriorityQueue<Integer>[] heap;
  int MAX = 20;

  BinomialHeap() {
    heap = new PriorityQueue[MAX];
  }

  PriorityQueue<Integer> merge(PriorityQueue<Integer> a, PriorityQueue<Integer> b) {
    PriorityQueue<Integer> c = new PriorityQueue<>();
    if (a != null) c.addAll(a);
    if (b != null) c.addAll(b);
    return c;
  }

  // insert (same as before)
  void insert(int x) {
    PriorityQueue<Integer> carry = new PriorityQueue<>();
    carry.add(x);
    for (int i = 0; i < MAX; i++) {
      if (heap[i] == null) {
        heap[i] = carry;
        return;
      }
      carry = merge(heap[i], carry);
      heap[i] = null;
    }
  }

  // find min
  int getMin() {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < MAX; i++) {
      if (heap[i] != null)
        min = Math.min(min, heap[i].peek());
    }
    return min;
  }

  // extract min (simulate binomial)
  void extractMin() {
    int idx = -1;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < MAX; i++) {
      if (heap[i] != null && heap[i].peek() < min) {
        min = heap[i].peek();
        idx = i;
      }
    }
    if (idx == -1) return;
    PriorityQueue<Integer> pq = heap[idx];
    heap[idx] = null;
    pq.poll();
    while (!pq.isEmpty()) {
      insert(pq.poll());
    }
  }
}
