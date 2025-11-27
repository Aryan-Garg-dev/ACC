package leetcode;

import utility.Console;

import java.util.ArrayList;
import java.util.List;

public class Q448 {
  static class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
      int n = nums.length;
      List<Integer> result = new ArrayList<>();
      int i = 0;
      while (i < n){
        if (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]){
          int temp = nums[nums[i] - 1];
          nums[nums[i] - 1] = nums[i];
          nums[i] = temp;
        } else {
          i++;
        }
      }

      for (i = 0; i < n; i++) {
        if (nums[i] != i + 1) result.add(i + 1);
      }

      return result;
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    Console.log()
      .println(sol.findDisappearedNumbers(new int[]{ 4, 3, 2, 7, 8, 2, 3, 1 }));
  }
}
