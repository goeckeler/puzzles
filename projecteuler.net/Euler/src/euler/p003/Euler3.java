package euler.p003;

import euler.primes.PrimeFactors;

/**
 * Largest prime factor
 *
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143?
 *
 * Spoiler: Just ask https://www.wolframalpha.com or any other search engine.
 */
public class Euler3
{

  public static void main(final String[] args) {
    final long[] numbers = {
      13195L,
      600851475143L
    };

    for (final long number : numbers) {
      System.out.println(String.format("Prime factors of %d are %s.", number, PrimeFactors.primeFactorsOf(number)));
    }
  }
}
