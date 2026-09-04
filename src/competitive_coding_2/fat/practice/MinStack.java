package competitive_coding_2.fat.practice;

import utility.logger.Logger;
import utility.Input;

import java.util.*;

public class MinStack {
  public static final int INVALID = Integer.MIN_VALUE;

  Stack<Integer> general;
  Stack<Integer> minimum;
  MinStack(){
    general = new Stack<>();
    minimum = new Stack<>();
  }

  public void push(int value){
    general.push(value);
    if (minimum.isEmpty() || value < minimum.peek()) {
      minimum.push(value);
    }
  }

  public int pop(){
    if (general.isEmpty()) return INVALID;
    int removed = general.pop();
    if (removed == minimum.peek()) minimum.pop();
    return removed;
  }

  public int top(){
    if (general.isEmpty()) return INVALID;
    return general.peek();
  }

  public int getMin(){
    if (minimum.isEmpty()) return INVALID;
    return minimum.peek();
  }

  private static final Input input = new Input();
  public static void main(String[] args) {
    MinStack minStack = new MinStack();
    while (true){
      int operation = input.prompt("""
        ==================
          Push: 1
          Pop:  2
          Top:  3
          Min:  4
          End:  DEFAULT 
        ==================
        """).readInt();
      boolean exit = false;
      switch (operation){
        case 1: minStack.push(input.prompt("Push: ").readInt()); break;
        case 2: Logger.debug().print("POP: ").println(minStack.pop()); break;
        case 3: Logger.debug().print("TOP: ").println(minStack.top()); break;
        case 4: Logger.debug().print("MIN: ").println(minStack.getMin()); break;
        default: exit = true;
      }
      if (exit) break;
    }
  }
}
