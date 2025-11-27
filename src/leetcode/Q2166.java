package leetcode;

public class Q2166 {
  static class Bitset {
    private final boolean[] bits;
    private final int size;
    private int ones;
    private boolean flipped;

    public Bitset(int size) {
      this.size = size;
      this.bits = new boolean[size];
    }

    public void fix(int idx) {
      if (bits[idx] == flipped){ // (T, T -> F),  (F, F -> T)  (T, F or F, T -> No Change)
        bits[idx] = !bits[idx];
        ones++;
      }
    }

     public void unfix(int idx) {
      if (bits[idx] != flipped){ 
        bits[idx] = !bits[idx];
        ones--;
      }
    }

    public void flip() {
      flipped = !flipped;
      ones = size - ones;
    }

    public boolean all() {
      return ones == size;
    }

    public boolean one() {
      return ones > 0;
    }

    public int count() {
      return ones;
    }

    public String toString() {
     StringBuilder sb = new StringBuilder();
     for (boolean b: bits) sb.append(b ^ flipped ? '1' : '0');
     return sb.toString();
    }
  }
}
