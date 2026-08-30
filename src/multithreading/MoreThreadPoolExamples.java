package multithreading;

import java.util.Random;
import java.util.concurrent.*;

public class MoreThreadPoolExamples {

  static class Task implements Runnable {
    private final int taskId;

    Task(int taskId){
      this.taskId = taskId;
    }

    @Override
    public void run() {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      System.out.println("Task: " + taskId + " Thread: " + Thread.currentThread().getName());
    }
  }

  static class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
      System.out.println("Thread: " + Thread.currentThread().getName());
      Thread.sleep(3000);
      return new Random().nextInt(0, Integer.MAX_VALUE);
    }
  }

  static class FixedThreadPoolExample {

    public static void run(String[] args) {
      int availableCores = Runtime.getRuntime().availableProcessors(); // 24
      ExecutorService executorService = Executors.newFixedThreadPool(availableCores);

      for (int i = 0; i < 1_000; i++)
        executorService.execute(new Task(i));

      executorService.shutdown();
      System.out.println("Thread: " + Thread.currentThread().getName());
    }

  }

  static class CachedThreadPoolExample {

    public static void run(String[] args) {
      ExecutorService executorService = Executors.newCachedThreadPool();

      // Add additional threads as needed instead of waiting for threads to unblock
      for (int i = 0; i < 1_000; i++)
        executorService.execute(new Task(i));

      executorService.shutdown();
      System.out.println("Thread: " + Thread.currentThread().getName());
    }

  }

  static class ScheduledThreadPoolExample {

    public static void run(String[] args) {
      ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

      // task to run after 10 second delay
      executorService.schedule(new Task(1), 10, TimeUnit.SECONDS);
      // Task to run repeatedly every 10 seconds
      executorService.scheduleAtFixedRate(new Task(2), 15, 10, TimeUnit.SECONDS);
      // Task to run repeatedly 10 seconds after the previous task completes
      executorService.scheduleWithFixedDelay(new Task(3), 15, 10, TimeUnit.SECONDS);

//      executorService.shutdown();
      System.out.println("Thread: " + Thread.currentThread().getName());
    }

  }

  static class SingleThreadPoolExample {

    public static void run(String[] args) {
      ExecutorService executorService = Executors.newSingleThreadExecutor();

      // Always run in order
      for (int i = 0; i < 1_000; i++)
        executorService.execute(new Task(i));

      executorService.shutdown();
      System.out.println("Thread: " + Thread.currentThread().getName());
    }

  }


  static class CallableTaskExample {
    public static void run(String[] args) {
      try (ExecutorService executorService = Executors.newFixedThreadPool(10)){
        Future<Integer> future = executorService.submit(new CallableTask());
        Integer result = future.get(1, TimeUnit.SECONDS); // blocking operation until future is ready to return
        System.out.println("Result: " + result + " Thread Name: " + Thread.currentThread().getName());
      } catch (Exception e) {
        System.out.println(e.getClass());
//        e.printStackTrace();
      }
    }
  }

  static class CustomThreadPoolExample {
    private static class CustomRejectionHandler implements RejectedExecutionHandler {
      @Override
      public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        Task t = r instanceof Task ? ((Task) r) : null;
        if (t != null) System.out.println("Rejected Execution of Task: " + t.taskId);
      }
    }

    public static void run(String[] args) {
      try (var executorService = new ThreadPoolExecutor(
        10, 100,
        120, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(300),
        new CustomRejectionHandler()
      )) {
        for (int i = 0; i < 1000; i++)
          executorService.execute(new Task(i));
        executorService.shutdown();
      } catch (Exception e){
        System.out.println(e.getClass());
      }
    }

  }

  static class VirtualThreadPoolExample {
    public static void run(String[] args) {
      Semaphore semaphore = new Semaphore(100);
      try (var executorService = Executors.newVirtualThreadPerTaskExecutor()){
        for (int i = 0; i < 1000; i++){
          int idx = i;
          executorService.submit(() -> {
            try {
              semaphore.acquire();
              new Task(idx).run();
            } catch (Exception e){
              System.out.println(e.getClass());
            } finally {
              semaphore.release();
            }
          });
        }
      } catch (Exception e){
        System.out.println(e.getClass());
      }
    }

  }



  public static void main(String[] args){
    VirtualThreadPoolExample.run(args);
  }
}
