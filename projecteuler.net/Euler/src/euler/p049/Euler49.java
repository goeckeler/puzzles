package euler.p049;

import java.util.Set;

import euler.primes.Sieve;

/**
 * Prime permutations
 *
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in two ways: (i)
 * each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.
 *
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property, but there is
 * one other 4-digit increasing sequence.
 *
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 *
 */
public class Euler49
{
  public static void main(final String[] args) {
    // We need to apply the following constraints:
    //
    // a) All terms are primes.
    // b) All terms are exactly 4 digits
    // c) We need only terms that are permutations of each other
    // d) We have to discover an arithmetic sequence i.e. equidistance

    final Bag<Integer, Long> permutations = new Bag<>(1229);

    for (final long prime : Sieve.listPrimes(9999)) { // a
      if (prime < 1000) continue; // b
      permutations.put(fingerprint(Math.toIntExact(prime)), prime); // c
    }

    // d
    for (final Set<Long> candidates : permutations.values()) {
      if (candidates.size() < 3) continue;

      final Sequencer sequencer = new Sequencer(candidates);
      System.out.print(sequencer.sequence());
    }
  }

  public static int fingerprint(int number) {
    int fingerprint = 0;
    while (number > 0) {
      final int digit = number % 10;
      number /= 10;
      fingerprint += powerOfTen(digit);
    }
    return fingerprint;
  }

  static final int[] POWERS_OF_10 = {
    1,
    10,
    100,
    1000,
    10000,
    100000,
    1000000,
    10000000,
    100000000,
    1000000000
  };

  public static int powerOfTen(final int pow) {
    return POWERS_OF_10[pow];
  }
}
