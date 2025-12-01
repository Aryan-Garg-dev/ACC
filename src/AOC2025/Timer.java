package AOC2025;

import utility.Console;

import java.time.*;
import java.util.function.*;

public class Timer {

  public static <T> T measure(Supplier<T> fn) {
    Instant start = Instant.now();
    T result = fn.get();
    Instant end = Instant.now();
    Console.info()
      .print("Time Elapsed: ")
      .print(Duration.between(start, end).toMillis())
      .println(" ms");
    return result;
  }

  public static void measure(Runnable fn) {
    Instant start = Instant.now();
    fn.run();
    Instant end = Instant.now();
    Console.info()
      .print("Time Elapsed: ")
      .print(Duration.between(start, end).toMillis())
      .println(" ms");
  }
}
