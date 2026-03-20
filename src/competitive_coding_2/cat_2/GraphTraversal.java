package competitive_coding_2.cat_2;
import java.util.*;

public class GraphTraversal {
  public static class Graph {
    private int V;
    private List<List<Integer>> adj;

    public Graph(int v) {
      V = v;
      adj = new ArrayList<>();
      for (int i = 0; i < V; i++)
        adj.add(new ArrayList<>());
    }

    public void addEdge(int u, int v) {
      adj.get(u).add(v);
      adj.get(v).add(u); // remove for directed graph
    }

    // ─── BFS ───────────────────────────────────────────
    public void bfs(int start) {
      boolean[] visited = new boolean[V];
      Queue<Integer> queue = new LinkedList<>();

      visited[start] = true;
      queue.add(start);

      while (!queue.isEmpty()) {
        int node = queue.poll();
        System.out.print(node + " ");

        for (int neighbor : adj.get(node)) {
          if (!visited[neighbor]) {
            visited[neighbor] = true;
            queue.add(neighbor);
          }
        }
      }
    }

    // ─── DFS recursive ─────────────────────────────────
    public void dfsRecursive(int start) {
      boolean[] visited = new boolean[V];
      dfsHelper(start, visited);
    }

    private void dfsHelper(int node, boolean[] visited) {
      visited[node] = true;
      System.out.print(node + " ");

      for (int neighbor : adj.get(node))
        if (!visited[neighbor])
          dfsHelper(neighbor, visited);
    }
  }
}
