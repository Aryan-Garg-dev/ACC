package network_sockets.udp;

import utility.Input;
import utility.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient implements Closeable {
  private DatagramSocket socket;
  private static final int BUFFER_SIZE = 4 * 1024;
  private final InetAddress serverAddress = InetAddress.getByName("localhost");
  private final int serverPort;

  public UDPClient(int serverPort) throws IOException {
    socket = new DatagramSocket();
    this.serverPort = serverPort;
  }

  public void run() throws IOException {
    Input input = new Input();

    while (!socket.isClosed()){
      String message = input.prompt("Enter Message: ").readLine().trim();
      byte[] buffer = message.getBytes();

      DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
      socket.send(packet);
      Logger.info().print("Sent: ").println(message);

      if (message.equalsIgnoreCase("end")){
        socket.close();
        break;
      }

      buffer = new byte[BUFFER_SIZE];
      packet = new DatagramPacket(buffer, buffer.length);
      socket.receive(packet);

      message = new String(buffer).trim();
      Logger.info().println("Received: " + message);
    }
  }

  public void close(){
    socket.close();
  }

  public static void main(String[] args) {
    try (UDPClient client = new UDPClient(5000)){
      client.run();
    } catch (IOException e){
      Logger.error().println(e.getMessage());
    }
  }
}
