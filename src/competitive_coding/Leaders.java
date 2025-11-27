package competitive_coding;

import java.util.ArrayList;
import java.util.List;

public class Leaders {
  private final int[] _array;

  private Leaders(int[] array){
    this._array = array;
  }

  public static Leaders in(int[] array){
    return new Leaders(array);
  }

  public List<Integer> get(){
    List<Integer> leaders = new ArrayList<>();
    int n = this._array.length, max = this._array[n - 1];
    leaders.add(max);
    for (int i = n - 2; i >= 0; i--){
      if (this._array[i] > max){
        max = _array[i];
        leaders.add(this._array[i]);
      }
    }

    return leaders;
  }

  @Override
  public String toString() {
    return this.get().toString();
  }
}
