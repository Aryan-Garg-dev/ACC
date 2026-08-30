package competitive_coding_2.cat_1;

import java.util.Stack;

public class MinStack {
  Stack<Integer> stack = new Stack<>();
  Stack<Integer> minStack = new Stack<>();

  void push(int num){
    stack.push(num);
    if (!minStack.isEmpty())
       num = Math.min(minStack.peek(), num);
    minStack.push(num);
  }

  void pop(){
    if (stack.isEmpty()) return;
    stack.pop();
    minStack.pop();
  }

  int top(){
    return stack.peek();
  }

  int getMin(){
    return minStack.peek();
  }
}
