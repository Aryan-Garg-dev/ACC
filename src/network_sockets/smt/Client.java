package network_sockets.smt;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  private Socket socket;
  private DataOutputStream out;
  private Scanner in;

  public Client() throws IOException {
    try {
      socket = new Socket("localhost", Server.PORT);
      out = new DataOutputStream(socket.getOutputStream());
      in = new Scanner(System.in);
      writeMessages();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private void close(){
    try {
      socket.close();
      out.close();
      in.close();
    } catch(Exception e){
      System.err.println(e.getMessage());
    }
  }

  private void writeMessages() throws IOException {
    for (String line; !((line = in.nextLine()).equals(Server.STOP_STRING)); ){
      out.writeUTF(line);
    }
    close();
  }

  public static void main(String[] args) {
    try {
      new Client();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
