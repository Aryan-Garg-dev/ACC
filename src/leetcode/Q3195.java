package leetcode;

public class Q3195 {

  interface ISolution {
    int minimumArea(int[][] grid);
  }

  static class Solution implements ISolution {

    static class Naive implements ISolution {
      @Override
      public int minimumArea(int[][] grid){
        final int rows = grid.length, cols = grid[0].length;
        int first_r = rows - 1, last_r = 0, first_c = cols - 1, last_c = 0;
        for (int r = 0; r < rows; r++){
          for (int c = 0; c < cols; c++){
            if (grid[r][c] == 1){
              first_r = Math.min(first_r, r);
              last_r = Math.max(last_r, r);
              first_c = Math.min(first_c, c);
              last_c = Math.max(last_c, c);
            }
          }
        }
        return (last_r - first_r + 1) * (last_c - first_c + 1);
      }
    }

    static class Optimised implements ISolution {

        private boolean _colFirstLast(int[] row, int[] first_c, int[] last_c) {
          int cols = row.length;
          int lc = 0, rc = cols - 1;
          boolean found = false;

          while (lc < cols && row[lc] == 0) lc++;
          while (rc >= lc && row[rc] == 0) rc--;

          if (lc < cols && rc >= 0 && lc <= rc) {
            first_c[0] = Math.min(first_c[0], lc);
            last_c[0] = Math.max(last_c[0], rc);
            found = true;
          }
          return found;
        }

        public int minimumArea(int[][] grid) {
          int rows = grid.length, cols = grid[0].length;
          int first_r = 0, last_r = rows - 1;
          int[] first_c = {cols - 1};
          int[] last_c = {0};

          while (first_r < rows && !_colFirstLast(grid[first_r], first_c, last_c)) first_r++;

          while (last_r >= first_r && !_colFirstLast(grid[last_r], first_c, last_c)) last_r--;

          if (first_c[0] == 0 && last_c[0] == cols - 1) {
            return (last_r - first_r + 1) * cols;
          }

          int fc = 0, lc = cols - 1;
          boolean hasOne = false;

          while (fc < first_c[0]) {
            for (int r = first_r + 1; r < last_r; r++) {
              if (grid[r][fc] == 1) {
                hasOne = true;
                break;
              }
            }
            if (hasOne) break;
            fc++;
          }

          hasOne = false;

          while (lc > last_c[0]) {
            for (int r = first_r + 1; r < last_r; r++) {
              if (grid[r][lc] == 1) {
                hasOne = true;
                break;
              }
            }
            if (hasOne) break;
            lc--;
          }

          first_c[0] = Math.min(first_c[0], fc);
          last_c[0] = Math.max(last_c[0], lc);

          return (last_r - first_r + 1) * (last_c[0] - first_c[0] + 1);
        }

    }

    @Override
    public int minimumArea(int[][] grid){
      return new Naive().minimumArea(grid);
    }
  }
}
