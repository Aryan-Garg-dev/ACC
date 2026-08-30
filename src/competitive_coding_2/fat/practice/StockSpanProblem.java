package competitive_coding_2.fat.practice;

import java.util.*;

public class StockSpanProblem {
  static int[] calculateSpan(int[] prices){
    int n = prices.length;
    int[] span = new int[n];
    Stack<Integer> stack = new Stack<>();

    span[0] = 1;
    stack.push(0);

    for (int i = 1; i < prices.length; i++){
      while (!stack.isEmpty() && prices[i] > prices[stack.peek()]) stack.pop();
      span[i] = stack.isEmpty() ? i + 1 : (i - stack.peek());
      stack.push(i);
    }

    return span;
  }
}
