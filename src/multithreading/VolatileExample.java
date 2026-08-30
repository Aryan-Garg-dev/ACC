package multithreading;

public class VolatileExample {
  static class Example1 {
    public static volatile boolean flag = false;
    public static void run(String[] args) throws InterruptedException {
      long start = System.nanoTime();
      new Thread(() -> {
        long n = 0;
        while (!flag) n++;
        long end = System.nanoTime();
        System.out.println("Stopped: " + n + " after " + (end - start) / 1_000_000.0 + " ms");
      }).start();
      Thread.sleep(1000);
      flag = true;
    }
  }

  static class Example2 {
    public static volatile int count = 0;
    public static void run(String[] args) throws InterruptedException {

      var counterThread1 = new Thread(() -> {
        for (int i = 0; i < 1_00_000; i++) synchronized (VolatileExample.class){
          count++;
        }
      });

      var counterThread2 = new Thread(() -> {
        for (int i = 0; i < 1_00_000; i++) synchronized (VolatileExample.class){
          count++;
        }
      });

      counterThread1.start();
      counterThread2.start();
      counterThread1.join();
      counterThread2.join();
      System.out.println(count);
    }
  }

  public static void main(String[] args) throws InterruptedException {
//    Example1.run(args);
    Example2.run(args);
  }
}
