package network_sockets.chat;

import utility.Logger;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class Connection implements Runnable, Closeable {

  public record EventHandlers (
    BiConsumer<String, Connection> onMessage,
    Consumer<Connection> onClose
  ){}

  private final BufferedReader reader;
  private final PrintWriter writer;
  private final EventHandlers eventHandlers;

  public final String ip;
  public final int port;
  public final Socket socket;

  private String name;

  public Connection(
    Socket socket,
    EventHandlers eventHandlers
  ) throws IOException {
    this.socket = socket;
    ip = socket.getInetAddress().getHostAddress();
    port = socket.getPort();
    this.eventHandlers = eventHandlers;
    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    writer = new PrintWriter(socket.getOutputStream(), true);
    Logger.info().println(this);
  }

  public void readMessages() throws IOException {
    for (String msg; (msg = reader.readLine()) != null;){
      eventHandlers.onMessage.accept(msg, this);
      if (socket.isClosed()) return;
    }
    this.close();
  }

  public void send(String message){
    this.writer.println(message);
  }

  public PrintWriter getWriter(){
    return this.writer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void run() {
    try {
      this.readMessages();
    } catch (IOException e){
      Logger.error().println(e.getMessage());
      this.close();
    }
  }


  @Override
  public void close() {
    try {
      Logger.debug().println(this + " closed");
      eventHandlers.onClose.accept(this);
      reader.close();
      writer.close();
      socket.close();
    } catch (Exception e){
      Logger.error().println(e.getMessage());
    }
  }

  @Override
  public String toString(){
    if (name != null) return String.format("Connection(%s:%d, name=%s)", ip, port, name);
    return String.format("Connection(%s:%d)", ip, port);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Connection that = (Connection) o;
    return port == that.port && Objects.equals(ip, that.ip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ip, port);
  }
}
