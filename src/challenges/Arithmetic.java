package challenges;

import utility.Input;

final class DigitUtils {
  static int count(int number){
    if (number == 0) return 0;
    return (int) Math.floor(Math.log10(Math.abs(number))) + 1;
  }

  static int sum(int number){
    number = Math.abs(number);
    int sum = 0;
    while (number != 0){
      sum += number % 10;
      number /= 10;
    }
    return sum;
  }

  static int reverse(int number){
    int reversed = 0;
    boolean isNeg = number < 0;
    number = Math.abs(number);

    while (number > 0){
      int digit = number % 10;
      reversed = reversed * 10 + digit;
      number  /= 10;
    }

    return isNeg ? -reversed : reversed;
  }
}

final class Number {
  static int gcd(int num1, int num2){
    while (num2 != 0){
      int temp = num2;
      num2 = num1 % num2;
      num1 = temp;
    }
    return num1;
  }

  static int lcm(int num1, int num2){
    return Math.abs(num1 * num2) / gcd(num1, num2);
  }

  static boolean isPrime(int number){
    if (number <= 1) return false;
    if (number <= 3) return true;
    if (number % 2 == 0 || number % 3 == 0) return false;

    for (int i = 5; i * i <= number; i += 6){
      if (number % i == 0 || number % (i + 2) == 0) return false;
    }
    return true;
  }
}

public class Arithmetic {
  private final static Input input = new Input();

  public static void digitOperations() {
    int number = input.prompt("Enter a number: ").readInt();
    System.out.printf(
      "Count: %d; Sum: %d; Reversed: %d;\n",
      DigitUtils.count(number),
      DigitUtils.sum(number),
      DigitUtils.reverse(number)
    );
  }

  public static void numberOperations() {
    int first = input.prompt("Enter first number: ").readInt();
    int second = input.prompt("Enter second number: ").readInt();
    System.out.printf("LCM: %d;\n", Number.lcm(first, second));
    System.out.printf("GCD: %d;\n", Number.gcd(first, second));
  }

  public static void checkPrime(){
    int number = input.prompt("Enter a number: ").readInt();
    System.out.println(Number.isPrime(number) ? "Prime" : "Not a prime");
  }

  public static void main(String[] args) {
    digitOperations();
//    numberOperations();
//    checkPrime();
  }
}