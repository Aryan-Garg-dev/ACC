package competitive_coding_2.cat_1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SortQueue {
  static void sortQueue(Queue<Integer> q){
    int n = q.size();

    for (int i = 0; i < n; i++){
      int min = Integer.MAX_VALUE;
      int minIndex = -1;

      // min in unsorted part
      for (int j = 0; j < n; j++){
        int val = q.poll();
        if (j < n - i && val < min){
          min = val;
          minIndex = j;
        }
        q.add(val);
      }

      // remove that min
      for (int j = 0; j < n; j++){
        int val = q.poll();
        if (j != minIndex) q.add(val);
      }

      q.add(min);
    }
  }

  public static void main(String[] args) {
    Queue<Integer> q = new LinkedList<>(List.of(10, 7, 8, 2, 6));
    sortQueue(q);
    System.out.println(q);
  }
}
