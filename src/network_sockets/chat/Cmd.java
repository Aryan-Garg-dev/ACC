package network_sockets.chat;

public enum Cmd {
  EXIT("exit"),
  REGISTER("register"),
  SEND("send"),
  BROADCAST("broadcast"),
  LIST("list"),
  HELP("help");


  private final String value;
  Cmd(String value){
    this.value = value;
  }

  @Override
  public String toString(){
    return value;
  }

  public boolean equals(String string){
    return this.value.equals(string);
  }
}
