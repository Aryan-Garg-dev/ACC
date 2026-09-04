package network_sockets.chat;

import utility.logger.Logger;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.*;

public class ChatServer implements Closeable {
  private final ConcurrentHashMap<String, Connection> activeConnections;
//  private  CopyOnWriteArrayList<Object> messageFilters;
//  private final ConcurrentLinkedQueue<String> logQueue


  private final ServerSocket server;
  private final ExecutorService pool;

  public ChatServer(int port) throws IOException {
    server = new ServerSocket(port);
    Logger.info().println("Started server on PORT: " + port);
    pool = Executors.newVirtualThreadPerTaskExecutor();
    activeConnections = new ConcurrentHashMap<>();
  }

  public void initConnections() throws IOException {
//    logger.start();

    while (!server.isClosed()) {
      var clientSocket = server.accept();

      pool.execute(
        new Connection(
          clientSocket,
          new Connection.EventHandlers(
            this::handleMessage,
            this::handleConnectionClose
          )
        )
      );
    }
  }

  public void handleMessage(String message, Connection connection){
    Logger.debug().printf("[DEBUG] %s :> %s\n", connection, message);
    if (message == null || message.isBlank() || !message.startsWith("/")) {
      connection.send("Invalid command. Use /help.");
      return;
    }

    String[] head = message.trim().split(" ", 2); // ["/send", "bob hello there"]
    String cmd = head[0].substring(1).toLowerCase();
    String rest = head.length > 1 ? head[1] : "";

    if (Cmd.REGISTER.equals(cmd)) {
      String[] _args = rest.split(" ", 2);
      if (_args.length != 1 || _args[0].isBlank()) { connection.send("Usage: /register [name]"); return; }
      String name = _args[0];
      Connection existing = activeConnections.putIfAbsent(name, connection);
      if (existing != null) {
        connection.send(String.format("Name '%s' is already taken.", name));
        return;
      }
      connection.setName(name);
      connection.send(String.format("REGD %s", connection));
    }

    if (Cmd.SEND.equals(cmd)) {
      String[] _args = rest.split(" ", 2); // ["bob", "hello there friend"]
      if (_args.length != 2) { connection.send("Usage: /send [name] [message]"); return; }
      String name = _args[0];
      if (!activeConnections.containsKey(name)){
        connection.send(String.format("Client with %s does not exist.", name));
        return;
      }
      String msg = _args[1];
      var targetConn = activeConnections.get(name);
      targetConn.send(String.format("%s :> %s", connection, msg));
    }

    if (Cmd.BROADCAST.equals(cmd)) {
      if (rest.isBlank()) { connection.send("Usage: /broadcast [message]"); return; }
      String msg = rest;
      activeConnections.values().forEach(otherConnection -> {
        if (!connection.equals(otherConnection))
          otherConnection.send(String.format("%s :> %s", connection, msg));
      });

      if (Cmd.LIST.equals(cmd)) {
        String names = String.join(", ", activeConnections.keySet());
        connection.send(names.isEmpty() ? "No registered users." : "Registered: " + names);
      }
    }

    if (Cmd.EXIT.equals(cmd)) connection.close();
  }

  public void handleConnectionClose(Connection connection){
    String name = connection.getName();
    if (name != null) activeConnections.remove(name);
    else {
      for (var entry: activeConnections.entrySet()){
        if (entry.getValue() == connection){
          activeConnections.remove(entry.getKey());
        }
      }
    }
  }

  @Override
  public void close(){
    try {
      activeConnections.values().forEach(Connection::close);
      pool.shutdown();
      server.close();
    } catch (Exception e){
      Logger.error().println(e.getMessage());
    }
  }

  public static void main(String[] args) {
    try (
      ChatServer server = new ChatServer(5000)
    ){
      server.initConnections();
    } catch (IOException e){
      Logger.error().println(e.getMessage());
    }
  }
}
