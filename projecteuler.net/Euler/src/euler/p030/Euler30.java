package euler.p030;

/**
 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
 * 
 * <pre>
 *  1634 = 14 + 64 + 34 + 44
 *  8208 = 84 + 24 + 04 + 84
 *  9474 = 94 + 44 + 74 + 44
 * </pre>
 * 
 * As 1 = 14 is not a sum it is not included.
 * 
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 * 
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 * 
 * Solutions are:
 * 
 * 4150 4151 54748 92727 93084 194979
 * 
 * Sum is 443839.
 * 
 * Maximum number of digits to test: 10^n >= n * 9^5, d.h. n >= lg(n * 9^5).
 * 
 */
public class Euler30
{
  static final long[] exp3 =
      {
        0L, 1L, 2L * 2L * 2L, 3L * 3L * 3L, 4L * 4L * 4L, 5L * 5L * 5L, 6L * 6L * 6L, 7L * 7L * 7L, 8L * 8L * 8L,
        9L * 9L * 9L
      };

  static final long[] exp4 =
      {
        0L, 1L, 2L * 2L * 2L * 2L, 3L * 3L * 3L * 3L, 4L * 4L * 4L * 4L, 5L * 5L * 5L * 5L, 6L * 6L * 6L * 6L,
        7L * 7L * 7L * 7L, 8L * 8L * 8L * 8L, 9L * 9L * 9L * 9L
      };

  static final long[] exp5 =
      {
        0L, 1L, 2L * 2L * 2L * 2L * 2L, 3L * 3L * 3L * 3L * 3L, 4L * 4L * 4L * 4L * 4L, 5L * 5L * 5L * 5L * 5L,
        6L * 6L * 6L * 6L * 6L, 7L * 7L * 7L * 7L * 7L, 8L * 8L * 8L * 8L * 8L, 9L * 9L * 9L * 9L * 9L
      };

  public static void main(String[] args) {
    solve(6);
  }

  public static void solve(int digitsMax) {
    for (int digits = 2; digits <= digitsMax; ++digits) {
      solve(0L, 0, digits);
    }
  }

  public static void solve(long number, int index, int digits) {
    if (index == digits) {
      // check solution
      check(number);
    } else {
      for (int digit = 0; digit <= 9; ++digit) {
        // add a digit and check again
        if (index == 0 && digit == 0) continue;
        solve(number * 10 + digit, index + 1, digits);
      }
    }
  }

  public static void check(long number) {
    long exp = 0L;
    long value = number;

    while (value > 0) {
      Long index = value % 10;
      value /= 10;
      exp += exp5[index.intValue()];
    }

    if (exp == number) {
      System.out.println(number);
    }
  }
}
