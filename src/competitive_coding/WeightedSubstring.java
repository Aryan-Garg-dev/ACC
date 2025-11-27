package competitive_coding;

import java.util.HashSet;

public class WeightedSubstring {
  private static int[] parseWeights(String weights){
    int[] q = new int[26];
    for (int i = 0; i < 26; i++){
      q[i] = weights.charAt(i) - '0';
    }
    return q;
  }

  private static int _idx(char ch){
    return ch - 'a';
  }

  public static int getDistinctSubStrings(String string, String weights, int targetWeight){
    HashSet<String> set = new HashSet<>();
    int[] q = parseWeights(weights);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < string.length(); i++){
      int sum = 0;
      sb.setLength(0);
      for (int j = i; j < string.length(); j++){
        char ch = string.charAt(j);
        sum += q[_idx(ch)];
        sb.append(ch);
        if (sum <= targetWeight) set.add(sb.toString());
        else break;
      }
    }

    return set.size();
  }
}
