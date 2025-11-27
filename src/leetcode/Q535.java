package leetcode;

import utility.Console;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Q535 {
  static class Codec {
    private static final Map<String, String> data = new HashMap<>();

    public String encode(String longUrl) {
//      String id = UUID.randomUUID().toString();
      String id = String.valueOf((int) Math.floor(Math.random() * Math.pow(10, 6)));
      data.put(id, longUrl);
      return "https://garg.com/" + id;
    }

    public String decode(String shortUrl) {
      String id = shortUrl.substring(17);
      return data.get(id);
    }
  }

  public static void main(String[] args) {
    Codec tinyURL = new Codec();
    String encoded = tinyURL.encode("https://leetcode.com/problems/design-tinyurl");
    Console.log().println(encoded);
    Console.log().println(tinyURL.decode(encoded));
  }

}
