package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Q1797 {
  static class AuthenticationManager {
    private final int ttl;
    private final Map<String, Integer> tokens;

    public AuthenticationManager(int timeToLive) {
      this.ttl = timeToLive;
      this.tokens = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {
      this.tokens.put(tokenId, currentTime);
    }

    public void renew(String tokenId, int currentTime) {
      Integer tokenIssueTime = this.tokens.get(tokenId);
      if (tokenIssueTime == null) return;
      if (currentTime >= tokenIssueTime + ttl) return;
      this.tokens.put(tokenId, currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {
      int count = 0;
      for (int issueTime: tokens.values()){
        if (currentTime < issueTime + ttl) count++;
      }
      return count;
    }
  }
}
