package leetcode;

import utility.Console;

import java.util.HashSet;
import java.util.Set;

public class Q1930 {
  static class Solution {
    private int idx(char c) {
      return c - 'a';
    }

    public int countPalindromicSubsequence(String s) {
      int count = 0;
      int[] temp = new int[26];
      boolean[] done = new boolean[26];
      for (int j = 0; j < s.length(); j++){
        char c = s.charAt(j);
        int id = idx(c);
        if (done[id]) continue;
        done[id] = true;
        int first = s.indexOf(c), last = s.lastIndexOf(c);
        id += 1;
        if (last - first >= 2) {
          for (int i = first + 1; i < last; i++) temp[idx(s.charAt(i))] = id;
          for (int i = 0; i < 26; i++){
            if (temp[i] == id) count += 1;
          }
        }
      }
      return count;
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();

    Console.log()
      .println(sol.countPalindromicSubsequence("aabca"))
      .println(sol.countPalindromicSubsequence("adc"))
      .println(sol.countPalindromicSubsequence("bbcbaba"));
  }
}
