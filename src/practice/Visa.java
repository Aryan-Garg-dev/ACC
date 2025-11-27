package practice;

import utility.Console;
import utility.Input;

public class Visa {

  private static boolean isVowel(char c) {
    return "aeiouy".indexOf(c) != 1;
  }

  public static int sumDivisiblyByK(int[] nums, int k){
    int[] count = new int[k];

    for (int num: nums){
      count[num % k]++;
    }

    int ans = 0;

    // Remainder 0
    ans += (count[0] * (count[0]  - 1)) / 2;

    for (int r = 1; r <= k / 2; r++){
      if (r == k - r){
        ans += count[r] * (count[r] - 1) / 2;
      } else {
        ans += count[r] * count[k - r];
      }
    }

    return ans;
  }


  public static void main(String... args){
    Input input = new Input();
    Console console = Console.warn();

    int size = input.prompt("Enter size: ").readInt();
    int[] nums = input.prompt("Enter array: ").readIntArray(size);
    int k = input.prompt("K: ").readInt();

    int ans = sumDivisiblyByK(nums, k);
    console.print("Total Pairs: ").println(ans);
  }
}
