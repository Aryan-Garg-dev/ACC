package leetcode;

import javax.xml.crypto.Data;
import java.util.*;

public class Q3508 {
  static class Router {
    private static class DataPacket {
      int source, destination, timestamp;
      public DataPacket(int source, int destination, int timestamp){
        this.source = source;
        this.destination = destination;
        this.timestamp = timestamp;
      }
      public int[] toArray(){
        return new int[]{ source, destination, timestamp };
      }

      public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof DataPacket p)) return false;
        return this.source == p.source
          && this.destination == p.destination
          && this.timestamp == p.timestamp;
      }

      public int hashCode(){
        return Objects.hash(source, destination, timestamp);
      }
    }

    private int memoryLimit;
    private Queue<DataPacket> queue;
    private Set<DataPacket> packetSet;
    // destination -> timestamp -> count
    private Map<Integer, TreeMap<Integer, Integer>> destinationMap;

    public Router(int memoryLimit) {
      this.memoryLimit = memoryLimit;
      this.queue = new ArrayDeque<>();
      this.packetSet = new HashSet<>();
      this.destinationMap = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
      DataPacket p = new DataPacket(source, destination, timestamp);
      if (packetSet.contains(p)) return false;
      if (queue.size() >= memoryLimit){
        DataPacket old = queue.poll();
        packetSet.remove(old);
        var countMap = destinationMap.get(old.destination);
        countMap.compute(old.timestamp, (k, v) -> v == null ? 0 : v - 1);
        if (countMap.get(old.timestamp) == 0) countMap.remove(old.timestamp);
      }

      queue.offer(p);
      packetSet.add(p);
      destinationMap
        .computeIfAbsent(destination, k -> new TreeMap<>())
        .put(timestamp, destinationMap.get(destination).getOrDefault(timestamp, 0) + 1);
      return true;
    }

    public int[] forwardPacket() {
      DataPacket p = queue.poll();
      if (p == null) return new int[]{};
      packetSet.remove(p);
      destinationMap.get(p.destination).compute(p.timestamp, (k, v) -> v == null ? 0 : v - 1);
      return p.toArray();
    }

    public int getCount(int destination, int startTime, int endTime) {
      var countMap = destinationMap.get(destination);
      if (countMap == null || countMap.isEmpty()) return 0;
      return countMap.subMap(startTime, true, endTime, true)
        .values().stream().mapToInt(Integer::intValue).sum();
    }
  }

  public static void main(String[] args) {


  }
}
