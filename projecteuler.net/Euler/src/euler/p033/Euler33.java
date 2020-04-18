package euler.p033;

import static euler.primes.PrimeFactors.isPrime;
import static euler.utils.Utils.gcd;

/**
 * Digit cancelling fractions
 *
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may
 * incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 *
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 *
 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two
 * digits in the numerator and denominator.
 *
 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 */
public class Euler33
{

  public static void main(final String[] args) {
    // Well, both numerator and denominator must share at least one prime factor so we can cancel the term.
    // By the way, forget about all multiples of 11 as they cannot share the same digits for all fractions below one.
    // Be aware of the prime factors 2,3 and 5 as they can be contained in the remaining digit. 2*3*5 = 30.
    int fraction_numerator = 1;
    int fraction_denominator = 1;

    for (int denominator = 12; denominator < 100; ++denominator) {
      if (isPrime(Long.valueOf(denominator)) || denominator % 11 == 0) {
        continue;
      }

      for (int numerator = 11; numerator < denominator; ++numerator) {
        // we could ignore primes, but with numbers less than 100 a modulo operation is much faster
        if (numerator % 11 == 0 || (30 * denominator) % numerator != 0) {
          continue;
        }

        // trivial examples to be excluded
        if (denominator % 10 == 0 || numerator % 10 == 0) {
          continue;
        }

        // now we remove the digits of the numerator from the denominator if possible and check if the same division
        // happens
        if (digitCancelling(numerator, denominator)) {
          fraction_numerator *= numerator;
          fraction_denominator *= denominator;
        } ;
      }
    }

    // In the product of the factors, find the greatest common denominator to cancel it out to get the lowest value
    final long product = fraction_denominator / gcd(fraction_numerator, fraction_denominator);
    System.out.format("\nThe product of the denominators of the cancelled fractions is %d.", product);
  }

  private static boolean digitCancelling(final int numerator, final int denominator) {
    final int fraction = 30 * denominator / numerator;
    final String down = String.valueOf(denominator);
    for (final String digit : String.valueOf(numerator).split("")) {
      if (down.contains(digit)) {
        final int d = Integer.valueOf(String.valueOf(denominator).replaceFirst(digit, ""));
        final int n = Integer.valueOf(String.valueOf(numerator).replaceFirst(digit, ""));

        if ((30 * d) % n == 0 && (30 * d) / n == fraction) {
          System.out.format("(%d / %d) => (%d / %d)\n", numerator, denominator, n, d);
          return true;
        }
      }
    }

    return false;
  }
}
