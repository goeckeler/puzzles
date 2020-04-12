package euler.p092;

import java.util.BitSet;

/**
 * Square digit chains
 *
 * A number chain is created by continuously adding the square of the digits in a number to form a new number until it
 * has been seen before.
 *
 * For example,
 *
 * 44 → 32 → 13 → 10 → 1 → 1
 * 85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89
 *
 * Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. What is most amazing is that EVERY
 * starting number will eventually arrive at 1 or 89.
 *
 * How many starting numbers below ten million will arrive at 89?
 *
 * Notes:
 *
 * To make it clearer:
 *
 * 44 -> 4² + 4² = 16 + 16 = 32
 * 32 -> 3² + 2² = 9 + 4 = 13
 * 13 -> 1² + 3² = 1 + 9 = 10
 *
 * Now the highest number below 10,000,000 is 9,999,999. 9 * 9² = 567. So we only need to calculate the sequences for
 * those 567 numbers and reuse them for the higher numbers.
 */
public class Euler092
{
  private static BitSet eightish = new BitSet();
  private static BitSet oneish = new BitSet();

  public static void main(final String[] args) {
    final int MAXIMUM = 9_999_999;

    int count = 0;
    for (int number = 1; number <= MAXIMUM; ++number) {
      if (endsWith89(squareOfDigits(number))) {
        ++count;
      }
    }

    System.out.format("There are %d numbers that will arrive at 89 below and including %d.", count, MAXIMUM);
  }

  private static boolean endsWith89(final int number) {
    if (number == 89) return true;
    if (number == 1) return false;

    if (number == 89 || eightish.get(number)) return true;
    if (number == 1 || oneish.get(number)) return false;

    final boolean chainEnds = endsWith89(squareOfDigits(number));
    if (chainEnds) {
      eightish.set(number);
    } else {
      oneish.set(number);
    }

    return chainEnds;
  }

  private static int squareOfDigits(int number) {
    int square = 0;
    while (number > 0) {
      final int root = number % 10;
      square += root * root;
      number /= 10;
    }
    return square;
  }
}
