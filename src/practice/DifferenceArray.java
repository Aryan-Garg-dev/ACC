package practice;

import utility.Console;

public class DifferenceArray {
  // n elements and q queries
  // l, r, a -> a[l...r] + [a....a]
  public static int[] resolveQueries(int[] nums, int[][] queries){
    int n = nums.length;
    int[] diff = new int[n];
    for (int[] query: queries){
      int l = query[0], r = query[1], a = query[2];
      diff[l] += a;
      if (r < n - 1) diff[r + 1] -= a;
    }
    for (int i = 1; i < n; i++) diff[i] += diff[i - 1];
    for (int i = 0; i < n; i++) nums[i] += diff[i];
    return nums;
  }

  public static void main(String[] args) {
    Console.log().println(
      resolveQueries(
        new int[]{0,0,0,0,0,0,0},
        new int[][]{
          {0, 2, 5},
          {1, 3, 6},
          {2, 6, 1}
        }
      )
    );
  }
}
