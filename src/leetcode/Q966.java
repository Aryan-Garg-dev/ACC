package leetcode;

import utility.Console;

import java.util.*;


public class Q966 {
  static class Solution {
    private static String maskVowels(String word) {
      StringBuilder sb = new StringBuilder();
      for (char c : word.toLowerCase().toCharArray()) {
        if ("aeiou".indexOf(c) >= 0) {
          sb.append('*');
        } else {
          sb.append(c);
        }
      }
      return sb.toString();
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
      Set<String> exactWords = new HashSet<>(Arrays.asList(wordlist));
      Map<String, String> caseInsensitive = new HashMap<>();
      Map<String, String> vowelInsensitive = new HashMap<>();

      for (String word : wordlist) {
        caseInsensitive.putIfAbsent(word.toLowerCase(), word);
        vowelInsensitive.putIfAbsent(maskVowels(word), word);
      }

      String[] result = new String[queries.length];
      for (int i = 0; i < queries.length; i++) {
        String q = queries[i];
        if (exactWords.contains(q)) {
          result[i] = q;
        } else {
            result[i] = caseInsensitive.getOrDefault(
            q.toLowerCase(),
            vowelInsensitive.getOrDefault(
              maskVowels(q),
              ""
            )
          );
        }
      }
      return result;
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    String[] wordList = {"KiTe","kite","hare","Hare"};
    String[] queries = {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};
    Console.log().println(sol.spellchecker(wordList, queries));

    wordList = new String[]{"yellow"};
    queries = new String[]{"YellOw"};
    Console.log().println(sol.spellchecker(wordList, queries));



  }
}
