package competitive_coding;

public class Maneuver {
  public static int getTotalPaths(int rows, int cols){
    int[] dp = new int[cols];
    dp[0] = 1;
    for (int r = 0; r < rows; r++){
      for (int c = 1; c < cols; c++){
        dp[c] += dp[c - 1];
      }
    }
    return dp[cols - 1];
  }
}
