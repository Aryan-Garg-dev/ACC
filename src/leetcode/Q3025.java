package leetcode;

import java.util.Arrays;

public class Q3025 {
  static class Solution {
    // eval pairs of 2 points and check if no other point exist between them
    private int[][] _points;

    public int numberOfPairs(int[][] points){
      this._points = points;
      int pairs = 0;
      for (int i = 0; i < points.length; i++){
        for (int j = i + 1; j < points.length; j++){
          pairs += isValidPair(points[i], points[j]) ? 1 : 0;
        }

      }
      return pairs;
    }

    public boolean isValidPair(int[] pointA, int[] pointB){
      int x1 = pointA[0], y1 = pointA[1];
      int x2 = pointB[0], y2 = pointB[1];
      if (x1 == x2 && y1 == y2) return false;
      if ((x1 <= x2 && y1 >= y2) || (x2 <= x1 && y2 >= y1)){
        int sx = Math.min(x1, x2), lx = Math.max(x1, x2);
        int sy = Math.min(y1, y2), ly = Math.max(y1, y2);
        for (var point: _points){
          int x = point[0], y = point[1];
          if ((x == x1 && y == y1) || (x == x2 && y == y2)) continue;
          if ((x <= lx && x >= sx) && (y >= sy && y <= ly)) return false;
        }
        return true;
      }
      return false;
    }

    static class OptimizedSolution {

      public int numberOfPairs(int[][] points){
        // top to bottom // left to right
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int pairs = 0;
        for (int i = 0; i < points.length; i++){
          int x1 = points[i][0], y1 = points[i][1];
          int top = y1, bot = Integer.MIN_VALUE;
          for (int j = i + 1; j < points.length; j++){
            int x2 = points[j][0], y2 = points[j][1];
            if (y2 <= top && y2 > bot){
              pairs++;
              bot = y2; // now points outside the range will not be selected otherwise the formed rect will include p2
              if (y2 == top) top--;
            }
          }
        }
        return pairs;
      }

    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();

  }
}
