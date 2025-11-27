package leetcode;

import utility.Console;

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
    Console.log().println(sm.reserve());
    sm.unreserve(1);
    Console.log().println(sm.reserve());
    Console.log().println(sm.reserve());
    sm.unreserve(2);
    Console.log().println(sm.reserve());
    sm.unreserve(1);
    Console.log().println(sm.reserve());
    sm.unreserve(2);
    Console.log().println(sm.reserve());
  }
}
