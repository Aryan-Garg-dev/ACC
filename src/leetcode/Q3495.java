package leetcode;

import utility.Console;

import java.util.LinkedList;
import java.util.Queue;

public class Q3495 {
  static class Solution {
    public long minOperations(int[][] queries) {
      long res = 0;
      int base = 4;
      int[] p4 = new int[16];
      p4[0] = 1;
      for (int i = 1; i < 16; i++){
        p4[i] = p4[i - 1] * 4;
      }
      for (int[] query : queries) {
        int l = query[0], r = query[1];
        long count = 0;
        for (int j = 1; j < 16; j++) {
          int left_bound = Math.max(l, p4[j - 1]);
          int right_bound = Math.min(r, p4[j] - 1);
          if (left_bound <= right_bound) {
            count += (long) j * (right_bound - left_bound + 1);
          }
        }
        res += (count + 1) / 2;
      }
      return res;
    }
  }



  public static void main(String[] args) {
    final Solution sol = new Solution();

    int[][] query = {{1, 2}, { 2, 4 }};
    Console.log().println(sol.minOperations(query));

    query = new int[][]{{2, 6}};
    Console.log().println(sol.minOperations(query));

    query = new int[][]{{1, 8}};
    Console.log().println(sol.minOperations(query));
  }
}
