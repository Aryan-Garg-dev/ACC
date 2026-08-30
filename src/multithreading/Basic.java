package multithreading;

class RunnableLoop implements Runnable {
  private final int threadNumber;

  public RunnableLoop(int threadNumber){
    this.threadNumber = threadNumber;
  }

  @Override
  public void run(){
    for (int i = 0; i <= 5; i++){
      System.out.printf("Thread: %d | ", threadNumber);
      System.out.println(i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}


class Loop extends Thread {
  private final int threadNumber;

  public Loop(int threadNumber){
    this.threadNumber = threadNumber;
  }

  @Override
  public void run(){
    for (int i = 0; i <= 5; i++){
      System.out.printf("Thread: %d | ", threadNumber);
      System.out.println(i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e){
        e.printStackTrace();
      }
    }
  }
}

public class Basic {
  public static void main(String[] args) {
//    for (int i = 0; i < 5; i++) new Loop(i).start();
    for (int i = 0; i < 5; i++) new Thread(new RunnableLoop(i)).start();
  }
}
