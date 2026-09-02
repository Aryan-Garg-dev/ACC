package network_sockets.ftp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FTPServer {
  private ServerSocket server;
  public static final int PORT = 5000;
  public static final String FILES_PATH="./resources";
  private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();


  public FTPServer(){
    try {
      server = new ServerSocket(PORT);
      acceptConnections();
    } catch (IOException e){
      System.err.println(e.getMessage());
    }
  }

  private void acceptConnections() throws IOException {
    while (!server.isClosed()){
      Socket client = server.accept();
      if (client.isConnected()){
        executorService.submit(() -> {
          Connection connection = new Connection(client);
          connection.sendFile();
          connection.close();
        });
      }
    }
  }

  public static void main(String[] args) {
    new FTPServer();
  }
}
