package competitive_coding;

public class Karatsuba {
 public static long multiply(long x, long y){
   if (x < 10 || y < 10) return x * y;
   int n = Math.max(Long.toString(x).length(), Long.toString(y).length());
   int half = n / 2;

   long multiplier = (long) Math.pow(10, half);
   long a = x / multiplier;
   long b = x % multiplier;
   long c = y / multiplier;
   long d = y % multiplier;

   long ac = multiply(a, c);
   long bd = multiply(b, d);
   long adbc = multiply(a + b, c + d) - ac - bd;

   return (long) (ac * Math.pow(multiplier, 2) + adbc * multiplier + bd);
 }
}
