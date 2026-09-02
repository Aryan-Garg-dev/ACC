package network_sockets.chat;

import utility.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient implements Closeable {
  private final Socket socket;
  private final BufferedReader in;
  private final PrintWriter out;

  public ChatClient(String host, int port) throws IOException {
    socket = new Socket(host, port);
    Logger.info().printf("Connecting to %s:%d\n", host, port);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
  }

  public void send(String message){
    out.println(message);
  }

  public String receive() throws IOException {
    return in.readLine();
  }

  @Override
  public void close(){
    try {
      in.close();
      out.close();
      socket.close();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("Usage: ChatClient <host> <port>");
      return;
    }

    try {
      String host = args[0];
      int port = Integer.parseInt(args[1]);

      try (
        var sc = new Scanner(System.in);
        var client = new ChatClient(host, port);
      ){
        Logger.info().printf("Connected to %s:%d\n", host, port);

        Thread receiver = receiverThread(client);
        receiver.start();

        while (sc.hasNextLine()){
          String input = sc.nextLine();
          if (input.isBlank() || !input.startsWith("/")) {
            System.err.println("Invalid command. Refer /help.");
            continue;
          }

          String[] head = input.trim().split(" ", 2);
          String cmd = head[0].substring(1).toLowerCase();
          String rest = head.length > 1 ? head[1] : "";

          if (!isValidCmd(cmd, rest)) {
            System.err.println("Invalid Arguments. Refer /help.");
            continue;
          }

          if (Cmd.HELP.equals(cmd)){
            printHelpMenu();
            continue;
          }

          client.send(input);
          if (Cmd.EXIT.equals(cmd)) break;
        }
      } catch (IOException | IllegalArgumentException e){
        System.err.println(e.getMessage());
      }

    } catch (NumberFormatException e){
      System.err.println("Invalid Port Number");
    }


  }

  private static Thread receiverThread(ChatClient client) {
    Thread receiver = new Thread(() -> {
      try {
        String response;
        while ((response = client.receive()) != null) {
          Logger.log().println(response);
        }
        Logger.debug().println("Server disconnected.");
      } catch (IOException e){
        if (!client.socket.isClosed()) {
          System.err.println(
            "Connection error: " + e.getMessage()
          );
        }
      }
    });

    receiver.setDaemon(true);
    return receiver;
  }

  private static boolean isValidCmd(String cmd, String rest){
    if (Cmd.SEND.equals(cmd)) {
      String[] parts = rest.split(" ", 2);
      return parts.length == 2 && !parts[0].isBlank() && !parts[1].isBlank();
    }
    if (Cmd.REGISTER.equals(cmd)) {
      return !rest.isBlank() && !rest.contains(" ");
    }
    if (Cmd.BROADCAST.equals(cmd)) {
      return !rest.isBlank();
    }
    if (Cmd.LIST.equals(cmd) || Cmd.HELP.equals(cmd) || Cmd.EXIT.equals(cmd)) {
      return rest.isBlank();
    }
    return false;
  }

  private static void printHelpMenu(){
    Logger.info()
      .println("== HELP (Command Menu) ==")
      .println("[HELP] /help")
      .println("1. /register [name]")
      .println("2. /list")
      .println("3. /send [name] [message]")
      .println("4. /broadcast [message]")
      .println("[EXIT] /exit")
      .println();
  }

}
