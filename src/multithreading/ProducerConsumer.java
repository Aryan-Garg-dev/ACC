package multithreading;

import javax.sound.sampled.Line;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SynchronizedBoundedBuffer<T> {
  private final Queue<T> buffer = new LinkedList<>();
  private final int capacity;

  public SynchronizedBoundedBuffer(int capacity) {
    this.capacity = capacity;
  }

  public synchronized void put(T item) throws InterruptedException {
    while (buffer.size() == capacity) {
      wait();
    }
    buffer.offer(item);
    notifyAll();
  }

  public synchronized T take() throws InterruptedException {
    while (buffer.isEmpty()) {
      wait();
    }
    T item = buffer.poll();
    notifyAll();
    return item;
  }
}

class BoundedBuffer<T> {
  private final Queue<T> buffer = new LinkedList<>();
  private final int capacity;

  private final Lock lock = new ReentrantLock();
  public final Condition notFull = lock.newCondition();
  public final Condition notEmpty = lock.newCondition();

  BoundedBuffer(int capacity){
    this.capacity = capacity;
  }

  public void put(T item) throws InterruptedException {
    lock.lock();
    try {
      while (buffer.size() == this.capacity) notFull.await();
      buffer.add(item);
      notEmpty.signalAll();
    } finally {
      lock.unlock();
    }
  }

  public T take() throws InterruptedException {
    lock.lock();
    try {
      while (buffer.isEmpty()) notEmpty.await();
      T item = buffer.poll();
      notFull.signalAll();
      return item;
    } finally {
      lock.unlock();
    }
  }
}

public class ProducerConsumer {
}
