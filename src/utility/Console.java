package utility;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Console {
  private static final String RESET = "\u001B[0m";
  private static final String DEBUG = "\u001B[34m"; // Blue
  private static final String INFO  = "\u001B[36m"; // Cyan
  private static final String WARN  = "\u001B[33m"; // Yellow
  private static final String ERROR = "\u001B[31m"; // Red

  private enum Level {
    LOG, WARN, ERROR, DEBUG, INFO
  }

  private Level currentLevel = Level.LOG;

  private Console() {};
  private Console(Level level){
    this.currentLevel = level;
  }

  public static Console log() {
    return new Console(Level.LOG);
  }

  public static Console error() {
    return new Console(Level.ERROR);
  }

  public static Console warn() {
    return new Console(Level.WARN);
  }

  public static Console info() {
    return new Console(Level.INFO);
  }

  public static Console debug() {
    return new Console(Level.DEBUG);
  }

  private String colorize(String message) {
    return switch (currentLevel) {
      case WARN -> WARN + message + RESET;
      case ERROR -> ERROR + message + RESET;
      case DEBUG -> DEBUG + message + RESET;
      case INFO -> INFO + message + RESET;
      default -> message;
    };
  }

  private static void deepArrayToStringRecursive(Object array, StringBuilder sb) {
    int length = Array.getLength(array);
    sb.append("[");
    for (int i = 0; i < length; i++) {
      Object elem = Array.get(array, i);
      if (elem == null) {
        sb.append("null");
      } else if (elem.getClass().isArray()) {
        deepArrayToStringRecursive(elem, sb);
      } else {
        sb.append(elem);
      }
      if (i < length - 1) sb.append(", ");
    }
    sb.append("]");
  }

  public static String stringify(Object obj) {
    if (obj == null) return "null";

    if (!obj.getClass().isArray()) return obj.toString();

    StringBuilder sb = new StringBuilder();
    deepArrayToStringRecursive(obj, sb);
    return sb.toString();
  }

  public Console print(Object message) {
    System.out.print(colorize(stringify(message)));
    return this;
  }

  public Console println(Object message) {
    System.out.println(colorize(stringify(message)));
    return this;
  }

  public Console println() {
    System.out.println();
    return this;
  }

  public Console printf(String format, Object... args) {
    String formatted = String.format(format, args);
    System.out.print(colorize(formatted));
    return this;
  }
}
