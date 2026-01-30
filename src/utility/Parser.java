package utility;

@FunctionalInterface
public interface Parser<T> {
  T parse(String input);
}
