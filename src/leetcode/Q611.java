package leetcode;

import utility.Console;

import java.util.Arrays;

public class Q611 {
  static class Solution {
    public int triangleNumber(int[] nums) {
      Arrays.sort(nums);
      int count = 0;

      for (int c = nums.length - 1; c > 1; c--){
        int a = 0, b = c - 1;
        while (a < b){
          if (nums[a] + nums[b] > nums[c]){
            count += (b - a);
            b--;
          } else a++;
        }
      }

      return count;
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    int[] nums = {2, 2, 3, 4};
    Console.log().println(sol.triangleNumber(nums));

    nums = new int[]{4, 2, 3, 4};
    Console.log().println(sol.triangleNumber(nums));
  }
}
