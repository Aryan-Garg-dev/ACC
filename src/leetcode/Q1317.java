package leetcode;

import utility.Console;

public class Q1317 {
  static class Solution {
    public int[] getNoZeroIntegers(int n) {
      int p = (int) Math.log10(n);
      int i = (int) Math.pow(10, p);
      if (i == 1) return new int[]{ i, n - i };
      return new int[]{ i - 1, (n - i) + 1 };
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();

    Console.log().print(sol.getNoZeroIntegers(11));
  }
}
