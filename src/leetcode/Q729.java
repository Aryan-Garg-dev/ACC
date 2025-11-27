package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Q729 {
  static class MyCalendar {
    TreeMap<Integer, Integer> events;

    public MyCalendar() {
      this.events = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
      Integer prev = events.floorKey(startTime);
      if (prev != null && events.get(prev) > startTime) {
        return false;
      }

      Integer next = events.ceilingKey(startTime);
      if (next != null && next < endTime) {
        return false;
      }

      events.put(startTime, endTime);
      return true;
    }
  }
}
