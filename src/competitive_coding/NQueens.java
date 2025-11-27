package competitive_coding;

import utility.Console;

public class NQueens {
  public static int ans;
  public static int[] queenPos;

  public static int solve(int n){
    ans = 0;
    queenPos = new int[n];
    solve(0, 0, 0, 0, n);
    return ans;
  }

  private static void solve(int row, int cols, int diag1, int diag2, int n) {
    if (row == n) {
      ans++;
      Console.log().print(queenPos);
      return;
    }

    int available = (~(cols | diag1 | diag2)) & ((1 << n) - 1);

    while (available != 0) {
      int pick = available & -available;  // rightmost 1-bit
      available -= pick;

      int col = Integer.numberOfTrailingZeros(pick);
      queenPos[row] = col;

      solve(row + 1,
        cols | pick,
        (diag1 | pick) << 1,
        (diag2 | pick) >> 1,
        n);
    }
  }

  public static void main(String[] args) {
    NQueens.solve(4);
  }
}
