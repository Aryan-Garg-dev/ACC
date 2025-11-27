package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Q1396 {
  static class UndergroundSystem {
    private static class Source {
      String source;
      int departure;

      public Source(String source, int departure){
        this.source = source;
        this.departure = departure;
      }
    }

    private final Map<Integer, Source> checkInData;
    private final Map<String, double[]> travelData;

    public UndergroundSystem() {
      checkInData = new HashMap<>();
      travelData = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
      checkInData.put(id, new Source(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
      Source j = checkInData.get(id);
      if (j == null) return;
      String stationId = j.source + '-' + stationName;
      int duration = t - j.departure;
      travelData.compute(stationId, (k, v)->{
        if (v == null) return new double[]{ duration, 1 };
        else return new double[]{ (v[0] * v[1] + duration) / (v[1] + 1), v[1] + 1 };
      });
    }

    public double getAverageTime(String startStation, String endStation) {
      String stationId = startStation + '-' + endStation;
      return travelData.get(stationId)[0];
    }
  }
}
