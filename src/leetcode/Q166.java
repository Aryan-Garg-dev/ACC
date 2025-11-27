package leetcode;

import utility.Console;

import java.util.HashMap;
import java.util.Map;

public class Q166 {
  static class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
      if (numerator == 0) return "0";

      StringBuilder result = new StringBuilder();
      if ((numerator * denominator) < 0) result.append('-');
      result.append(numerator / denominator);

      int remainder = numerator % denominator;
      if (remainder == 0) return result.toString();

      result.append('.');

      Map<Integer, Integer> remMap = new HashMap<>();
      while (remainder != 0){
        if (remMap.containsKey(remainder)){
          int start = remMap.get(remainder);
          result.insert(start, '(');
          result.append(')');
          break;
        }

        remMap.put(remainder, result.length());

        remainder *= 10;
        result.append(remainder / denominator);
        remainder %= denominator;
      }

      return result.toString();
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    Console.log().println(sol.fractionToDecimal(1, 2));
    Console.log().println(sol.fractionToDecimal(2, 1));
    Console.log().println(sol.fractionToDecimal(4, 333));
  }
}
