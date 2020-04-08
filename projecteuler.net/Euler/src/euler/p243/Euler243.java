package euler.p243;

import euler.primes.Totient;

/**
 * Resilience
 *
 * A positive fraction whose numerator is less than its denominator is called a proper fraction.
 * For any denominator, d, there will be d−1 proper fractions; for example, with d = 12:
 * 1/12 , 2/12 , 3/12 , 4/12 , 5/12 , 6/12 , 7/12 , 8/12 , 9/12 , 10/12 , 11/12 .
 *
 * We shall call a fraction that cannot be cancelled down a resilient fraction.
 *
 * Furthermore we shall define the resilience of a denominator, R(d), to be the ratio of its proper fractions that are
 * resilient; for example, R(12) = 4/11. In fact, d = 12 is the smallest denominator having a resilience R(d) < 4/10 .
 *
 * Find the smallest denominator d, having a resilience R(d) < 15499/94744 .
 *
 * Proper fraction : Echter Bruch
 * Cancelled down : Gekürzt
 *
 * Basically fractions cannot be cancelled down if the numerator is a co-prime of the divisor, which means they don't
 * share the same prime factors. The number of such relatively prime numbers can be determined as a totient.
 */
public class Euler243
{

  public static void main(final String[] args) {
    // Just a wild guess
    final int maximum = 1_000_000_000;
    final Totient totient = new Totient(maximum);

    final double cut = 1.0 * 15499 / 94744;

    for (int number = 2; number < maximum; ++number) {
      final double resilience = 1.0 * totient.totient(number) / (number - 1);
      if (resilience < cut) {
        System.out.println("Denominator is " + number);
        break;
      }
    }
  }
}
