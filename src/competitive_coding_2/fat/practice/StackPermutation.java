package competitive_coding_2.fat.practice;

import utility.Console;
import utility.Input;
import utility.Pair;

import java.util.*;

public class StackPermutation {
  public static boolean isStackPermutation(int[] original, int[] target){
    Stack<Integer> stack = new Stack<>();
    int i = 0;
    for (int num: original) {
      stack.push(num);
      while (!stack.isEmpty() && stack.peek() == target[i]) {
        stack.pop();
        i++;
      }
    }
    return stack.isEmpty();
  }

  public final static Input input = new Input();
  public static void main(String[] args) {
    int size = input.prompt("Enter size: ").readInt();
    Console.debug().println(
      isStackPermutation(
        input.prompt("Enter original sequence: ").readIntArray(size),
        input.prompt("Enter target sequence: ").readIntArray(size)
      )
    );
  }
}
