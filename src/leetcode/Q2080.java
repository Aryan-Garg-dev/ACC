package leetcode;

import java.util.*;

public class Q2080 {
  static class RangeFreqQuery {
    private final Map<Integer, List<Integer>> indexMap;

    public RangeFreqQuery(int[] arr) {
      indexMap = new HashMap<>();
      for (int i = 0; i < arr.length; i++){
        indexMap.computeIfAbsent(arr[i], f -> new ArrayList<>()).add(i);
      }
    }

    public int query(int left, int right, int value) {
      List<Integer> indexes = indexMap.get(value);
      if (indexes == null) return 0;
      int start = Collections.binarySearch(indexes, left);
      if (start < 0) start = -start - 1;
      int end = Collections.binarySearch(indexes, right + 1);
      if (end < 0) end = -end - 1;
      return end - start;
    }
  }
}
