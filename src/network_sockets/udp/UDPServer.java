package network_sockets.udp;

import utility.logger.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.net.*;

public class UDPServer implements Closeable {
  private static final int BUFFER_SIZE = 4 * 1024;

  private final DatagramSocket socket;

  public UDPServer(int portNumber) throws SocketException {
    socket = new DatagramSocket(portNumber);
  }

  public void run() throws IOException {
    while (!socket.isClosed()){
      byte[] buffer = new byte[BUFFER_SIZE];
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      socket.receive(packet);

      InetAddress address = packet.getAddress();
      int port = packet.getPort();
      packet = new DatagramPacket(buffer, buffer.length, address, port);
      String message = new String(packet.getData(), 0, packet.getLength());

      Logger.info().print("Received: ").println(message);

      if (message.equals("end")) break;

      message = "Got: " + message;
      buffer = message.getBytes();
      packet = new DatagramPacket(buffer, buffer.length, address, port);
      socket.send(packet);
      Logger.info().print("Sent: ").println(message);
    }
    socket.close();
  }

  public void close(){
    socket.close();
  }

  public static void main(String[] args) {
    try (UDPServer server = new UDPServer(5000)){
      server.run();
    } catch (IOException e){
      Logger.error().println(e.getMessage());
    }
  }
}
