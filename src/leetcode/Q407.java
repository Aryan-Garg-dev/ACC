package leetcode;

import utility.Console;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Q407 {
  static class Solution {
    static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int trapRainWater(int[][] heightMap) {
      int r = heightMap.length, c = heightMap[0].length;
      if (r <= 2 || c <= 2) return 0;

      boolean[][] visited = new boolean[r][c];
      PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

      for (int i = 0; i < r; i++){
        pq.offer(new int[]{ i, 0, heightMap[i][0] });
        pq.offer(new int[]{ i, c - 1, heightMap[i][c-1] });
        visited[i][0] = visited[i][c-1] = true;
      }

      for (int j = 0; j < c; j++){
        pq.offer(new int[]{ 0, j, heightMap[0][j] });
        pq.offer(new int[]{ r - 1, j, heightMap[r-1][j] });
        visited[0][j] = visited[r-1][j] = true;
      }

      int water = 0;
      while (!pq.isEmpty()){
        int[] cell = pq.poll();
        int x = cell[0], y = cell[1], height = cell[2];

        for (int[] d: dirs){
          int nx = x + d[0], ny = y + d[1];
          if (nx < 0 || ny < 0 || nx >= r || ny >= c || visited[nx][ny]) continue;
          visited[nx][ny] = true;

          water += Math.max(0, height - heightMap[nx][ny]);

          pq.offer(new int[]{ nx, ny, Math.max(height, heightMap[nx][ny]) });
        }
      }

      return water;
    }

  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    int[][] heightMap = {
      {1,4,3,1,3,2},
      {3,2,1,3,2,4},
      {2,3,3,2,3,1}
    };
    Console.log().println(sol.trapRainWater(heightMap));

    heightMap = new int[][]{
      {3,3,3,3,3},
      {3,2,2,2,3},
      {3,2,1,2,3},
      {3,2,2,2,3},
      {3,3,3,3,3},
    };
    Console.log().println(sol.trapRainWater(heightMap));

  }
}
