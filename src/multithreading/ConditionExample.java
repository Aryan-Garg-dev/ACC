package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
  private final Lock lock = new ReentrantLock();
  private final Condition conditionMet = lock.newCondition();

  public void method1() throws InterruptedException {
    lock.lock();
    try {
      // PRONE to spurious wake-ups (wrap under while loop)
      conditionMet.await();
      // can do dependant operations
    } finally {
      lock.unlock();
    }
  }

  public void method2(){
    lock.lock();
    try {
      // do some operations
      conditionMet.signalAll();
    } finally {
      lock.unlock();
    }
  }
}
