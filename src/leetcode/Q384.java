package leetcode;

import java.util.*;

public class Q384 {
  static class Solution {
    int[] nums;
    int[] copy;
    static Random rand = new Random();


    public Solution(int[] nums) {
      this.nums = nums;
      this.copy = nums.clone();
    }

    public int[] reset() {
      return nums;
    }

    public int[] shuffle() {
      for (int i = nums.length - 1; i > 0; i--){
        int j = rand.nextInt(i + 1);
        swap(copy, i, j);
      }

      return copy;
    }

    private void swap(int[] array, int i, int j){
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
    }
  }

  public static void main(String[] args) {

  }
}
