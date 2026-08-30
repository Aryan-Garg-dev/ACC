package competitive_coding_2.cat_1;

import java.util.Stack;

public class StockSpanProblem {
 static int[] calculateSpan(int[] price){
   int n = price.length;
   int[] span = new int[n];
   Stack<Integer> stack = new Stack<>();

   span[0] = 1;
   stack.push(0);

   for (int i = 1; i < n; i++){
     while (!stack.isEmpty() && price[i] >= price[stack.peek()]) stack.pop();
     span[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
     stack.push(i);
   }

   return span;
 }
}
