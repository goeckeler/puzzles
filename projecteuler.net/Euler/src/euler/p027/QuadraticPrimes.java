package euler.p027;

import euler.primes.Sieve;

/**
 * Euler published the remarkable quadratic formula:
 * 
 * n² + n + 41
 * 
 * It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40,
 * 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by
 * 41.
 * 
 * Using computers, the incredible formula n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive
 * values n = 0 to 79. The product of the coefficients, −79 and 1601, is −126479.
 * 
 * Considering quadratics of the form:
 * 
 * n² + an + b, where |a| < 1000 and |b| < 1000
 * 
 * where |n| is the modulus/absolute value of n e.g. |11| = 11 and |−4| = 4
 * 
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of
 * primes for consecutive values of n, starting with n = 0.
 */
public class QuadraticPrimes
{
  public static void main(String[] args) {
    long maxNumber = 1000L * 1000L;
    Sieve primes = new Sieve(maxNumber);

    int maxCount = 0;
    int maxA = 0;
    int maxB = 0;

    for (int a = -999; a < 1000; ++a) {
      for (int b = -999; b < 1000; ++b) {
        int count = 0;
        int number = 0;

        while (true) {
          number = count * count + a * count + b;
          if (number < 2) break;

          if (number > maxNumber) {
            System.out.println("Cannot check " + number + " as prime.");
            System.exit(1);
          }

          if (!primes.isPrime(number)) break;

          ++count;
        }

        if (count > maxCount) {
          maxCount = count;
          maxA = a;
          maxB = b;
        }
      }
    }

    System.out.println("a = " + maxA + ", b = " + maxB + " produces " + maxCount + " primes.");
    System.out.println("Solution is " + (maxA * maxB));
  }
}
