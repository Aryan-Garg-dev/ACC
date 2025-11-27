package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Q3709 {
  static class ExamTracker {
    private List<Integer> scores;
    private List<Integer> times;
    private List<Long> prefix;

    public ExamTracker() {
      scores = new ArrayList<>();
      times = new ArrayList<>();
      prefix = new ArrayList<>();
    }

    public void record(int time, int score) {
      if (!prefix.isEmpty()) prefix.add(prefix.getLast() + score);
      else prefix.add((long) score);
      scores.add(score);
      times.add(time);
    }

    public long totalScore(int startTime, int endTime) {
      int start = Collections.binarySearch(times, startTime);
      if (start < 0) start = -start - 1;
      int end = Collections.binarySearch(times, endTime + 1);
      if (end < 0) end = -end - 1;
      end--;

      if (start > end || end < 0) return 0;

      if (start == 0) return prefix.get(end);
      return prefix.get(end) - prefix.get(start - 1);
    }
  }
}
