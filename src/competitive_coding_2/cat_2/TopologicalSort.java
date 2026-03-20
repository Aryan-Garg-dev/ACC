package competitive_coding_2.cat_2;

import java.util.*;
import java.util.LinkedList;

public class TopologicalSort {
  static class Graph {
    private final int vertices;
    private final Map<Integer, List<Integer>> adjacencyList;

    public Graph(int vertices){
      this.vertices = vertices;
      this.adjacencyList = new HashMap<>();
      for (int i = 0; i < vertices; i++)
        this.adjacencyList.put(i, new ArrayList<>());
    }

    public void createEdge(int u, int v){
      this.adjacencyList.get(u).add(v);
    }

    public List<Integer> topologicalSort(){
      int[] inDegree = new int[this.vertices];
      for (int i = 0; i < vertices; i++)
        for (int j: adjacencyList.get(i))
          inDegree[j]++;


      Queue<Integer> queue = new LinkedList<>();
      for (int i = 0; i < vertices; i++)
        if (inDegree[i] == 0) queue.add(i);

      int visitedNodes = 0;
      List<Integer> order = new ArrayList<>();

      while (!queue.isEmpty()){
        int u = queue.poll();
        order.add(u);

        for (int i: adjacencyList.get(u))
          if (--inDegree[i] == 0) queue.add(i);

        visitedNodes++;
      }

      if (visitedNodes != vertices){
        System.err.println(
          "There's a cycle present in the Graph.\n" +
          "Given graph is not DAG."
        );
        return null;
      }

      return order;
    }
  }

  // Khan's Algo: Time Complexity O(V + E)
  public static void main(String[] args) {
    int[][] graph = {{0, 1}, {0, 2}, {1, 3}, {1, 3}, {1, 5}, {2, 3}, {2, 5}, {3, 4}, {5, 4}};
    Graph g = new Graph(6);
    for (int[] edge: graph) g.createEdge(edge[0], edge[1]);
    System.out.println(g.topologicalSort());
  }
}
