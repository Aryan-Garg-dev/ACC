package leetcode;

import utility.Console;

import java.util.LinkedList;
import java.util.Queue;

public class Q2327 {
  static class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
      int mod = 1_000_000_007;
      long[][] dp = new long[n][forget]; // dp[day][age]
      dp[0][0] = 1; // day 0, person 1 knows, age 0

      for (int i = 1; i < n; i++) {
        for (int j = 0; j < forget; j++) {
          if (j > 0) {
            dp[i][j] = dp[i - 1][j - 1];
          }
        }
        for (int j = delay - 1; j < forget - 1; j++) {
          if (j >= 0) {
            dp[i][0] = (dp[i][0] + dp[i - 1][j]) % mod;
          }
        }
      }

      long total = 0;
      for (int j = 0; j < forget; j++){
        total = (total + dp[n-1][j]) % mod;
      }

      return (int) total;
    }

  }

  static class OptimalSolution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
      int mod = 1_000_000_007;
      long[] dp = new long[n + 1];
      dp[1] = 1;

      long share = 0;
      for (int day = 2; day <= n; day++){
        if (day - delay >= 1) share = (share + dp[day - delay]) % mod;
        if (day - forget >= 1) share = (share - dp[day - forget] + mod) % mod;
        dp[day] = share;
      }

      long total = 0;
      for (int day = n - forget + 1; day <= n; day++){
        if (day >= 1) total = (total + dp[day]) % mod;
      }

      return (int) total;
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();

    int n = 6, delay = 2, forget = 4;
    Console.log().println(sol.peopleAwareOfSecret(n, delay, forget));

    n = 4; delay = 1; forget = 3;
    Console.log().println(sol.peopleAwareOfSecret(n, delay, forget));
  }
}
