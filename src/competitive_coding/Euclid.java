package competitive_coding;

public class Euclid {
  public static int gcd(int num1, int num2){
    // O(log(min(a, b)))
    if (num1 == 0) return num2;
    return gcd(num2 % num1, num1);
  }

  public static class Result {
    int x, y;
    Result(int x, int y){
      this.x = x;
      this.y = y;
    }
  }

  public static int gcdExtended(int a, int b, Result result){
    // ax + by = gcd(a, b)
    if (a == 0){
      result.x = 0;
      result.y = 1;
      return b;
    }
    Result tempResult = new Result(1, 1);
    int gcd = gcdExtended(b % a, a, tempResult);
    // ax + by = gcd
    // a = gcd - (b / a) * x
    result.x = tempResult.y - (b / a) * tempResult.x;
    result.y = tempResult.x;
    return gcd;
  }
}
