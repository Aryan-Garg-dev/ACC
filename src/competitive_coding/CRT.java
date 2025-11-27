package competitive_coding;

import java.util.stream.IntStream;

public class CRT {
  private static int _getBruteForce(int[] num, int[] rem, int size){
    int x = 1;
    while (true){
      int j;
      for (j = 0; j < size; j++){
        if (x % num[j] != rem[j]) break;
      }
      if (j == size) return x;
      x++;
    }
  }

  private static int _getOptimized(int[] num, int[] rem, int size){
    int x = 0;
    int p = IntStream.of(num).reduce(1, (acc, i) -> acc * i);
    for (int i = 0; i < size; i++) {
      int M = p / num[i]; // M/m1
      int M_1 = 0; // modulo inverse
      for (int j = 0; j < num[i]; j++) {
        if ((M * j) % num[i] == 1) {
          M_1 = j;
          break;
        }
      }
      x = x + rem[i] * M * M_1;
    }
    return x % p;
  }

  public static int evaluate(int[] num, int[] rem, int size){
    return _getOptimized(num, rem, size);
  }
}
