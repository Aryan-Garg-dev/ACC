package leetcode;

import utility.Console;

import java.util.*;
import java.util.stream.Collectors;

public class Q1733 {
  static class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
      Map<Integer, Set<Integer>> userLang = new HashMap<>();
      for (int i = 0; i < languages.length; i++){
        userLang.put(i + 1, Arrays.stream(languages[i]).boxed().collect(Collectors.toSet()));
      }

      Set<Integer> possibleCandidates = new HashSet<>();

      for (var friendship: friendships){
        int u1 = friendship[0], u2 = friendship[1];
        var lang1 = userLang.get(u1); var lang2 = userLang.get(u2);
        if (lang1.stream().anyMatch(lang2::contains)) continue;
        else {
          possibleCandidates.add(u1);
          possibleCandidates.add(u2);
        }
      }

      int minCandidates = Integer.MAX_VALUE;

      for (int i = 1; i <= n; i++){
        int candidatesThatDoNotKnowThisLang = 0;
        for (int candidate: possibleCandidates){
          if (!userLang.get(candidate).contains(i)) candidatesThatDoNotKnowThisLang++;
        }
        minCandidates = Math.min(minCandidates, candidatesThatDoNotKnowThisLang);
      }

      return minCandidates;
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();

    int n = 2;
    int[][] languages = {{1}, {2}, {1, 2}};
    int[][] friendships = {{1, 2}, {1, 3}, {2, 3}};

    Console.log().println(sol.minimumTeachings(n, languages, friendships));

    n = 3;
    languages = new int[][]{{2}, {1, 3}, {1, 2}, {3}};
    friendships = new int[][]{{1, 4}, {1, 2}, {3, 4}, {2, 3}};

    Console.log().println(sol.minimumTeachings(n, languages, friendships));
  }
}

/*
 Build a map: user -> set of languages they know

2. For each friendship:
    If the two users have no common language:
        Add both users to a set of "candidates" (people who might need teaching)

3. For each possible language (1 to n):
    Count how many users in "candidates" do NOT know this language
    Keep track of the minimum count found

4. Return the minimum count
* */
