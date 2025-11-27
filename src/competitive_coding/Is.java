package competitive_coding;

import java.util.HashMap;
import java.util.Map;

public class Is {
  public static boolean stobogrammatic(int num){
    return Stobogammatic.check(num);
  }

  public static boolean binaryPalindrome(int num){
    return BinaryPalindrome.checkWithBinOps(num);
  }
}

class BinaryPalindrome {
  public static boolean checkWithBinOps(int x){
    int reversed = 0;
    int original = x;
    while (x > 0){
      reversed <<= 1; // Left shift by 1
      reversed |= (x & 1); // Add the least significant bit of 'x' to 'reversed'
      x >>= 1; // Right shift by 1
    }
    return reversed == original;
  }

  public static boolean checkWithStrOps(int x){
    String bin = Integer.toBinaryString(x);
    String rev = new StringBuilder(bin).reverse().toString();
    return bin.equals(rev);
  }
}

class Stobogammatic {
  private final static Map<Character, Character> _dictionary;

  static {
    _dictionary = new HashMap<>();
    _dictionary.put('0', '0');
    _dictionary.put('1', '1');
    _dictionary.put('6', '9');
    _dictionary.put('8', '8');
    _dictionary.put('9', '6');
  }

  public static boolean check(int n){
    String num = String.valueOf(n);
    int left = 0, right = num.length() - 1;
    while (left < right){
      char leftChar = num.charAt(left);
      char rightChar = num.charAt(right);
      if (!_dictionary.containsKey(leftChar) || _dictionary.get(leftChar) != rightChar){
        return false;
      }
      left++; right--;
    }
    return true;
  }
}
