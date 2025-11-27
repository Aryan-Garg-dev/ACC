package competitive_coding;

public class Rotate {
  public static int[] left(int[] arr, int k){
    int n = arr.length;
    k %= n;

    int[] result = new int[n];
    for (int i = 0; i < n; i++){
      result[(i + k) % n] = arr[i];
    }

    return result;
  }

  public static void leftInPlace(int[] arr, int k){
    int n = arr.length;
    k %= n;
    reverse(arr, 0, n - 1);
    reverse(arr, 0, k - 1);
    reverse(arr, k, n - 1);
  }

  public static void reverse(int[] arr, int start, int end){
    while (start < end){
      int temp = arr[start];
      arr[start] = arr[end];
      arr[end] = temp;
      start++;
      end--;
    }
  }
}
