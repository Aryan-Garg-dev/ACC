package leetcode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Q380 {
  static class RandomizedSet {
    private final Set<Integer> set;
    private static final Random random = new Random();

    public RandomizedSet() {
      set = new HashSet<>();
    }

    public boolean insert(int val) {
      return set.add(val);
    }

    public boolean remove(int val) {
      return set.remove(val);
    }

    public int getRandom() {
      int idx = random.nextInt(set.size() - 1);
      return (int) set.toArray()[idx];
    }
  }
}
