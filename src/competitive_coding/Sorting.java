package competitive_coding;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class NaturalOrder implements Comparator<String> {
  private static final Pattern pattern = Pattern.compile("(\\D*)(\\d*)");

  @Override
  public int compare(String s1, String s2){
    Matcher m1 = pattern.matcher(s1);
    Matcher m2 = pattern.matcher(s2);

    while (m1.find() && m2.find()){
      int textCompare = m1.group(1).compareTo(m2.group(1));
      if (textCompare != 0) return textCompare;

      String num1 = m1.group(2), num2 = m2.group(2);
      if (num1.isEmpty() && num2.isEmpty()) continue;

      int n1 = num1.isEmpty() ? 0 : Integer.parseInt(num1);
      int n2 = num2.isEmpty() ? 0 : Integer.parseInt(num2);
      if (n1 != n2) return n1 - n2;
    }

    return s1.compareTo(s2);
  }
}

class QuickSort { // Avg O(nlogn) Worst O(n^2)
  private static void _swapInt(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }


  private static int _partition(int[] nums, int low, int high){
    int pivot = nums[high];
    int i = low - 1;

    for (int j = low; j < high; j++){
      if (nums[j] < pivot){
        i++;
        _swapInt(nums, i, j);
      }
    }

    _swapInt(nums, i + 1, high);
    return i + 1;
  }

  public static void sort(int[] nums, int low, int high){
    if (low < high){
      int pivotIndex = _partition(nums, low, high);
      sort(nums, low, pivotIndex - 1);
      sort(nums, pivotIndex, nums.length);
    }
  }
}

class SelectionSort {
  private static void _swapInt(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void sort(int[] nums){
    int n = nums.length;
    for (int i = 0; i < n; i++){
      int minIdx = i;
      for (int j = i + 1; j < n; j++){
        if (nums[j] > nums[minIdx]) minIdx = j;
      }
      _swapInt(nums, i, minIdx);
    }
  }
}

public class Sorting {

  public static void naturalOrder(List<String> stringList){
    stringList.sort(new NaturalOrder());
  }

  public static void selection(int[] nums){
    SelectionSort.sort(nums);
  }

  public static void quick(int[] nums){
    QuickSort.sort(nums, 0, nums.length - 1);
  }
}

