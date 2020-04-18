package euler.p044;

/**
 * Pentagon numbers
 *
 * Pentagonal numbers are generated by the formula, <code>P<sub>n</sub>=n(3n−1)/2</code>. The first ten pentagonal
 * numbers are:
 *
 * 1, 5, 12, 22, 35, 51, 70, 92, 117, 145, ...
 *
 * It can be seen that P<sub>4</sub> + P<sub>7</sub> = 22 + 70 = 92 = P<sub>8</sub>. However, their difference, 70 − 22
 * = 48, is not pentagonal.
 *
 * Find the pair of pentagonal numbers, P<sub>j</sub> and P<sub>k</sub>, for which their sum and difference are
 * pentagonal and D = |P<sub>k</sub> − P<sub>j</sub>| is minimised; what is the value of D?
 */
public class Euler44
{
  /**
   * Of course we could construct say the first million of pentagon numbers and check for each number if we reach
   * another pentagon number. That are 1 Million over 2 combinations, so we have 500,000 * 999,999 possibilities, or
   * roughly 500,000,000,000 runs. 500 billion. Wonder how fast this computer can count that, below a minute?
   *
   * Yes, it can.
   *
   * The maximum integer we can perform a square root on is 2,147,395,600 (well, this is at least the highest perfect
   * square root). That leaves us with a maximum of n of roughly 37,836.
   *
   * Unfortunately we are restricted by the test for pentagons which multiplies the integers far out of reach.
   */
  public static void main(final String[] args) {
    final long MAXIMUM = 1_000_000;
    System.out.format("Maximal value we can search for is %d.\n", MAXIMUM);

    long difference = Long.MAX_VALUE;

    for (long upper = 2; upper < MAXIMUM; ++upper) {
      final long upperPentagon = pentagonal(upper);

      // Pentagonal numbers increase exponential. If the difference to the next lower pentagonal number is smaller than
      // the current known difference we are done, as the number can only increase.
      final long step = upperPentagon - pentagonal(upper - 1);
      if (step >= difference) {
        break;
      }

      // The trick is to go backward and compare the current pentagonal number with all lower pentagonal numbers, so
      // that we minimize the difference
      for (long lower = upper - 1; lower >= 1; --lower) {
        final long lowerPentagon = pentagonal(lower);

        final long sub = upperPentagon - lowerPentagon;

        // As above, once the difference gets bigger than the known difference it cannot get smaller and we can skip all
        // further tests for lower pentagonal numbers.
        if (sub >= difference) {
          break;
        }

        final long add = upperPentagon + lowerPentagon;

        if (isPentagonal(add) && isPentagonal(sub)) {
          difference = sub;
          System.out.format("(%d, %d) Δ %d\n", lowerPentagon, upperPentagon, difference);
        }
      }
    }

    System.out.format("That's all, folks! The smallest difference is %d.", difference);
  }

  /**
   * Just to make the code a bit more readable.
   *
   * @param n the n<sup>th</sup> pentagonal number
   * @return the corresponding pentagonal number
   */
  private static long pentagonal(final long n) {
    return (3 * n - 1) * n / 2;
  }

  private static long isqrt(final long number) {
    return Math.round(Math.floor(Math.sqrt(number)));
  }

  /**
   * A number is a pentagon if we can write it as <code>n(3n−1)/2</code>.
   *
   * The trivial check is thus
   *
   * <code>
   *   final int n = (1 + isqrt(1 + 24 * number)) / 6; // midnight formula
   *   return number == n * (3 * n - 1) / 2;
   * </code>
   *
   * However that multiplication drives us wide above the range of integers ... thus we use long.
   *
   * Furthermore, (1 + 24x) needs to be a perfect square, and 1 + root must be dividable be 6 without a remainder, which
   * tells us the square root divided by 6 must be a remainder of 5.
   *
   * @param number the suspected pentagon number
   * @return <code>true</code> if it is a pentagon, otherwise <code>false</code>
   */
  private static boolean isPentagonal(final long number) {
    final long square = 1 + 24 * number;
    final long root = isqrt(square);

    return root * root == square && root % 6 == 5;
  }
}
