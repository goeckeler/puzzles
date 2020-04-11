package euler.p046;

import euler.primes.Totient;

/**
 * Goldbach's other conjecture
 *
 * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice
 * a square.
 *
 * 9 = 7 + 2×1^2
 * 15 = 7 + 2×2^2
 * 21 = 3 + 2×3^2
 * 25 = 7 + 2×3^2
 * 27 = 19 + 2×2^2
 * 33 = 31 + 2×1^2
 *
 * It turns out that the conjecture was false.
 *
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 *
 * Thoughts:
 *
 * Hards and Littlewood conjecture is "Every large odd number (n > 5) is the sum of a prime and the double of a prime."
 * Squares are never prime. That must be the hint where we can prove the above conjecture false.
 *
 * Let's go for a brute force attack and test all odd numbers.
 */
public class Euler46
{
  private static int MAXIMUM = 10_000;
  private static Totient primer = new Totient(MAXIMUM);

  public static void main(final String[] args) {
    // we can start with 35 as all composite numbers below are given in the example
    for (int number = 35; number <= MAXIMUM; number += 2) {
      if (breaksConjecture(number)) {
        System.out.println("\n" + "-".repeat(20) + "\nBreaks: " + number);
        System.exit(0);
      }
    }
  }

  private static boolean breaksConjecture(final int number) {
    // the conjecture only applies to composite odd numbers
    if (primer.isPrime(number)) return false;

    // check if we can find any combination that supports the conjecture
    for (int root = 1; root * root * 2 < number; ++root) {
      if (primer.isPrime(number - root * root * 2)) {
        System.out.println(String.format("%d = %d + 2 * %d^2", number, number - root * root * 2, root));
        return false;
      }
    }

    // Now we haven't found a prime that matches the offset
    return true;
  }
}
