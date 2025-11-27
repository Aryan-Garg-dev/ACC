package leetcode;

import utility.Console;

import java.util.ArrayList;
import java.util.List;

public class Q1472 {
  static class BrowserHistory {
    private final List<String> history;
    private int end;
    private int idx;

    public BrowserHistory(String homepage) {
      history = new ArrayList<>();
      history.add(homepage);
      idx = 0;
      end = 0;
    }

    public void visit(String url) {
      if (history.size() > idx + 1) history.set(++idx, url);
      else {
        history.add(url);
        idx++;
      }
      end = idx;
    }

    public String back(int steps) {
      idx = idx - Math.min(steps, idx);
      return history.get(idx);
    }

    public String forward(int steps) {
      idx = idx + Math.min(steps, end - idx);
      return history.get(idx);
    }
  }

  public static void main(String[] args) {
    BrowserHistory history = new BrowserHistory("leetcode.com");
    history.visit("google.com");
    history.visit("facebook.com");
    history.visit("youtube.com");
    Console.log()
      .println(history.back(1))
      .println(history.back(1))
      .println(history.forward(1));
    history.visit("linkedin.com");
    Console.log()
      .println(history.forward(2))
      .println(history.back(2))
      .println(history.back(7));
  }
}
