package leetcode;

import utility.Console;

import java.util.TreeMap;

public class Q933 {
  static class RecentCounter {
    private final TreeMap<Integer, Integer> data;
    private int count = 0;

    public RecentCounter() {
      data = new TreeMap<>();
    }

    public int ping(int t) {
     count++;
     this.data.put(t, count);
     if (t < 3000) return count;
     if (this.data.lowerKey(t - 3000) == null) return count;
     return count - this.data.get(this.data.lowerKey(t-3000));
    }
  }

  public static void main(String[] args) {
    RecentCounter counter = new RecentCounter();
    Console.log().println(counter.ping(1));
    Console.log().println(counter.ping(100));
    Console.log().println(counter.ping(3001));
    Console.log().println(counter.ping(3002));
  }
}
