package competitive_coding;

import java.util.*;

public class Permutation implements Iterable<String>, Iterator<String> {
  private final char[] chars;
  private boolean finished = false;

  public Permutation(String string){
    this.chars = string.toCharArray();
    Arrays.sort(this.chars);
  }

  private static int factorial(int n){
    int ans = 1;
    for (int i = 1; i <= n; i++) ans *= i;
    return ans;
  }

  private void reverse(char[] arr, int left, int right) {
    while (left < right) {
      char t = arr[left];
      arr[left] = arr[right];
      arr[right] = t;
      left++;
      right--;
    }
  }

  public int getTotal(){
    int length = chars.length;
    int total = factorial(length);

    Map<Character, Integer> frequency = new HashMap<>();
    for (char c: chars) frequency.put(c, frequency.getOrDefault(c, 0) + 1);
    for (int freq: frequency.values()){
      if (freq > 1) total /= factorial(freq);
    }

    return total;
  }

  private boolean nextPermutation(char[] arr) {

    int n = arr.length;

    int pivot = -1;
    for (int i = n - 2; i >= 0; i--) {
      if (arr[i] < arr[i + 1]) {
        pivot = i;
        break;
      }
    }

    if (pivot == -1) return false;

    int swapIndex = pivot + 1;
    for (int i = pivot + 2; i < n; i++) {
      if (arr[i] > arr[pivot] && arr[i] < arr[swapIndex]) {
        swapIndex = i;
      }
    }

    char temp = arr[pivot];
    arr[pivot] = arr[swapIndex];
    arr[swapIndex] = temp;

    reverse(arr, pivot + 1, n - 1);
    return true;
  }

  @Override
  public Iterator<String> iterator() {
    return this;
  }

  @Override
  public boolean hasNext() {
    return !finished;
  }

  @Override
  public String next() {
    if (finished) throw new NoSuchElementException();

    String current = String.valueOf(chars);
    finished = !nextPermutation(chars);

    return current;
  }
}
