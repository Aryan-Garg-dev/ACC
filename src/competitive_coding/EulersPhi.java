package competitive_coding;

public class EulersPhi {
  public static int phi(int n){
    int result = n;

    for (int i = 2; n * n < i; i++){
      if (n % i == 0){
        result -= result / i;
        while (n % i == 0) n /= i;
      }
    }

    if (n > 1) result -= result / n;

    return result;
  }
}
