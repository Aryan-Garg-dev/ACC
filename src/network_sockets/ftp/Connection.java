package network_sockets.ftp;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;

public class Connection {
  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;

  public Connection(Socket socket) {
    this.socket = socket;
    try {
      in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
      out = new DataOutputStream(socket.getOutputStream());
    } catch (IOException e){
      throw new RuntimeException(e);
    }
  }

  public void sendFile() {
    try {
      sendMenu();
      int index = getSelectedFileIndex();
      sendSelectedFile(index);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private void sendSelectedFile(int index) throws IOException {
    File[] fileList = getFileList();
    File selectedFile = fileList[index];
    List<String> fileLines = Files.readAllLines(selectedFile.toPath());
    String fileContent = String.join("\n", fileLines);
    out.writeUTF(fileContent);
  }

  private int getSelectedFileIndex() throws IOException, NumberFormatException {
    String input = in.readUTF();
    return Integer.parseInt(input) - 1;
  }

  private void sendMenu() throws IOException {
    StringBuilder menu = new StringBuilder("** Files **\n");
    File[] fileList = getFileList();
    out.writeUTF("" + fileList.length);
    for (int i = 0; i < fileList.length; i++){
      menu.append(String.format("%d - %s\n", i + 1, fileList[i].getName()));
    }
    out.writeUTF(menu.toString());
  }

  private static File[] getFileList(){
    return new File(FTPServer.FILES_PATH).listFiles();
  }


  public void close(){
    try {
      in.close();
      out.close();
      socket.close();
    } catch (Exception e){
      System.err.println(e.getMessage());
    }
  }

}
