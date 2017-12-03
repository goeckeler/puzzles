package euler.p050;

import java.util.List;

import euler.primes.Sieve;

/**
 * The prime 41, can be written as the sum of six consecutive primes:
 * 
 * 41 = 2 + 3 + 5 + 7 + 11 + 13
 * 
 * This is the longest sum of consecutive primes that adds to a prime below one-hundred. The longest sum of consecutive
 * primes below one-thousand that adds to a prime, contains 21 terms, and is equal to 953.
 * 
 * Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */
public class Euler50
{
  public static void main(String[] args) {
    System.out.println("Generating primes ...");
    int maximum = 1000000;
    int largestIndex = 0;
    long largestTerms = 0;
    List<Long> primer = Sieve.listPrimes(maximum);
    System.out.println("Examining " + primer.size() + " primes.");
    long[] primes = new long[primer.size()];
    for (int i=0; i<primer.size(); ++i) {
      primes[i] = primer.get(i);
    }
    primer = null;
    System.out.println("Copied primes.");

    for (int index = 0; index < primes.length && primes[index] < maximum; ++index) {
      long terms = terms(index, primes);
      System.out.println("Prime " + primes[index] + " has " + terms + " terms.");
      if (terms > largestTerms) {
        largestIndex = index;
        largestTerms = terms;
      }
    }
    
    System.out.println("Prime #" + largestIndex + " = " + primes[largestIndex] + " has " + largestTerms + " terms");
  }
  
  public static long sum(int lower, int upper, int[] primes) {
    long sum = 0;
    for (int i=lower; i <= upper; ++i) sum += primes[i];
    return sum;
  }
  
  public static long terms(int index, long[] primes) {
    long prime = primes[index];
    
    // determine starting sum which is below the prime
    long sum = 0;
    int n = 0;
    while (sum < prime) {
      sum += primes[n++];
    }
    
    // sum starts with 2?
    if (sum == prime) return n;
    
    // have to remove from the start as the last prime was too high
    int s = 0;
    while (sum > prime) {
      sum -= primes[s++]; 
      --n;
    }

    if (sum == prime) return n;
    return 0;
  }
}
