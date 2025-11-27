package leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q355 {
  static class Twitter {

    static class User {
      public int id;
      public Set<Integer> following;
      public Set<Integer> tweets;

      public User(int id){
        this.id = id;
        this.following = new HashSet<>();
        this.tweets = new HashSet<>();
      }
    }

    public Map<Integer, User> data;
    public Map<Integer, Integer> tweets;
    public int tweetCount;

    public Twitter() {
      data = new HashMap<>();
      tweets = new HashMap<>();
      tweetCount = 0;
    }

    private User getOrCreateUser(int userId){
      User user = data.get(userId);
      if (user == null){
        user = new User(userId);
        data.put(userId, user);
      }
      return user;
    }

    public void postTweet(int userId, int tweetId) {
      User user = getOrCreateUser(userId);
      user.tweets.add(tweetId);
      tweets.put(tweetId, tweetCount++);
    }

    public List<Integer> getNewsFeed(int userId) {
      User user = getOrCreateUser(userId);
      List<Integer> totalFeed = new ArrayList<>(user.tweets);
      for (Integer followeeId: user.following){
        User followee = getOrCreateUser(followeeId);
        totalFeed.addAll(followee.tweets);
      }

      totalFeed.sort((a, b)->{
         return tweets.get(b) - tweets.get(a);
      });

      return totalFeed.stream().limit(10).toList();
    }

    public void follow(int followerId, int followeeId) {
     User follower = getOrCreateUser(followerId);
     follower.following.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
      User follower = getOrCreateUser(followerId);
      follower.following.remove(followeeId);
    }
  }

}
