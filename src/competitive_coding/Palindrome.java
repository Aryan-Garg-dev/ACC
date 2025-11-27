package competitive_coding;

import utility.Console;

class Manacher {
  private static char[] transformString(String s){
    char[] t = new char[s.length() * 2 + 1];
    for (int i = 0; i < t.length; i++){
      t[i] = (i % 2 == 0) ? '#': s.charAt(i / 2);
    }
    return t;
  }

  public static String longestPalindrome(String s){
    char[] t = transformString(s);

    int n = t.length;
    int[] P = new int[n];
    int C = 0, R = 0;

    for (int i = 0; i < n; i++){
      int mirror = 2 * C - i;

      if (i < R) P[i] = Math.min(P[mirror], R - i);

      while (
          i - P[i] - 1 >= 0 &&
          i + P[i] + 1 < n &&
          t[i - P[i] - 1] == t[i + P[i] + 1]
      ) {
        P[i]++;
      }

      if (i + P[i] > R){
        C = i; R = i + P[i];
      }
    }

    int maxLen = 0;
    int center = 0;
    for (int i = 0; i < n; i++){
      if (P[i] > maxLen){
        maxLen = P[i];
        center = i;
      }
    }

    int start = (center - maxLen) / 2;
    return s.substring(start, start + maxLen);
  }
}

public class Palindrome {
  private String _str = null;
  private int[] _freq = null;

  private Palindrome(String str){
    this._str = str;
    this._freq = new int[26];
    countFreq();
  }

  private void countFreq(){
    for (int i = 0; i < this._str.length(); i++) _freq[this._str.charAt(i) - 'a']++;
  }

  public static Palindrome of(String str){
    return new Palindrome(str);
  }

  public boolean isPossible(){
    int odd = 0;
    for (int freq: this._freq){
      if (freq % 2 == 1) odd++;
    }

    int strLen = this._str.length();
    if (strLen % 2 == 0 && odd > 0) return false;
    if (strLen % 2 == 1 && odd != 1) return false;
    return true;
  }

  private String findOddAndRemoveItsFreq(){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++){
      if (_freq[i] % 2 != 0){
        sb.repeat((char) (i + 'a'), _freq[i]);
        _freq[i] = 0;
        return sb.toString();
      }
    }
    return "";
  }

  public String first(){
    int len = this._str.length();
    if (!isPossible()) return null;
    String oddStr = "";
    StringBuilder front = new StringBuilder();

    if (len % 2 == 1) oddStr = findOddAndRemoveItsFreq();
    for (int i = 0; i < 26; i++){
      if (this._freq[i] != 0){
        char ch = (char) (i + 'a');
        front.repeat(ch, this._freq[i] / 2);
      }
    }

    return front + oddStr + front.reverse();
  }

  public static String getLongest(String string){
    return Manacher.longestPalindrome(string);
  }
}
