package competitive_coding;

import utility.Console;
import utility.Input;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  static Input input = new Input();

  public static void sievePractice(){
    int limit = input.prompt("Enter limit: ").readInt();
    List<Integer> primes = Sieve.getPrimeNumbers(limit);
    Console.log().println(primes);

    int start = input.prompt("Start: ").readInt();
    int end = input.prompt("End: ").readInt();
    primes = Sieve.getPrimeNumbers(start, end);
    Console.log().println(primes);

    var twins = Sieve.getTwinPrimes(input.prompt("Limit: ").readInt());
    Console.log().println(twins);

    Console.log()
      .println(
        Sieve
          .stream()
          .limit(input.prompt("Count: ").readInt())
          .collect(Collectors.toList())
      );
  }

  public static void eulersPhiPractice(){
    int n = input.prompt("Enter n: ").readInt();
    int phi = EulersPhi.phi(n);
    Console.log().println(phi);
  }

  public static void strobogrammaticPractice(){
    Console.log()
      .println(
        Is.stobogrammatic(input.prompt("Enter number: ").readInt())
      );
  }

  public static void binaryPalindromePractice(){
    Console.log()
      .println(
        Is.binaryPalindrome(input.prompt("Enter Number: ").readInt())
      );
  }

  public static void euclidPractice(){
    int num1 = input.prompt("Enter number 1: ").readInt();
    int num2 = input.prompt("Enter number 2: ").readInt();
    Euclid.Result r = new Euclid.Result(0, 0);
    Console.log()
      .println(Euclid.gcdExtended(num1, num2, r))
      .printf("x=%d, y=%d", r.x, r.y);
  }

  public static void chineseRemainderTheoremPractice(){
    var count = input.prompt("Enter size: ").readInt();
    var numbers = input.prompt("Enter numbers: ").readIntArray(count);
    var remainders = input.prompt("Enter remainders: ").readIntArray(count);
    var crt = CRT.evaluate(numbers, remainders, count);
    Console.log().println(crt);
  }

  public static void majorityElementPractice(){
    var size = input.prompt("Enter size: ").readInt();
    var numbers = input.prompt("Enter numbers: ").readIntArray(size);
    int majorityElement = MajorityElement.of(numbers).getBySorting();
    Console.log()
      .print("Majority Element by sorting: ")
      .println(majorityElement);
    majorityElement = MajorityElement.of(numbers).getBySorting();
    Console.log()
      .print("Majority Element by Boyer Moore: ")
      .println(majorityElement);
  }

  public static void longestSequencePractice(){
    var number = input.prompt("Enter number: ").readInt();
    var longestSequence = LongestSequence.of(1).in(number).get();
    Console.log()
      .print("Longest Sequence of 1: ")
      .println(longestSequence);
    var longestSequenceAfterFlip = LongestSequence
      .of(1)
      .in(number)
      .getAfterFlip();
    Console.log()
      .print("Longest Sequence of 1 After Flip: ")
      .println(longestSequenceAfterFlip);
  }

  public static void karatsubaPractice(){
    int x = input.prompt("Enter X: ").readInt();
    int y = input.prompt("Enter Y: ").readInt();
    Console.log()
      .printf("%d x %d = %d", x, y, Karatsuba.multiply(x, y))
      .println();
  }

  public static void leadersPractice(){
    int size = input.prompt("Enter size: ").readInt();
    int[] array = input.prompt("Enter numbers: ").readIntArray(size);
    Console.log().println(Leaders.in(array));
  }

  public static void palindromePractice(){
    String str = input.prompt("Enter string: ").readLine();
    Console.log().println(Palindrome.of(str).isPossible());
    Console.log()
      .print("First Palindrome from Chars: ")
      .println(Palindrome.of(str).first());
    Console.log()
      .print("Longest Palindrome In String: ")
      .println(Palindrome.getLongest(str));
  }

  public static void rotateArrayPractice(){
    int size = input.prompt("Enter size: ").readInt();
    int[] numbers = input.prompt("Enter number: ").readIntArray(size);
    Rotate.leftInPlace(numbers, 2);
    Console.log().print(numbers);
  }

  public static void sortingPractice(){
    List<String> stringList = input.prompt("Enter strings: ").readWordArray();
    Sorting.naturalOrder(stringList);
    Console.log().println(stringList);
  }

  public static void weightedSubstringPractice(){
    String string = input.prompt("String: ").readLine();
    String weights = input.prompt("Weights: ").readLine();
    int k = input.prompt("Target Weight: ").readInt();
    Console.log()
      .println(WeightedSubstring.getDistinctSubStrings(string, weights, k));
  }

  public static void moveHyphenPractice(){
    Console.log()
      .println(
        new Move('-').toFront(input.prompt("String: ").readLine())
      );
  }

  public static void permutationPractice(){
    Permutation perm = new Permutation(
      input.prompt("String: ").readLine()
    );
    Console.log()
      .print("Total possible permutations: ").println(perm.getTotal());
    for (String p: perm) Console.log().println(p);
  }

  public static void maneuveringPractice(){
    int rows = input.prompt("Enter no. of rows: ").readInt();
    int cols = input.prompt("Enter no. of cols: ").readInt();
    Console.log().println(Maneuver.getTotalPaths(rows, cols));
  }

  public static void combinationPractice(){
    Console.log()
      .println(Combination.get(
        input.readIntArray(input.prompt("Size: ").readInt()),
        input.prompt("k: ").readInt()
      ));
  }

  public static void schedulePractice(){
    int size = input.prompt("Count: ").readInt();
    int[] start = input.prompt("Start: ").readIntArray(size);
    int[] finish = input.prompt("finish: ").readIntArray(size);
    Schedule.selectActivities(Schedule.list(start, finish));
  }

  public static void main(String[] args) {
    schedulePractice();
  }
}
