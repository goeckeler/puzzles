package euler.p254;

import java.math.BigInteger;
import java.util.BitSet;

/**
 * Define f(n) as the sum of the factorials of the digits of n. For example, f(342) = 3! + 4! + 2! = 32.
 *
 * Define sf(n) as the sum of the digits of f(n). So sf(342) = 3 + 2 = 5.
 *
 * Define g(i) to be the smallest positive integer n such that sf(n) = i. Though sf(342) is 5, sf(25) is also 5, and it
 * can be verified that g(5) is 25.
 *
 * Define sg(i) as the sum of the digits of g(i). So sg(5) = 2 + 5 = 7.
 *
 * Further, it can be verified that g(20) is 267 and ∑ sg(i) for 1 ≤ i ≤ 20 is 156.
 *
 * What is ∑ sg(i) for 1 ≤ i ≤ 150?
 */
public class Euler254
{
  public static void main(final String[] args) {
    BigInteger upper = BigInteger.valueOf(50L);
    BigInteger sumG = BigInteger.ZERO;

    BitSet git = new BitSet(upper.intValue());

    int found = 0;
    for (long n = 1; n < Integer.MAX_VALUE; ++n) {
      BigInteger value = sf(f(n));

      if (n % 100000 == 0) {
        System.out.print("CHECKING > " + n);
        System.out.println(" FOUND " + found + " / " + upper);
      }

      // got a solution already? are we in range?
      if (value.compareTo(upper) > 0 || git.get(value.intValue())) continue;

      git.set(value.intValue());
      ++found;
      sumG = sumG.add(sf(BigInteger.valueOf(n)));

      System.out.print("INTEGER " + n + " MAPS TO " + value + " AND ADDS " + sf(BigInteger.valueOf(n)) + " TO YIELD " +
                       sumG);
      System.out.println(" FOUND " + found + " / " + upper);

      if (found == upper.intValue()) break;
    }

    System.out.println("\n\nSum is " + sumG);
  }

  public static long fac(final long n) {
    if (n < 2) return 1;

    long factorial = 1;
    for (long i = 2; i <= n; ++i) {
      factorial *= i;
    }
    return factorial;
  }

  public static BigInteger f(final long number) {
    BigInteger sum = BigInteger.ZERO;
    long value = number;

    while (value > 0) {
      long digit = value % 10;
      value /= 10;

      sum = sum.add(faculty[(int) digit]);
    }

    return sum;
  }

  public static BigInteger sf(final BigInteger number) {
    BigInteger sum = BigInteger.ZERO;
    String value = number.toString();

    for (int index = 0; index < value.length(); ++index) {
      sum = sum.add(BigInteger.valueOf(Long.valueOf(value.substring(index, index + 1))));
    }

    return sum;
  }

  static BigInteger[] faculty =
      {
        BigInteger.valueOf(fac(0L)), BigInteger.valueOf(fac(1L)), BigInteger.valueOf(fac(2L)),
        BigInteger.valueOf(fac(3L)), BigInteger.valueOf(fac(4L)), BigInteger.valueOf(fac(5L)),
        BigInteger.valueOf(fac(6L)), BigInteger.valueOf(fac(7L)), BigInteger.valueOf(fac(8L)),
        BigInteger.valueOf(fac(9L))
      };
}
