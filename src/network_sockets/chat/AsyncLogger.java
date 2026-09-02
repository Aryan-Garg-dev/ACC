package network_sockets.chat;

import java.util.concurrent.ConcurrentLinkedQueue;

public class AsyncLogger {
  private final ConcurrentLinkedQueue<String> logQueue = new ConcurrentLinkedQueue<>();

  private void log(String message){
    logQueue.add(message);
  }

  void log(Object o){
    logQueue.add(o.toString());
  }

  private void log(String format, Object ...args){
    logQueue.add(String.format(format, args));
  }

  public final void start(){
    Thread loggerThread = new Thread(() -> {
      while (true) {
        String logEntry = logQueue.poll();
        if (logEntry != null) {
          System.out.println("[LOG]" + logEntry);
        }
      }
    });
    loggerThread.setDaemon(true);
    loggerThread.start();
  }
}
