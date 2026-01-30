package utility.events;

@FunctionalInterface
public interface EventListener {
  void onEvent(Object...args);
}
