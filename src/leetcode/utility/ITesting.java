package leetcode.utility;

import java.util.function.Function;

public interface ITesting<T, R> {
  void runTests(Function<T, R> solver);
}
