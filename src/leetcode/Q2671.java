package leetcode;

public class Q2671 {
  static class FrequencyTracker {
    private final int[] count;
    private final int[] frequency;

    public FrequencyTracker() {
      this.count = new int[(int) 1e5 + 1];
      this.frequency = new int[(int) 1e5 + 1];
    }

    public void add(int number) {
      this.frequency[this.count[number]]--;
      this.count[number]++;
      this.frequency[this.count[number]]++;

    }

    public void deleteOne(int number) {
      this.frequency[this.count[number]]--;
      this.count[number]--;
      this.frequency[this.count[number]]++;
    }

    public boolean hasFrequency(int frequency) {
      return this.frequency[frequency] != 0;
    }
  }
}
