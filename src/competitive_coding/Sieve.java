package competitive_coding;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sieve {

  public static List<Integer> getPrimeNumbers(int n){
    return SimpleSieve.getPrimeNumbers(n);
  }

  public static List<Integer> getPrimeNumbers(int start, int end){
    return SegmentedSieve.getPrimeNumbers(start, end);
  }

  public static Stream<Integer> stream(){
    return new IncrementalSieve().stream();
  }

  public static List<List<Integer>> getTwinPrimes(int n){
    List<Integer> primes = SimpleSieve.getPrimeNumbers(n);
    List<List<Integer>> twins = new ArrayList<>();

    for (int i = 1; i < primes.size(); i++){
      if (primes.get(i) - primes.get(i-1) == 2){
        twins.add(List.of(primes.get(i-1), primes.get(i)));
      }
    }

    return twins;
  }
}

class SimpleSieve {
  public static List<Integer> getPrimeNumbers(int n) {
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true);
    for (int i = 2; i * i <= n; i++) {
      if (isPrime[i]) {
        for (int j = i * i; j <= n; j += i) {
          isPrime[j] = false;
        }
      }
    }
    return IntStream.rangeClosed(2, n).filter(i -> isPrime[i]).boxed().toList();
  }
}

class SegmentedSieve {
  public static List<Integer> getPrimeNumbers(int start, int end) {
    boolean[] isPrime = new boolean[end - start + 1];
    Arrays.fill(isPrime, true);

    List<Integer> primes = SimpleSieve.getPrimeNumbers((int) Math.sqrt(end));

    for (int prime : primes) {
      int base = Math.max(prime * prime, ((start + prime - 1) / prime) * prime);
      for (int j = base; j <= end; j += prime) {
        isPrime[j - start] = false;
      }
    }

    if (start == 1) isPrime[0] = false;

    return IntStream.rangeClosed(Math.max(start, 2), end).filter(i -> isPrime[i - start]).boxed().toList();
  }
}

class IncrementalSieve implements Iterator<Integer>, Iterable<Integer> {
  private int current = 2;
  private final Map<Integer, List<Integer>> compositeMap = new HashMap<>();

  @Override
  public Iterator<Integer> iterator(){
    return this;
  }

  @Override
  public boolean hasNext(){
    return true;
  }

  @Override
  public Integer next(){
    while (true){
      if (!this.compositeMap.containsKey(current)){
        compositeMap.put(current * current, new ArrayList<>(List.of(current)));
        return current++;
      } else {
        for (int prime: compositeMap.get(current)){
          int nextMultiple = current + prime;
          compositeMap.computeIfAbsent(nextMultiple, k -> new ArrayList<>()).add(prime);
        }
        compositeMap.remove(current);
        current++;
      }
    }
  }

  public Stream<Integer> stream(){
    return Stream.generate(this::next);
  }
}
