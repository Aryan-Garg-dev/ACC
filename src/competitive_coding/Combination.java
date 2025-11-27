package competitive_coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
  private static List<List<Integer>> result;

  private static void helper(int[] nums, int k, int idx, List<Integer> current){
    if (current.size() == k) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = idx; i < nums.length; i++){
      current.add(nums[i]);
      helper(nums, k, i + 1, current);
      current.removeLast();
    }
  }

  public static List<List<Integer>> get(int[] nums, int k){
    result = new ArrayList<>();
    Arrays.sort(nums);
    helper(nums, k, 0, new ArrayList<>());
    return result;
  }
}
