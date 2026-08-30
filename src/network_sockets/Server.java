package network_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class Server {
  public void start(final int portNumber){
    try (var serverSocket = new ServerSocket(portNumber)){
      try (var executor = Executors.newVirtualThreadPerTaskExecutor()){
        while (true){
          var client = serverSocket.accept();
          executor.submit(() -> {
            System.out.println("Client Connected!");
            var clientIp = client.getInetAddress().getHostAddress();
            var clientPort = client.getPort();
            try (
              var clientInput = new BufferedReader(new InputStreamReader(client.getInputStream()));
              var output = new PrintWriter(client.getOutputStream(), true)
            ) {
              for (String inputLine; (inputLine = clientInput.readLine()) != null;){
                System.out.println("[" + clientIp + ":" + clientPort + "]:> " + inputLine);
                output.println(new StringBuilder(inputLine).reverse());
              }
            } catch (IOException exception){
              throw new RuntimeException(exception);
            }
          });
        }
      }
    } catch (IOException exception){
      throw new RuntimeException(exception);
    }
  }
}
