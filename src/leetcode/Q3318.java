package leetcode;

import utility.Console;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Q3318 {
  static class Solution {
    private int xSum(Map<Integer, Integer> count, int x){
      PriorityQueue<int[]> pq = new PriorityQueue<>((p, q)->{
        if (p[1] == q[1]) return q[0] - p[0];
        else return q[1] - p[1];
      });

      for (Map.Entry<Integer, Integer> entry: count.entrySet()){
        pq.add(new int[]{ entry.getKey(), entry.getValue() });
      }

      int sum = 0;
      while (x > 0){
        var entry = pq.poll();
        if (entry != null){
          sum += (entry[0] * entry[1]);
        }
        x--;
      }

      return sum;
    }

    public int[] findXSum(int[] nums, int k, int x) {
      Map<Integer, Integer> count = new HashMap<>();
      int[] result = new int[nums.length - k + 1];
      int iter = 0;

      for (int i = 0; i < k; i++){
        count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
      }
      result[iter++] = xSum(count, x);

      for (int i = k; i < nums.length; i++){
        count.computeIfPresent(nums[i - k], (key, value)->value- 1);
        count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        result[iter++] = xSum(count, x);
      }

      return result;
    }

  }

  public static void main(String[] args) {
    int[] nums = {1,1,2,2,3,4,2,3};
    Console.log().print(new Solution().findXSum(nums, 6, 2));
  }
}
