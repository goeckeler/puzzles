package euler.p065;

import java.math.BigInteger;

import euler.utils.Utils;

/**
 * Convergents of e
 *
 * See https://projecteuler.net/problem=65 for a complete description as this needs MathML.
 *
 * We can write the constant <code>e</code> as a continued fraction in the following form:
 *
 * e=[2;1,2,1,1,4,1,1,6,1,...,1,2k,1,...]
 *
 * The first ten terms in the sequence of convergents for e are:
 *
 * <pre>
 * 2  3  8  11  19  87  106, 193, 1264, 1457
 * -, -, -, --, --, --, ---, ---, ----, ----, ...
 * 1  1  3   4   7  32   39   71   465   536
 * </pre>
 *
 * The sum of digits in the numerator of the 10th convergent is 1+4+5+7=17.
 *
 * Find the sum of digits in the numerator of the 100th convergent of the continued fraction for e.
 */
public class Euler65
{
  /**
   * <pre>
   *                                            b                              ca + b
   * So the standard form we talk about is a + --- which we need to convert to ------, and then flip it around.
   *                                            c                                 c
   * </pre>
   */
  public static void main(final String[] args) {
    continuedFraction(1);
    continuedFraction(2);
    continuedFraction(3);
    continuedFraction(10);
    continuedFraction(100);
  }

  private static void continuedFraction(final int steps) {
    // well, I tried with "int" and with "long" and failed utterly
    BigInteger denominator = BigInteger.ZERO; // the initial term has no continued fraction
    BigInteger numerator = BigInteger.ONE; // the initial numerator is the identity value

    for (int index = steps - 1; index >= 0; --index) {
      final BigInteger fraction = BigInteger.valueOf(denominator(index)); // c
      final BigInteger term = fraction.multiply(numerator).add(denominator); // ca + b

      // flip the division
      denominator = numerator;
      numerator = term;
    }

    System.out.format("Using %d steps results in %s / %s, sum is %s.\n", steps, numerator, denominator,
                      Utils.checksum(numerator));
  }

  /**
   * e=[2;1,2,1,1,4,1,1,6,1,...,1,2k,1,...]
   */
  private static int denominator(final int index) {
    // initial term
    if (index == 0) { return 2; }

    // 2k term
    if (index % 3 == 2) { return 2 * (index + 1) / 3; }

    // all others are a one
    return 1;
  }
}
