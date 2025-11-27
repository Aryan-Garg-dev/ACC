package challenges;

import utility.Input;

class OddEven {
  public static boolean isEven(int num){
    return ((num & 1) == 0) ;
  }
}

public class Bitwise {
  private final static Input input = new Input();
  public static void main(String[] args) {
    int num = input.prompt("Enter a number: ").readInt();
    System.out.println(
      OddEven.isEven(num)
      ? "Even number"
      : "Odd number"
    );
  }
}
