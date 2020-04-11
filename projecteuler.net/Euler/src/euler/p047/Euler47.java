package euler.p047;

import java.util.stream.Collectors;

import euler.primes.Factors;

/**
 * Distinct prime factors
 *
 * The first two consecutive numbers to have two distinct prime factors are:
 *
 * 14 = 2 × 7
 * 15 = 3 × 5
 *
 * The first three consecutive numbers to have three distinct prime factors are:
 *
 * 644 = 2² × 7 × 23
 * 645 = 3 × 5 × 43
 * 646 = 2 × 17 × 19.
 *
 * Find the first four consecutive integers to have four distinct prime factors each. What is the first of these
 * numbers?
 *
 */
public class Euler47
{
  private static int BOUND = 300_000;
  private static Factors factors = new Factors(BOUND);

  public static void main(final String[] args) {
    final int pairing = 4;
    int sequence = 0;
    for (long number = 2; number < BOUND; ++number) {
      if (distinctPrimeFactors(number) == pairing) {
        ++sequence;
      } else {
        sequence = 0;
      }

      if (sequence == pairing) {
        for (int offset = 1 - sequence; offset <= 0; ++offset) {
          System.out.println(String.format("%d : %s", number + offset, factors.primeFactors(number + offset)));
        }
        break;
      }
    }
  }

  private static int distinctPrimeFactors(final long number) {
    return factors.primeFactors(number).stream().collect(Collectors.toSet()).size();
  }
}
