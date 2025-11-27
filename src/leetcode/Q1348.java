package leetcode;

import utility.Console;

import java.util.*;

public class Q1348 {
  static class TweetCounts {
    private final Map<String, List<Integer>> tweets;

    public TweetCounts() {
      tweets = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
      tweets.putIfAbsent(tweetName, new ArrayList<>());
      List<Integer> tweetRecord = tweets.get(tweetName);

      int index = Collections.binarySearch(tweetRecord, time);
      if (index < 0) index = -index - 1;
      tweetRecord.add(index, time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
      int chunkSize = switch (freq){
        case "minute" -> 60;
        case "hour" -> 3600;
        case "day" -> 86400;
        default -> throw new IllegalArgumentException("Invalid frequency: " + freq);
      };

      List<Integer> tweetRecord = tweets.get(tweetName);
      if (tweetRecord == null) return Collections.emptyList();

      int start = Collections.binarySearch(tweetRecord, startTime);
      if (start < 0) start = -start - 1;

      int n = (endTime - startTime) / chunkSize + 1;
      List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
      for (int i = start; i < tweetRecord.size() && tweetRecord.get(i) <= endTime; i += 1){
        int index = (tweetRecord.get(i) - startTime) / chunkSize;
        result.set(index, result.get(index) + 1);
      }

      return result;
    }
  }

  public static void main(String[] args) {
    TweetCounts twitter = new TweetCounts();

    twitter.recordTweet("tweet3", 0);
    twitter.recordTweet("tweet3", 60);
    twitter.recordTweet("tweet3", 10);

    Console.log()
      .println(twitter.getTweetCountsPerFrequency("minute", "tweet3", 0, 59))
      .println(twitter.getTweetCountsPerFrequency("minute", "tweet3", 0, 60));

    twitter.recordTweet("tweet3", 120);

    Console.log()
      .println(twitter.getTweetCountsPerFrequency("hour", "tweet3", 0, 210));
  }
}
