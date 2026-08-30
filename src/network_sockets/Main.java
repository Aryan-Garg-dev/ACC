package network_sockets;
import java.util.Scanner;

public class Main {
  private static final int PORT_NUMBER = 12345;

  public static void main(String ...args){
    try (var scanner = new Scanner(System.in)){
      System.out.println("Is this a server? (y/n)");
      if (scanner.nextLine().equalsIgnoreCase("y")){
        new Server().start(PORT_NUMBER);
      } else {
        new Client().start(PORT_NUMBER, scanner);
      }
    }
  }
}