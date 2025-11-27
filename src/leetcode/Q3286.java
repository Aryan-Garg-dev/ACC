package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q3286 {
  static class Solution {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
      Queue<int[]> queue = new LinkedList<>(); // x, y, health
      int rows = grid.size(), cols = grid.getFirst().size();
      int[][] maxHealth = new int[rows][cols];

      if (grid.getFirst().getFirst() == 1) health -= 1;
      queue.offer(new int[]{0, 0, health});
      maxHealth[0][0] = health;


      while (!queue.isEmpty()){
        int[] curr = queue.poll();
        int r = curr[0], c = curr[1], h = curr[2];
        if (r == rows - 1 && c == cols - 1) return true;
        for (int[] dir : DIRECTIONS) {
          int nr = r + dir[0], nc = c + dir[1];
          if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) continue;
          int newHealth = h - grid.get(nr).get(nc);
          if (newHealth <= 0) continue;
          if (newHealth > maxHealth[nr][nc]) {
            maxHealth[nr][nc] = newHealth;
            queue.offer(new int[]{nr, nc, newHealth});
          }
        }
      }
      return false;
    }
  }
}
