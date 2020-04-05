package euler.p026;

/**
 * Reciprocal cycles
 *
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to
 * 10 are given:
 *
 * 1/2 = 0.5
 * 1/3 = 0.(3)
 * 1/4 = 0.25
 * 1/5 = 0.2
 * 1/6 = 0.1(6)
 * 1/7 = 0.(142857)
 * 1/8 = 0.125
 * 1/9 = 0.(1)
 * 1/10 = 0.1
 *
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring
 * cycle.
 *
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
public class Euler26
{
  public static void main(final String[] args) {
    int maximalCycle = 0;
    long maximalDivisor = 0L;
    for (long divisor = 2; divisor <= 1000; ++divisor) {
      final int cycle = cycleLength(divisor);
      System.out.println(String.format("%d with a cycle of %d.", divisor, cycle));

      if (cycle > maximalCycle) {
        maximalCycle = cycle;
        maximalDivisor = divisor;
      }
    }

    System.out.println("-".repeat(50));
    System.out.println(String.format("%d has the largest cycle of %d.", maximalDivisor, maximalCycle));
  }

  /**
   * Math from grammar school ... find the next number.
   *
   * 1 / 7 = 0 (0 R 7, add a 0)
   * 10 / 7 = 1 (1 R 3, add a 0)
   * 30 / 7 = 4 (4 R 2)
   * 20 / 7 = 2 (2 R 6)
   * 60 / 7 = 8 (8 R 4)
   * 40 / 7 = 5 (5 R 5)
   * 50 / 7 = 7 (7 R 1)
   * 10 / 7 = 1
   *
   * Wait a second. We started with 1, so once the remainder is 1 again, then the cycle starts again.
   *
   * But what if there is no remainder like 1/2 = 0.5? Then we stop there and now.
   *
   * Beware of the cunning one digit repetitions:
   *
   * 10 / 6 = 1 R 4
   * 40 / 6 = 6 R 4
   * 40 / 6 = 6 R 4 ...
   *
   * Let's put it this way. If the remainder repeats the previous remainder we also have a loop.
   *
   * Finally beware of the prime factors of 10, that are 2 and 5. If you divide by 28 you get ...
   *
   * 100 / 28 = 3 R 16
   * 160 / 28 = 5 R 20
   * 200 / 28 = 7 R 4
   * 40 / 28 = 1 R 12
   * 120 / 28 = 4 R 8
   * 80 / 28 = 2 R 24
   * 240 / 28 = 8 R 16 - and here it starts again. The cycle length is 6.
   *
   * If we remove those prime factors from the divisor we won't get the same result but the same cycle.
   * 28 / 2 = 14 /2 = 7.
   */
  public static int cycleLength(long divisor) {
    while (divisor > 2 && divisor % 2 == 0) {
      divisor /= 2;
    }
    while (divisor > 5 && divisor % 5 == 0) {
      divisor /= 5;
    }

    long nominator = 1;
    long lastRemainder = 0;

    int length = 0;
    do {
      nominator = nominator * 10 % divisor;

      if (nominator == 0) { return 0; } ;
      if (nominator == lastRemainder) { return 1; }

      lastRemainder = nominator;
      ++length;
    } while (nominator != 1);

    return length;
  }
}
