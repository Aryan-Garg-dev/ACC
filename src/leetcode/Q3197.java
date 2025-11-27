package leetcode;

import utility.Console;

public class Q3197 {
  /*
  * Minimize the area covering all the one's.
  * Cut the grid into 3 rectangles.
  * */

  static class Solution {

    static class Cell {
      int r, c;
      public Cell(int r, int c) {
        this.r = r;
        this.c = c;
      }
      public Cell copy() {
        return new Cell(r, c);
      }
    }

    static class Rect {
      Cell first = new Cell(0, 0), last = new Cell(0, 0);
      public Rect(Cell first, Cell last) {
        this.first = first;
        this.last = last;
      }
      public int area() {
        return (last.r - first.r + 1) * (last.c - first.c + 1);
      }
    }

    private static Rect smallestRect(int[][] grid, Cell first, Cell last) {
      int rows = grid.length, cols = grid[0].length;
      int minRow = rows, maxRow = -1;
      int minCol = cols, maxCol = -1;

      for (int r = first.r; r <= last.r && r < rows; r++) {
        for (int c = first.c; c <= last.c && c < cols; c++) {
          if (grid[r][c] == 1) {
            minRow = Math.min(minRow, r);
            maxRow = Math.max(maxRow, r);
            minCol = Math.min(minCol, c);
            maxCol = Math.max(maxCol, c);
          }
        }
      }

      return new Rect(new Cell(minRow, minCol), new Cell(maxRow, maxCol));
    }

    private static int getArea(Rect rect){
      return rect.area();
    }

    public int minimumSum(int[][] grid) {
      int rows = grid.length, cols = grid[0].length;
      Rect s_rect = smallestRect(
        grid,
        new Cell(0,0),
        new Cell(rows - 1, cols - 1)
      );

      int r, c, dr, dc;
      final Cell first = s_rect.first, last = s_rect.last;
      int minimum_sum = Integer.MAX_VALUE, sum = 0;

      h_3: // first_r -> r, r -> dr, dr -> last_r
      if (rows > 2){
        for (r = first.r; r < last.r - 1; r++){
          for (dr = r + 1; dr < last.r; dr++){
            sum =
                + getArea(smallestRect(grid, first, new Cell(r, last.c)))
                + getArea(smallestRect(grid, new Cell(r + 1, first.c), new Cell(dr, last.c)))
                + getArea(smallestRect(grid, new Cell(dr + 1, first.c), last));

            minimum_sum = Math.min(sum, minimum_sum);
          }
        }
      }

      v_3: // first_c -> c, c -> dc, dc -> last_c
      if (cols > 2){
        for (c = first.c; c < last.c - 1; c++){
          for (dc = c + 1; dc < last.c; dc++){
            sum =
                + getArea(smallestRect(grid, first, new Cell(last.r, c)))
                + getArea(smallestRect(grid, new Cell(first.r, c + 1), new Cell(last.r, dc)))
                + getArea(smallestRect(grid, new Cell(first.r, dc + 1), last));
            minimum_sum = Math.min(sum, minimum_sum);
          }
        }
      }

      other:
      for (r = first.r; r < last.r; r++){
        for (c = first.c; c < last.c; c++){
          for (int count = 1; count <= 4; count++){
            sum = switch (count) {
              case 1 -> // v_2_h
                  + getArea(smallestRect(grid, first, new Cell(r, c)))
                  + getArea(smallestRect(grid, new Cell(first.r, c + 1), new Cell(r, last.c)))
                  + getArea(smallestRect(grid, new Cell(r + 1, first.c), last));
              case 2 -> // v_h_2
                    + getArea(smallestRect(grid, first, new Cell(last.r, c)))
                  + getArea(smallestRect(grid, new Cell(first.r, c + 1), new Cell(r, last.c)))
                  + getArea(smallestRect(grid, new Cell(r + 1, c + 1), last));
              case 3 -> // h_v_2
                  + getArea(smallestRect(grid, first, new Cell(r, last.c)))
                  + getArea(smallestRect(grid, new Cell(r + 1, first.c), new Cell(last.r, c)))
                  + getArea(smallestRect(grid, new Cell(r + 1, c + 1), last));
              case 4 -> // h_2_v
                  + getArea(smallestRect(grid, first, new Cell(r, c)))
                  + getArea(smallestRect(grid, new Cell(r + 1, first.c), new Cell(last.r, c)))
                  + getArea(smallestRect(grid, new Cell(first.r, c + 1), last));
              default -> sum;
            };
            minimum_sum = Math.min(minimum_sum, sum);
          }
        }
      }

      return minimum_sum;
    }

  }

  public static void main(String...args){
    var sol = new Solution();
    int[][][] grids = {
      new int[][]{{ 0, 0, 0 }, { 0, 0, 0 }, { 1, 1, 1 }, { 0, 0, 0 }},
      new int[][]{{ 1, 0, 1 }, { 1, 1, 1 }},
      new int[][]{{ 1, 0, 1, 0}, { 0, 1, 0, 1 }},
    };
    Console.log().println(sol.minimumSum(grids[0]));
  }
}
