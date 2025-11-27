package competitive_coding;

import java.util.Arrays;

public class MajorityElement {
  private int[] _array;

  private MajorityElement(int[] array){
    this._array = array;
  }

  public static MajorityElement of(int[] array){
    return new MajorityElement(array);
  }

  public int getBySorting(){
    var clone = _array.clone();
    Arrays.sort(clone);
    int count = 1, current = clone[0], n = clone.length;
    for (int i = 1; i < n; i++){
      if (clone[i] == current){
        count++;
      } else {
        if (count > n/2) return current;
        count = 1;
        current = clone[i];
      }
    }
    return -1;
  }

  public int getByBoyerMoore(){
    int count = 0, n = _array.length, candidate = _array[0];

    for (int num : _array) {
      if (count == 0) {
        candidate = num;
        count = 1;
      } else {
        if (candidate == num) count++;
        else count = count - 1;
      }
    }

    count = 0;
    for (int num: _array){
      if (num == candidate) count++;
    }

    if (count > n/2) return candidate;
    return -1;
  }
}

