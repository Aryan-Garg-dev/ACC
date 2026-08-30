package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
  static class Example1 {
    public static AtomicInteger count = new AtomicInteger(0);
    public static void run(String[] args) throws InterruptedException {

      var counterThread1 = new Thread(() -> {
        for (int i = 0; i < 1_00_000; i++) count.incrementAndGet();
      });

      var counterThread2 = new Thread(() -> {
        for (int i = 0; i < 1_00_000; i++) count.incrementAndGet();
      });

      counterThread1.start();
      counterThread2.start();
      counterThread1.join();
      counterThread2.join();
      System.out.println(count.get());
    }
  }


  public static void main(String[] args) throws InterruptedException {
    Example1.run(args);
  }
}
