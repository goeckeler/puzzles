package euler.p097;

import java.math.BigInteger;

/**
 * The first known prime found to exceed one million digits was discovered in 1999, and is a Mersenne prime of the form
 * 2^(6972593)−1; it contains exactly 2,098,960 digits. Subsequently other Mersenne primes, of the form 2^(p)−1, have
 * been found which contain more digits.
 * 
 * However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits: 28433×2^(7830457)+1.
 * 
 * Find the last ten digits of this prime number.
 */
public class Euler97
{
  public static void main(String[] args) {
    BigInteger two = BigInteger.valueOf(2);
    BigInteger cut = BigInteger.valueOf(10000000000L);
    BigInteger number = BigInteger.ONE;
    number = number.multiply(BigInteger.valueOf(28433));
    for (int i=0; i < 7830457; ++i) {
      if (i % 10000 == 0) System.out.print(".");
      number = number.multiply(two).mod(cut);
    }
    number = number.add(BigInteger.ONE).mod(cut);
    System.out.println("\n" + number.toString());
  }
}
