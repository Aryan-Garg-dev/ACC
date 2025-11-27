package leetcode;

import java.util.Arrays;

public class Q304 {
  static class NumMatrix {

    int[][] matrix;

    public NumMatrix(int[][] matrix) {
      this.matrix = matrix;
      _preProcessMatrix();
    }

    private void _preProcessMatrix(){
      if (matrix == null) return;
      final int rows = matrix.length, cols = matrix[0].length;
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          matrix[r][c] +=
            (r > 0 ? matrix[r - 1][c] : 0) +
              (c > 0 ? matrix[r][c - 1] : 0) -
              (r > 0 && c > 0 ? matrix[r - 1][c - 1] : 0);
        }
      }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      if (matrix == null) return 0;
      return
        matrix[row2][col2] +
          (row1 > 0 && col1 > 0 ? matrix[row1-1][col1-1] : 0) -
          (row1 > 0 ? matrix[row1-1][col2]: 0) -
          (col1 > 0 ? matrix[row2][col1-1]: 0);
    }
  }
}
