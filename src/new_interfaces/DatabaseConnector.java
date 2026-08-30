package new_interfaces;

public interface DatabaseConnector {
  default void connectToDatabase(){
    logConnection("Production");
  }

  default void connectToStaging(){
    logConnection("Staging");
  }

  private void logConnection(String environment){
    System.out.println("Attempting connection to: " +  environment);
  }

  static void printVersion(){
    System.out.println(getSystemPrefix() + " Version 2.0");
  }

  private static String getSystemPrefix(){
    return "[SYS-LOG]";
  }
}
