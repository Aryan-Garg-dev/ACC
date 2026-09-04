package leetcode;

import utility.logger.Logger;

import java.util.PriorityQueue;

public class Q1845 {
  static class SeatManager {
    private final PriorityQueue<Integer> pq; // seats that were once used and freed.
    private int nextSeat; // new unused seat
    private int totalSeats;

    public SeatManager(int n) {
      pq = new PriorityQueue<>();
      nextSeat = 1;
      totalSeats = n;
    }

    public int reserve() {
      if (nextSeat <= totalSeats && pq.isEmpty()) return nextSeat++;
      return pq.poll();
    }

    public void unreserve(int seatNumber) {
      pq.offer(seatNumber);
    }
  }

  public static void main(String[] args) {
    SeatManager sm = new SeatManager(5);
    Logger.log().println(sm.reserve());
    sm.unreserve(1);
    Logger.log().println(sm.reserve());
    Logger.log().println(sm.reserve());
    sm.unreserve(2);
    Logger.log().println(sm.reserve());
    sm.unreserve(1);
    Logger.log().println(sm.reserve());
    sm.unreserve(2);
    Logger.log().println(sm.reserve());
  }
}
