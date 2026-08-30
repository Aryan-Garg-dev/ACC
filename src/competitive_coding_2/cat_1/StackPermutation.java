package competitive_coding_2.cat_1;

import java.util.Stack;

public class StackPermutation {
  public static boolean isStackPermutation(int[] original, int[] target){
    Stack<Integer> stack = new Stack<>();

    int i = 0;
    for (int num: original){
      stack.push(num);
      while (!stack.isEmpty() && stack.peek() == target[i]){
        stack.pop();
        i++;
      }
    }

    return stack.isEmpty();
  }

  public static void main(String[] args) {
    System.out.println(
      isStackPermutation(
        new int[]{ 1, 2, 3 },
        new int[]{ 2, 1, 3 }
      )
    );
  }
}
