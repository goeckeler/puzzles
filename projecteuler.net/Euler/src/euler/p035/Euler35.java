package euler.p035;

import euler.primes.Totient;
import euler.utils.Utils;

/**
 * Circular primes
 *
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves
 * prime. There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 *
 * How many circular primes are there below one million?
 */
public class Euler35
{
  // Funnily enough I must have solved it at some time probably involving Wolfram Alpha.
  // Wonderful insight about Circular Primes at https://primes.utm.edu/glossary/page.php?sort=CircularPrime
  // Nonetheless I will use a kind of intelligent brute force here.

  // a) Determine all primes until 1.000.000.
  // b) Check if all rotations are also prime - cancel if the rotated digit is even.
  // c) If so increase the counter until done.

  // We could mark all rotated primes as being visited to fasten the process.
  public static void main(final String[] args) {
    final int maximum = 1_000_000;
    final Totient sieve = new Totient(10 * maximum); // 199 => 991 rotated!
    final int[] primes = sieve.primes(2, maximum);
    int circularPrimesCount = 0;

    for (final int prime : primes) {
      final int digits = Integer.valueOf(prime).toString().length();
      int rotated = prime;
      do {
        final int head = rotated / 10;
        final int tail = rotated % 10;
        rotated = tail * Utils.powerOfTen(digits - 1) + head;

        if (!sieve.isPrime(rotated)) {
          break;
        }
      } while (rotated != prime);

      if (prime == rotated) {
        System.out.print(prime);
        System.out.print(" ");
        ++circularPrimesCount;
      }
    }

    System.out.println();
    System.out.println("-".repeat(72));
    System.out.println(String.format("There are %d primes until %d.", primes.length, maximum));
    System.out.println(String.format("There are %d circular primes until %d.", circularPrimesCount, maximum));
  }
}
