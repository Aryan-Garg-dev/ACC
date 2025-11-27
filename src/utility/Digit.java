package utility;

public class Digit {
  public static int count(int number){
    if (number == 0) return 0;
    return (int) Math.floor(Math.log10(Math.abs(number))) + 1;
  }

  public static int sum(int number){
    number = Math.abs(number);
    int sum = 0;
    while (number != 0){
      sum += number % 10;
      number /= 10;
    }
    return sum;
  }
}
