package competitive_coding;

public class Maze {
  private static boolean[][] visited;
  private static int N;

  private static boolean travel(int[][] maze, int i, int j){
    if (i < 0 || i >= N || j < 0 || j >= N || visited[i][j]) return false;
    if (i == N - 1 && j == N - 1) return true;
    visited[i][j] = true;
    if (
      travel(maze, i - 1, j) ||
      travel(maze, i, j - 1) ||
      travel(maze, i, j + 1) ||
      travel(maze, i + 1, j)
    ) return true;
    visited[i][j] = false;
    return false;
  }

  public static boolean canTravel(int[][] maze){
    N = maze.length;
    visited = new boolean[N][N];
    return travel(maze, 0, 0);
  }
}
