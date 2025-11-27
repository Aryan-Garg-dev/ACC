package leetcode;

import utility.Console;

import java.util.Arrays;
import java.util.List;

public class Q120 {
  static class Solution {
    private Integer[][] memo;

    private int dfs(List<List<Integer>> triangle, int level, int index){
      if (level == triangle.size() - 1) {
        return triangle.get(level).get(index);
      }

      if (memo[level][index] != null){
        return memo[level][index];
      }

      int left = dfs(triangle, level + 1, index);
      int right = dfs(triangle, level + 1, index + 1);

      return memo[level][index] = triangle.get(level).get(index) + Math.min(left, right);
    }
    public int minimumTotal(List<List<Integer>> triangle) {
      int n = triangle.size();
      memo = new Integer[n][n];
      return dfs(triangle, 0, 0);
    }

  }


  public static void main(String[] args) {
    Solution sol = new Solution();
    List<List<Integer>> triangle = List.of(
      List.of(-10)

    );

    Console.log().println(sol.minimumTotal(triangle));
  }
}
