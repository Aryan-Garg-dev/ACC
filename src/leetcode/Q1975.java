package leetcode;

import utility.Console;

public class Q1975 {
  static class Solution {
    public long maxMatrixSum(int[][] matrix) {
      int n = matrix.length;
      int negCount = 0, sum = 0, min = Integer.MAX_VALUE;
      for (int[] nums : matrix) {
        for (int num: nums) {
          if (num < 0) negCount++;
          num = Math.abs(num);
          sum += num;
          min = Math.min(min, num);
        }
      }

      if (negCount % 2 == 1) sum -= 2 * min;

      return (long) sum;
    }
  }

  public static void main(String[] args) {
    int[][] matrix; Solution sol = new Solution();

    matrix = new int[][]{
      {1, -1}, {-1, 1}
    };

    Console.log().println(sol.maxMatrixSum(matrix));

    matrix = new int[][]{
      { 1, 2, 3 },{ -1, -2, -3 },{ 1, 2, 3 }
    };

    Console.log().println(sol.maxMatrixSum(matrix));
  }
}
