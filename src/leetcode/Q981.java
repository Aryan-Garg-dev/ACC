package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Q981 {
  static class TimeMap {
    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
      map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
      map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
      var timeMap = map.get(key);
      if (timeMap == null) return "";
      var entry = timeMap.floorEntry(timestamp);
      if (entry == null) return "";
      return entry.getValue();
    }
  }
}
