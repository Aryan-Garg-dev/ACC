package leetcode;

import utility.Console;
import utility.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Q1146 {
  static class SnapshotArray {
    private int snapId;
    private List<TreeMap<Integer, Integer>> data;

    public SnapshotArray(int length) {
      this.snapId = 0;
      this.data = new ArrayList<>(length);
      for (int i = 0; i < length; i++){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(this.snapId, 0);
        data.add(map);
      }
    }

    public void set(int index, int val) {
     this.data.get(index).put(this.snapId, val);
    }

    public int snap() {
      return snapId++;
    }

    public int get(int index, int snap_id) {
      return this.data.get(index).floorEntry(snap_id).getValue();
    }
  }

  public static void main(String[] args) {
    SnapshotArray array = new SnapshotArray(3);
    array.set(0, 5);
    array.snap();
    array.set(0, 6);
    Console.log().println(array.get(0, 0));
  }
}
