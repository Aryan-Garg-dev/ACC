package network_sockets.smt;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  private final ServerSocket server;
  private DataInputStream in;

  public static final int PORT = 5000;
  public static final String STOP_STRING = "!#";

  public Server() throws IOException {
    server = new ServerSocket(PORT);
    initConnections();
  }

  public void initConnections() throws IOException {
    Socket client = server.accept();
    in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
    readMessages();
  }

  private void close(){
    try {
      server.close();
      in.close();
    } catch(Exception e){
      System.err.println(e.getMessage());
    }
  }

  private void readMessages() throws IOException {
    String line;
    while (!(line = in.readUTF()).equals(STOP_STRING)){
      System.out.println(line);
    }
  }

  public static void main(String[] args) {
    try {
      new Server();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
