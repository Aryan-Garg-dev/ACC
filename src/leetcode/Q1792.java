package leetcode;

import utility.Console;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q1792 {
  static class Solution {
    private double _gain(int pass, int total) {
      return (double) (pass + 1) / (total + 1) - (double) pass / total;
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
      Queue<double[]> pq = new PriorityQueue<>((a, b)->Double.compare(b[0], a[0]));

      for (var c: classes){
        pq.offer(new double[]{_gain(c[0], c[1]), c[0], c[1]});
      }

      while (extraStudents-- > 0){
        double[] top = pq.poll();
        if (top == null) continue;
        int pass = (int) top[1];
        int total = (int) top[2];
        pass++;
        total++;
        pq.offer(new double[]{ _gain(pass, total), pass, total });
      }

      double sum = 0;
      while (!pq.isEmpty()){
        double[] curr = pq.poll();
        sum += curr[1] / curr[2];
      }

      return sum / classes.length;
    }
  }

  public static void main(String[] args) {
      Solution sol = new Solution();

      int[][] classes = {{ 1, 2 }, { 3, 5 }, { 2, 2 }};
      int extraStudents = 2;
      Console.log().println(sol.maxAverageRatio(classes, extraStudents));

      classes = new int[][]{{2, 4}, {3, 9}, {4, 5}, {2, 10}};
      extraStudents = 4;
    Console.log().println(sol.maxAverageRatio(classes, extraStudents));
  }
}
