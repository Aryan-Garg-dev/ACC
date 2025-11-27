package utility;

import java.util.Arrays;
import java.util.stream.IntStream;

public final class IntArray {
  public static long sum(int[] arr){
    return Arrays.stream(arr).summaryStatistics().getSum();
  }

  public static double mean(int[] arr){
    return Arrays.stream(arr).summaryStatistics().getAverage();
  }

  public static int min(int[] arr){
    return Arrays.stream(arr).summaryStatistics().getMin();
  }

  public static int max(int[] arr){
    return Arrays.stream(arr).summaryStatistics().getMax();
  }

  public static int count(int[] arr, int num){
    int count = 0;
    for (int n: arr){
      if (num == n) count++;
    }
    return count;
  }

  public static boolean isSorted(int[] arr){
    if (arr.length == 0) return true;
    for (int i = 1; i < arr.length; i++){
      if (arr[i] < arr[i-1]) return false;
    }
    return true;
  }

  public static int[] remove(int[] arr, int indexOrElement, boolean isIndex){
    if (isIndex){
      if (indexOrElement >= arr.length) return arr;
      return IntStream
        .range(0, arr.length)
        .filter(i -> i != indexOrElement)
        .map(i -> arr[i])
        .toArray();
    } else {
      return Arrays
        .stream(arr)
        .filter(num -> num != indexOrElement)
        .toArray();
    }
  }

  public static int[] reverse(int[] arr){
    return IntStream.range(0, arr.length)
      .map(i -> arr[arr.length - 1 - i])
      .toArray();
  }

  public static Boolean isPalindrome(int[] arr){
    return IntStream
      .range(0, arr.length / 2)
      .noneMatch(i -> arr[i] != arr[arr.length - i - 1]);
  }

  public static int[] mergeSorted(int[] first, int[] second){
    return IntStream
      .concat(Arrays.stream(first), Arrays.stream(second))
      .sorted()
      .toArray();
  }

  public static int diagonalSum2d(int[][] matrix){
    return IntStream
      .range(0, matrix.length)
      .map(i -> matrix[i][i] + matrix[i][matrix.length-1-i])
      .sum();
  }
}