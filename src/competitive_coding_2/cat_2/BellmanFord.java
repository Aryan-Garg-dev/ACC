package competitive_coding_2.cat_2;

import utility.Logger;

import java.util.ArrayList;
import java.util.List;

// Time Complexity O(V*E)
public class BellmanFord {
  static class Graph {
    static class Edge {
      int src, dest, weight;
      public Edge(int src, int dest, int weight){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
      }
    }

    private final int V, E;
    private final List<Edge> edges;

    public Graph(int v, int e){
      V = v;
      E = e;
      edges = new ArrayList<>();
    }

    public void createEdge(int u, int v, int w){
      edges.add(new Edge(u, v, w));
    }

    public int[] bellmanFord(int src){
      int[] dist = new int[V];
      for (int i = 0; i < V; i++)
        dist[i] = Integer.MAX_VALUE;
      dist[src] = 0;
      for (int i = 1; i < V; i++){
        for (int j = 0; j < E; j++){
          Edge edge = edges.get(j);
          int u = edge.src, v = edge.dest, w = edge.weight;
          if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
            dist[v] = dist[u] + w;
        }
      }

      for (int j = 0; j < E; j++){
        Edge edge = edges.get(j);
        int u = edge.src, v = edge.dest, w = edge.weight;
        if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]){
          System.err.println("Negative weight detected");
          return null;
        }
      }

      return dist;
    }
  }

  public static void main(String[] args) {
    int V = 5, E = 7;
    int[][] graph = {
      {0, 1, 5},
      {0, 2, 4},
      {1, 3, 3},
      {2, 1, 6},
      {3, 2, 4},
      {1, 4, -4},
      {4, 2, 2},
    };

    Graph g = new Graph(V, E);
    for (int[] edge: graph)
      g.createEdge(edge[0], edge[1], edge[2]);

    Logger.log().println(g.bellmanFord(0));
  }
}
