package leetcode;

import utility.Console;

public class Q547 {
  static class Solution {
    private boolean[] _visited;
    private int _n;
    private int[][] _isConnected;

    public int findCircleNum(int[][] isConnected) {
      _n = isConnected.length;
      _visited = new boolean[_n];
      _isConnected = isConnected;

      int provinces = 0;
      for (int i = 0; i < _n; i++){
        if (!_visited[i]){
          provinces += 1;
          _dfs(i);
        }
      }

      return provinces;
    }

    private void _dfs(int i){
      _visited[i] = true;
      for (int j = 0; j < _n; j++){
        if (_isConnected[i][j] == 1 && !_visited[j]){
          _dfs(j);
        }
      }
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    
    int[][] isConnected = {
      { 1, 1, 0 },
      { 1, 1, 0 },
      { 0, 0, 1 },
    };

    Console.log().println(sol.findCircleNum(isConnected));

    isConnected = new int[][]{
      { 1, 0, 0 },
      { 0, 1, 0 },
      { 0, 0, 1 },
    };

    Console.log().println(sol.findCircleNum(isConnected));
  }
}
