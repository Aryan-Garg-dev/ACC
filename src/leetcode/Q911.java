package leetcode;

import utility.Console;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Q911 {
  static class TopVotedCandidate {
    private final int[] topVoted;
    private final int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
      int n = persons.length;
      this.times = times;
      this.topVoted = new int[n];
      int[] votes = new int[n];
      int maxVotes = 0;
      int leader = -1;
      for (int i = 0; i < times.length; i++){
        int time = times[i];
        int person = persons[i];
        votes[person]++;
        if (votes[person] >= maxVotes){
          maxVotes = votes[person];
          leader = person;
        }
        topVoted[i] = leader;
      }
    }

    public int q(int t) {
      t = Arrays.binarySearch(this.times, t + 1);
      // lower bound evaluation (last element ≤ original t)
      t = t < 0 ? - t - 2 : t - 1;
      return topVoted[t];
    }
  }

  public static void main(String[] args) {
    TopVotedCandidate t = new TopVotedCandidate(
      new int[]{0, 1, 1, 0, 0, 1, 0},
      new int[]{0, 5, 10, 15, 20, 25, 30}
    );

    Console.log().println(t.q(3));
    Console.log().println(t.q(12));
    Console.log().println(t.q(25));
    Console.log().println(t.q(15));
    Console.log().println(t.q(24));
    Console.log().println(t.q(8));
  }
}
