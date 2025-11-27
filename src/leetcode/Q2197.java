package leetcode;

import utility.Console;

import java.util.*;
import java.util.stream.Collectors;

public class Q2197 {
  static class Solution {

    private static int gcd(int a, int b){
      if (b == 0) return Math.abs(a);
      return gcd(b, a % b);
    }

    public List<Integer> replaceNonCoprimes(int[] nums) {
      Deque<Integer> stack = new ArrayDeque<>();
      for (int num: nums){
        int curr = num;
        while (!stack.isEmpty()){
          int top = stack.peekLast();
          int hcf = gcd(top, curr);
          if (hcf == 1) break;
          curr = (int)(((long) curr * top) / hcf);
          stack.pollLast();
        }
        stack.addLast(curr);
      }

      return new ArrayList<>(stack);
    }

  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    int[] nums = {6,4,3,2,7,6,2};
    Console.log().println(sol.replaceNonCoprimes(nums));

    nums = new int[]{2,2,1,1,3,3,3};
    Console.log().println(sol.replaceNonCoprimes(nums));

    nums = new int[]{31,97561,97561,97561,97561,97561,97561,97561,97561};
    Console.log().println(sol.replaceNonCoprimes(nums));


  }
}
