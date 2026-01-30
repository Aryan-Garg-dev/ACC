package utility.events;

import java.util.HashMap;
import java.util.Map;

public class EventEmitter {
  Map<String, EventListener> listeners = new HashMap<>();
  public void emit(String event, Object...args){
    listeners.get(event).onEvent(args);
  }
  public void on(String event, EventListener listener){
    listeners.put(event, listener);
  }
}
