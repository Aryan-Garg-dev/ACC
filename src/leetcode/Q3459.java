package leetcode;

public class Q3459 {

  static class Solution {
    private int _rows = 0, _cols = 0;
    final private int[][] _directions = {
      // { dr, dc }
      {  1,  1 }, // bottom-right
      {  1, -1 }, // bottom-left
      { -1, -1 }, // top-left
      { -1,  1 }, // bottom-right
    };


    public int lenOfVDiagonal(int[][] grid) {
      _rows = grid.length;
      _cols = grid[0].length;
      int[][][] memo = new int[_rows][_cols][8];
      int result = 0;
      for (int r = 0; r < _rows; r++){
        for (int c = 0; c < _cols; c++){
          if (grid[r][c] == 1){
            int[] max = { _rows - r, c + 1, r + 1, _cols - c };
            for (int d = 0; d < 4; d++){
              if (max[d] > result){
                result = Math.max(result, 1 + dfs(grid, r, c, 2, 1, d, memo));
              }
            }
          }
        }
      }
      return result;
    }

    public int dfs(int[][] grid, int r, int c, int target, int canTurn, int dirIdx, int[][][] memo){
      r += _directions[dirIdx][0];
      c += _directions[dirIdx][1];
      if (r < 0 || r >= _rows || c < 0 || c >= _cols || grid[r][c] != target) return 0;
      int dir_turn = dirIdx * 2 + canTurn;
      if (memo[r][c][dir_turn] > 0){
        return memo[r][c][dir_turn];
      }
      int best = dfs(grid, r, c, 2 - target, canTurn, dirIdx, memo);
      if (canTurn == 1){
        int[] max = { _rows - r, c + 1, r + 1, _cols - c };
        dirIdx = (dirIdx + 1) % 4;
        if (max[dirIdx] > best){
          best = Math.max(best, dfs(grid, r, c, 2 - target, 0, dirIdx, memo));
        }
      }
      return memo[r][c][dir_turn] = best + 1;
    }

  }

  public static void main(String[] args) {
    int[][][] inputs = {
      new int[][]{
        { 2, 2, 1, 2, 2 },
        { 2, 0, 2, 2, 0 },
        { 2, 0, 1, 1, 0 },
        { 1, 0, 2, 2, 2 },
        { 2, 0, 0, 2, 2 },
      },

      new int[][]{
        { 2, 2, 2, 2, 2 },
        { 2, 0, 2, 2, 0 },
        { 2, 0, 1, 1, 0 },
        { 1, 0, 2, 2, 2 },
        { 2, 0, 0, 2, 2 }
      },

      new int[][]{
        { 1, 2, 2, 2, 2 },
        { 2, 2, 2, 2, 0 },
        { 2, 0, 0, 0, 0 },
        { 0, 0, 2, 2, 2 },
        { 2, 0, 0, 2, 0 }
      }
    };

    final Solution sol = new Solution();

    for (var input: inputs){
      System.out.println(sol.lenOfVDiagonal(input));
    }
  }
}
