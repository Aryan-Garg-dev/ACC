package challenges;

import utility.Console;
import utility.Input;
import utility.IntArray;

import java.util.Arrays;


public class Array {
  public final static Input input = new Input();

  public static void arrStatsAndOps(){
    int[] arr = input.prompt("Enter arr of size 5: ").readIntArray(5);
    Console.log()
        .printf("Sum: %d", IntArray.sum(arr)).println()
        .printf("Mean: %.2f", IntArray.mean(arr)).println()
        .printf("Max: %d", IntArray.max(arr)).println()
        .printf("Min: %d", IntArray.min(arr)).println()
        .printf("Is Sorted: %b", IntArray.isSorted(arr)).println()
        .printf("Is palindrome: %b", IntArray.isPalindrome(arr)).println()
        .print("Reversed: ").println(Arrays.toString(IntArray.reverse(arr)));
    int numToCount = input.prompt("Enter num to count: ").readInt();
    Console.log()
      .printf("Count of %d: %d", numToCount, IntArray.count(arr, numToCount))
      .println();
    int indexToRemove = input.prompt("Index to remove: ").readInt();
    Console.log()
      .print("Filtered Array: ")
      .println(Arrays.toString(IntArray.remove(arr, indexToRemove, true)));
  }

  public static void mergeArrays(){
    int[] first = input.prompt("Enter sorted arr of size 5: ").readIntArray(5);
    int[] second = input.prompt("Enter another sorted arr of size 5: ").readIntArray(5);
    Console.log()
      .print("Merged: ")
      .println(Arrays.toString(IntArray.mergeSorted(first, second)));
  }

  public static void printDiagSum(){
    int[][] matrix = new int[3][3];
    Console.log().println("Input 3X3 matrix:");
    for (int i = 0; i < 3; i++){
      matrix[i] = input.readIntArray(3);
    }
    Console.log()
      .print("Sum of Diag Elements: ")
      .println(IntArray.diagonalSum2d(matrix));
  }

  public static void main(String[] args) {
//    arrStatsAndOps();
//    mergeArrays();
//    printDiagSum();
  }
}
