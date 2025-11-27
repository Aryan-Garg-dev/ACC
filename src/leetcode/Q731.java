package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Q731 {
  static class Solution1 {

    public static class MyCalendarTwo {
      private final TreeMap<Integer, Integer> timeline;

      public MyCalendarTwo() {
        timeline = new TreeMap<>();
      }

      private void rollback(int startTime, int endTime){
        timeline.put(startTime, timeline.get(startTime) - 1);
        timeline.put(endTime, timeline.get(endTime) + 1);
      }

      public boolean book(int startTime, int endTime) {
        timeline.put(startTime, timeline.getOrDefault(startTime, 0) + 1);
        timeline.put(endTime, timeline.getOrDefault(endTime, 0) - 1);

        int active = 0;
        for (int delta: timeline.values()){
          active += delta;
          if (active >= 3){
            rollback(startTime, endTime);
            return false;
          }
        }

        return true;
      }
    }

  }

  static class Solution2 {

    public static class MyCalendarTwo {
      private final List<int[]> bookings;
      private final List<int[]> overlaps;

      public MyCalendarTwo() {
        bookings = new ArrayList<>();
        overlaps = new ArrayList<>();
      }

      public boolean book(int startTime, int endTime) {
        for (int[] interval: overlaps){
          if (Math.max(startTime, interval[0]) < Math.min(endTime, interval[1]))
            return false;
        }

        for (int[] interval: bookings){
          int overlapStart = Math.max(startTime, interval[0]);
          int overlapEnd = Math.min(endTime, interval[1]);
          if (overlapStart < overlapEnd){
            overlaps.add(new int[]{ overlapStart, overlapEnd });
          }
        }

        bookings.add(new int[]{ startTime, endTime });
        return true;
      }
    }

  }
}
