package leetcode;

import utility.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Q17 {
  static class Solution {
    private final static Map<Character, String> map = Map.of(
      '2', "abc",
      '3', "def",
      '4', "ghi",
      '5', "jkl",
      '6', "mno",
      '7', "pqrs",
      '8', "tuv",
      '9', "wxyz"
    );

    private static void dfs(String digits, int index, String current, List<String> result){
      if (index == digits.length()) {
        result.add(current);
        return;
      }
      for (char c: map.get(digits.charAt(index)).toCharArray()){
        dfs(digits, index + 1, current + c, result);
      }
    }


    public List<String> letterCombinations(String digits) {
      List<String> result = new ArrayList<>();
      String current = "";
      dfs(digits, 0, current, result);
      return result;
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    Console.log().println(sol.letterCombinations("23"));
  }
}
