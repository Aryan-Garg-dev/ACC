package leetcode;

import utility.Console;
import utility.Input;

import java.util.Arrays;

public class Q498 {
  static class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
      final int rows = mat.length, cols = mat[0].length;
      int[] result = new int[rows*cols];
      int idx = 0;
      int row = 0, col = 0, dir = 1; // 1 up-right -1 down-left
      while (idx < rows * cols){
        result[idx++] = mat[row][col];
        if (dir == 1){
          if (col == cols - 1){
            row++;
            dir = -1;
          } else if (row == 0) {
            col++;
            dir = -1;
          } else { // up-rght
            row--;
            col++;
          }
        } else {
          if (row == rows - 1){
            col++;
            dir = 1;
          } else if (col == 0){
            row++;
            dir = 1;
          } else {
            row++;
            col--;
          }
        }
      }
      return result;
    }
  }

  static final Input input = new Input();

  public static void main(String[] args) {
    int size = input.prompt("Size: ").readInt();
    int[][] matrix = new int[size][];
    for (int i = 0; i < size; i++){
      matrix[i] = input.readIntArray(size);
    }

    Solution sol = new Solution();
    Console.log().print(sol.findDiagonalOrder(matrix));
  }
}
