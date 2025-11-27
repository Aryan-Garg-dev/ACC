package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Q1488 {

  static class Solution {
    public int[] avoidFlood(int[] rains){
      int n = rains.length;
      int[] res = new int[n];

      Map<Integer, Integer> fullLakes = new HashMap<>();
      TreeSet<Integer> dryDays = new TreeSet<>();

      for (int i = 0; i < n; i++){
        int lake = rains[i];

        if (lake > 0){
          if (fullLakes.containsKey(lake)){
            Integer dryDay = dryDays.higher(fullLakes.get(lake));
            if (dryDay == null) return new int[0];
            res[dryDay] = lake;
            dryDays.remove(dryDay);
          }

          fullLakes.put(lake, i);
          res[i] = -1;
        } else {
          dryDays.add(i);
          res[i] = 1;
        }
      }

      return res;
    }
  }

  public static void main(String[] args) {

  }
}
