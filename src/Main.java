import utility.Input;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Main {
  public static int a, b;

  public static void swap(){
    a = a + b;
    b = a - b;
    a = a - b;
  }

  public static void main(String[] args) {
    Input input = new Input();
    a = input.prompt("Enter a: ").readInt();
    b = input.prompt("Enter b: ").readInt();
    System.out.println(Arrays.asList(a, b));
    swap();
    System.out.println(Arrays.asList(a, b));
  }
}