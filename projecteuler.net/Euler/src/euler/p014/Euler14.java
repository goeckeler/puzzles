package euler.p014;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * The following iterative sequence is defined for the set of positive integers:
 * 
 * <pre>
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 * </pre>
 * 
 * Using the rule above and starting with 13, we generate the following sequence:
 * 
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * 
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been
 * proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * 
 * Which starting number, under one million, produces the longest chain? Once the chain starts the terms are allowed to
 * go above one million.
 */
public class Euler14
{
  static Map<BigInteger, Integer> cache = new HashMap<BigInteger, Integer>(1000003);
  static BigInteger TWO = BigInteger.valueOf(2);
  static BigInteger THREE = BigInteger.valueOf(3);
  static BigInteger MAX = BigInteger.valueOf(1000000);
  static BigInteger DOTS = BigInteger.valueOf(20000);

  public static void main(String[] args) {
    // System.out.println("113383 has " + countNoCache(BigInteger.valueOf(113383)) + " terms");

    BigInteger largestNumber = BigInteger.ONE;
    long largestTerm = 0;
    for (BigInteger number = TWO; number.compareTo(MAX) < 0; number = number.add(BigInteger.ONE)) {
      int count = countNoCache(number);
      if (count > largestTerm) {
        largestNumber = number;
        largestTerm = count;
      }

      if (number.mod(DOTS).equals(BigInteger.ZERO)) {
        System.out.print('.');
      }
    }
    System.out.println("\nNumber " + largestNumber + " produces " + largestTerm + " terms.");
  }

  public static int terms(BigInteger number) {
    if (BigInteger.ONE.equals(number)) return 1;

    Integer terms = cache.get(number);
    if (terms == null) {
      terms = countTerms(number);
      cache.put(number, terms);
    }
    return terms;
  }

  public static int countTerms(BigInteger number) {
    if (number.mod(TWO) == BigInteger.ZERO) { return 1 + terms(number.divide(TWO)); }

    return 1 + terms(number.multiply(THREE).add(BigInteger.ONE));
  }

  public static int countNoCache(BigInteger number) {
    // System.out.println(number);
    if (BigInteger.ONE.equals(number)) return 1;

    if (number.mod(TWO) == BigInteger.ZERO) { return 1 + countNoCache(number.divide(TWO)); }

    return 1 + countNoCache(number.multiply(THREE).add(BigInteger.ONE));
  }
}
