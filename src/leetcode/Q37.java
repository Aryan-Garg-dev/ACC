package leetcode;

import java.util.Arrays;
import java.util.Optional;

public class Q37 {
  static class Solution {
    private static boolean _canPlace(char[][] board, int row, int col, char digit){
      int // Total 9 grids (3 rows and 3 cols)
        grid_row = (row / 3) * 3,
        grid_col = (col / 3) * 3;
      for (int i = 0; i < 9; i++){
        if (board[row][i] == digit || board[i][col] == digit) return false;
        if (board[grid_row + i / 3][grid_col + i % 3] == digit) return false;
      }
      return true;
    }

    private static boolean _validateAndSolveSudoku(char[][] board, int row, int col){
      if (row == 9) return true;
      int nextRow = col == 8 ? row + 1: row;
      int nextCol = col == 8 ? 0 : col + 1;
      if (board[row][col] != '.') return _validateAndSolveSudoku(board, nextRow, nextCol);
      for (char digit = '1'; digit <= '9'; digit++){
        if (_canPlace(board, row, col, digit)){
          board[row][col] = digit;
          if (_validateAndSolveSudoku(board, nextRow, nextCol)) return true;
          board[row][col] = '.';
        }
      }
      return false;
    }

    public void solveSudoku(char[][] board) {
      _validateAndSolveSudoku(board, 0, 0);
    }
  }

  static class OptimisedSolution {
    private final boolean[][]
      _rowUsed = new boolean[9][9],
      _colUsed = new boolean[9][9],
      _gridUsed = new boolean[9][9];

    private void _preProcess(char[][] board){
      for (int _r = 0; _r < 9; _r++){
        for (int _c = 0; _c < 9; _c++){
          if (board[_r][_c] != '.'){
            int digit = board[_r][_c] - '1';
            int grid = (_r / 3) * 3 + (_c / 3);
            _rowUsed[_r][digit] = _colUsed[_c][digit] = _gridUsed[grid][digit] = true;
          }
        }
      }
    }

    private boolean _validateAndSolveSudoku(char[][] board, int row, int col){
      if (row == 9) return true;
      int nextRow = (col == 8) ? row + 1: row;
      int nextCol = (col == 8) ? 0 : col + 1;

      if (board[row][col] != '.') return _validateAndSolveSudoku(board, nextRow, nextCol);

      int grid = (row / 3) * 3 + (col / 3);
      for (int d = 0; d < 9; d++){
        if (!_rowUsed[row][d] && !_colUsed[col][d] && !_gridUsed[grid][d]){
          board[row][col] = (char) (d + '1');
          _rowUsed[row][d] = _colUsed[col][d] = _gridUsed[grid][d] = true;

          if (_validateAndSolveSudoku(board, nextRow, nextCol)) return true;

          board[row][col] = '.';
          _rowUsed[row][d] = _colUsed[col][d] = _gridUsed[grid][d] = false;
        }
      }

      return false;
    }

    public void solveSudoku(char[][] board){
      _preProcess(board);
      _validateAndSolveSudoku(board, 0, 0);
    }
  }

  public static void printSudoku(char[][] board){
    for (var row: board){
      System.out.println(Arrays.toString(row));
    }
  }

  public static void main(String...args){
    char[][] validSudoku = {
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    char[][] invalidSudoku = {
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '5', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    OptimisedSolution sol = new OptimisedSolution();

    sol.solveSudoku(validSudoku);
    printSudoku(validSudoku);
    System.out.println();

    sol.solveSudoku(invalidSudoku);
    printSudoku(invalidSudoku);
  }
}
