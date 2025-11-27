package leetcode.utility;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class TestUtils {
  public static class Test<T, R> {
    public final T input;
    public final R expected;

    public Test(T input, R expected) {
      this.input = input;
      this.expected = expected;
    }
  }

  public static <T, R> void runTests(
    List<Test<T, R>> tests,
    Function<T, R> solver,
    Consumer<T> beforeTest
  ) {
    for (int i = 0; i < tests.size(); i++) {
      var test = tests.get(i);

      beforeTest.accept(test.input);

      R result = solver.apply(test.input);

      if (!result.equals(test.expected)) {
        throw new AssertionError("Test " + i + " failed. " +
          "expected: " + test.expected + ", got: " + result);
      }
      System.out.println("Test " + i + " passed ✅");
    }
  }

  public static <T, R> void runTests(List<Test<T, R>> tests, Function<T, R> solver) {
    runTests(tests, solver, t -> {});
  }
}
