package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Q2034 {
  static class StockPrice {
    private final Map<Integer, Integer> stockRecord; // [timestamp, price]
    private final PriorityQueue<int[]> maxPrice;      // [price, timestamp]
    private final PriorityQueue<int[]> minPrice;      // [price, timestamp]
    private int latestTimestamp;

    public StockPrice() {
      stockRecord = new HashMap<>();
      minPrice = new PriorityQueue<>((a, b) -> a[0] - b[0]);
      maxPrice = new PriorityQueue<>((a, b) -> b[0] - a[0]);

    }

    public void update(int timestamp, int price) {
      stockRecord.put(timestamp, price);
      latestTimestamp = Math.max(latestTimestamp, timestamp);
      maxPrice.offer(new int[]{price, timestamp});
      minPrice.offer(new int[]{price, timestamp});
    }

    public int current() {
      return stockRecord.get(latestTimestamp);
    }

    public int maximum() {
      // Lazy Removal of outdated records
      while (!maxPrice.isEmpty()){
        int[] top = maxPrice.peek();
        if (stockRecord.get(top[1]) == top[0]) return top[0];
        maxPrice.poll();
      }
      return -1;
    }

    public int minimum() {
      while (!minPrice.isEmpty()){
        int[] top = minPrice.peek();
        if (stockRecord.get(top[1]) == top[0]) return top[0];
        minPrice.poll();
      }
      return -1;
    }
  }
}
