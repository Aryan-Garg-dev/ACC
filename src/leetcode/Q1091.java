package leetcode;

import utility.Console;

import java.util.LinkedList;
import java.util.Queue;

public class Q1091 {
  static class Solution {
    private final static int[][] _directions = {
      { 0, 1 }, // right
      { 1, 1 }, // bottom-right
      { 1, 0 }, // bottom
      { 1, -1 }, // bottom-left
      { 0, -1 }, // left
      { -1, -1 }, // top-left
      { -1, 0 }, // top
      { -1, 1 }, // top-right
    };

    private int n = 0;

    public int shortestPathBinaryMatrix(int[][] grid) {
      n = grid.length;
      if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
      int path = 1;

      boolean[] visited = new boolean[n*n];
      visited[_v(0, 0)] = true;

      Queue<Integer>q = new LinkedList<>();
      q.add(_v(0, 0));

      int r, c, curr, nr, nc, i;
      while (!q.isEmpty()){
        int size = q.size();
        for (int s = 0; s < size; s++){
          curr = q.poll(); r = curr / n; c = curr % n;
          if (r == n-1 && c == n-1) return path;
          for (var dir: _directions){
            nr = r + dir[0]; nc = c + dir[1]; i = _v(nr, nc);
            if (nr < 0 || nr >= n || nc < 0 || nc >= n || grid[nr][nc] == 1 || visited[i]) continue;
            q.add(i);
            visited[i] = true;
          }
        }
        path++;
      }

      return -1;
    }

    private int _v(int r, int c){
      return r * n + c;
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();

    int[][] grid = {{ 0, 1 }, { 1, 0 }};
    Console.log().println(sol.shortestPathBinaryMatrix(grid));

    grid = new int[][]{
      { 0, 0, 0 },
      { 1, 1, 0 },
      { 1, 1, 0 },
    };
    Console.log().println(sol.shortestPathBinaryMatrix(grid));

    grid = new int[][]{
      { 1, 0, 0 },
      { 1, 1, 0 },
      { 1, 1, 0 },
    };
    Console.log().println(sol.shortestPathBinaryMatrix(grid));

  }
}
