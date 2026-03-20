package competitive_coding_2.cat_2;

import utility.Console;

import java.util.*;

// Time: O(V + E + W*V) [where w -> max weight
// Space: O(V + W*V)
// faster for small W
public class DialsAlgorithm {
  static class Edge {
    int to, weight;
    Edge(int to, int weight){
      this.to = to;
      this.weight = weight;
    }
  }

  static class Graph {
    private final int V;
    private final Map<Integer, List<Edge>> graph;
    private int maxWeight;

    public Graph(int V){
      this.V = V;
      graph = new HashMap<>();
      for (int i = 0; i < V; i++)
        graph.put(i, new ArrayList<>());
    }

    public void addEdge(int u, int v, int w){
      graph.get(u).add(new Edge(v, w));
      graph.get(v).add(new Edge(u, w));
      maxWeight = Math.max(maxWeight, w);
    }

    public int[] shortestPath(int src){
      int maxDistance = maxWeight * V;

      int[] dist = new int[V];
      Arrays.fill(dist, Integer.MAX_VALUE);
      dist[src] = 0;

      List<Integer>[] buckets = new ArrayList[maxDistance + 1];
      for (int i = 0; i <= maxDistance; i++) buckets[i] = new ArrayList<>();
      buckets[0].add(src);

      int idx = 0;
      while (idx <= maxDistance){
        while (idx <= maxDistance && buckets[idx].isEmpty()) idx++;
        if (idx > maxDistance) break;

        int u = buckets[idx].removeFirst();
        // skip outdated entries
        if (dist[u] < idx) continue;

        for (Edge edge: graph.get(u)){
          int v = edge.to;
          int w = edge.weight;

          if (dist[u] + w < dist[v]){
            dist[v] = dist[u] + w;
            buckets[dist[v]].add(v);
          }
        }
      }

      return dist;
    }
  }

  public static void main(String[] args) {
    int V = 9;
    int src = 0;
    int[][] edges = {
      {0, 1, 4},
      {0, 7, 8},
      {1, 2, 8},
      {1, 7, 11},
      {2, 3, 7},
      {2, 8, 2},
      {3, 4, 9},
      {3, 5, 14},
      {4, 5, 10},
      {5, 6, 2},
      {6, 7, 1},
      {6, 8, 6},
      {7, 8, 7}
    };

    Graph g = new Graph(V);
    for (int[] edge: edges) g.addEdge(edge[0], edge[1], edge[2]);

    int[] res = g.shortestPath(src);
    Console.log().println(res);
  }
}
