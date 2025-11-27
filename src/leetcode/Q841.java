package leetcode;

import utility.Console;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q841 {
  static class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms){
      int total_rooms = rooms.size();
      boolean[] visited = new boolean[total_rooms];
      Queue<Integer> q = new LinkedList<>();
      q.add(0);

      while (!q.isEmpty()){
        int room = q.poll();
        visited[room] = true;
        for (int i: rooms.get(room)){
          if (!visited[i]) q.add(i);
        }
      }

      for (int room = 0; room < total_rooms; room++){
        if (!visited[room]) return false;
      }

      return true;
    }
  }

  public class OptimizedSolution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms){
      boolean[] visited = new boolean[rooms.size()];
      _dfs(0, rooms, visited);
      for (var v: visited){
        if (!v) return false;
      }
      return true;
    }

    private void _dfs(int room, List<List<Integer>> rooms, boolean[] visited){
      if (visited[room]) return;
      visited[room] = true;
      for (int key: rooms.get(room)) _dfs(key, rooms, visited);
    }
  }

  public static void main(String[] args) {
    Solution sol = new Solution();
    List<List<Integer>> rooms = new ArrayList<>(List.of(
      List.of(1),
      List.of(2),
      List.of(3),
      List.of()
    ));
    Console.log().println(sol.canVisitAllRooms(rooms));

    rooms = new ArrayList<>(List.of(
      List.of(1, 3),
      List.of(3, 0, 1),
      List.of(2),
      List.of(0)
    ));
    Console.log().println(sol.canVisitAllRooms(rooms));
  }
}
