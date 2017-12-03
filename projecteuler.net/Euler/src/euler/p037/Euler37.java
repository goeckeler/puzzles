package euler.p037;

import java.util.List;

import euler.primes.Sieve;

/**
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from
 * left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797,
 * 379, 37, and 3.
 * 
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left. 2, 3, 5, and 7
 * are not considered to be truncatable primes.
 */
public class Euler37
{
  public static void main(String[] args) {
    int max = 799997;
    List<Long> primes = Sieve.listPrimes(max);
    int found = 11;
    int index = 0;
    long sum = 0;
    
    while (found > 0) {
      long prime = primes.get(index);
      if (prime > 9L && truncatable(prime, primes)) {
        --found;
        System.out.println(prime);
        sum += prime;
      }
      ++index;
    }
    
    System.out.println("SUM = " + sum);
  }

  public static boolean truncatable(long prime, List<Long> primes) {
    String text = "" + prime;
    
    String first = text.substring(0, 1);
    String last = text.substring(text.length() - 1, text.length());
    
    if ("3".equals(last) || "7".equals(last)) {
      if ("2".equals(first) || "3".equals(first) || "5".equals(first) || "7".equals(first)) {
        boolean truncatable = true;
        // check left truncate (last digit has been checked already)
        for (int i = 1; truncatable && i < text.length() - 1; ++i) {
          truncatable = primes.contains(Long.valueOf(text.substring(i)));
        }
        
        // check right truncate (first digit has been checked already)
        for (int i = text.length() - 1; truncatable && i > 1; --i) {
          truncatable = primes.contains(Long.valueOf(text.substring(0,i)));
        }
        
        return truncatable;
      }
    }
    
    return false;
  }
}
