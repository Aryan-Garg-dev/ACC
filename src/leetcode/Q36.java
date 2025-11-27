package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Q36 {
  static class Solution {
    public boolean isValidSudoku(char[][] board){
      for (int row = 0; row < 9; row++){
        boolean[]
          rowCheck = new boolean[9],
          colCheck = new boolean[9],
          gridCheck = new boolean[9];

        for (int col = 0; col < 9; col++){

          // Horizontal
          if (board[row][col] != '.'){
            int num = board[row][col] - '1';
            if (rowCheck[num]) return false;
            rowCheck[num] = true;
          }

          if (board[col][row] != '.'){
            int num = board[col][row] - '1';
            if (colCheck[num]) return false;
            colCheck[num] = true;
          }

          int gridRow = (row / 3) * 3 + col / 3;
          int gridCol = (row % 3) * 3 + col % 3;
          if (board[gridRow][gridCol] != '.'){
            int num = board[gridRow][gridCol] - '1';
            if (gridCheck[num]) return false;
            gridCheck[num] = true;
          }
        }
      }
      return true;
    }
  }

  public static void main(String...args){
    char[][] validBoard = {
      {'5','3','.','.','7','.','.','.','.'},
      {'6','.','.','1','9','5','.','.','.'},
      {'.','9','8','.','.','.','.','6','.'},
      {'8','.','.','.','6','.','.','.','3'},
      {'4','.','.','8','.','3','.','.','1'},
      {'7','.','.','.','2','.','.','.','6'},
      {'.','6','.','.','.','.','2','8','.'},
      {'.','.','.','4','1','9','.','.','5'},
      {'.','.','.','.','8','.','.','7','9'}
    };
    char[][] invalidRow = {
      {'5','3','.','.','7','.','.','3','.'},
      {'6','.','.','1','9','5','.','.','.'},
      {'.','9','8','.','.','.','.','6','.'},
      {'8','.','.','.','6','.','.','.','3'},
      {'4','.','.','8','.','3','.','.','1'},
      {'7','.','.','.','2','.','.','.','6'},
      {'.','6','.','.','.','.','2','8','.'},
      {'.','.','.','4','1','9','.','.','5'},
      {'.','.','.','.','8','.','.','7','9'}
    };

    Solution sol = new Solution();
    System.out.println(sol.isValidSudoku(validBoard));
    System.out.println(sol.isValidSudoku(invalidRow));
  }
}
