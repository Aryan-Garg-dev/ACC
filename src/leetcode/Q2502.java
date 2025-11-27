package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q2502 {
  static class Allocator {
    private final int[] memory;

    public Allocator(int n) {
      this.memory = new int[n];
    }

    public int allocate(int size, int mID) {
      int n = memory.length, count = 0;
      for (int i = 0; i < n; i++){
        if (memory[i] == 0){
          count++;
          if (count == size){
            int start = i - size + 1;
            for (int j = start; j < size; j++) memory[j] = mID;
            return start;
          }
        } else {
          count = 0;
        }
      }

      return -1;
    }

    public int freeMemory(int mID) {
      int freed = 0;
      for (int i = 0; i < memory.length; i++) {
        if (memory[i] == mID) {
          memory[i] = 0;
          freed++;
        }
      }
      return freed;
    }
  }
}
