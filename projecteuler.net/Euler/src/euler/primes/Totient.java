package euler.primes;

import java.util.stream.IntStream;

/**
 * Totient function, also called EulerPhi.
 *
 * See https://mathworld.wolfram.com/TotientFunction.html for an explanation or lookup on wikipedia.
 *
 * Self called super speedy solution in C++ on
 * https://codegolf.stackexchange.com/questions/26739/super-speedy-totient-function.
 *
 * Brilliantly explained solution for an efficient totient function can be found on
 * https://www.geeksforgeeks.org/eulers-totient-function-for-all-numbers-smaller-than-or-equal-to-n/
 *
 * Solutions for all kind of programming languages can be found on Rosetta Code, see
 * https://rosettacode.org/wiki/Totient_function
 */
public class Totient
{
  private final int maximum;
  private final int[] phi;
  private boolean computed = false;

  public Totient(final int maximumNumber) {
    maximum = maximumNumber;
    phi = new int[maximum + 1];
  }

  /**
   * Determine the numbers of numbers less than n which are relatively prime to n. This is also called co-prime. In
   * German it is "teilerfremd".
   *
   * @param number the number for which you want to know the number of relatively primes below n
   * @return the count of co-primes less than the number
   */
  public int totient(final int number) {
    assert number <= maximum;
    assert number > 0;

    computeTotients();
    return phi[number];
  }

  /**
   * Just by knowing the totient you instantly know if the number is prime, as only primes have phi[n] == n-1.
   *
   * @param number the number you want to know if it is a prime number
   * @return <code>true</code> if it is a prime number, otherwise <code>false</code>.
   */
  public boolean isPrime(final int number) {
    assert number <= maximum;
    assert number > 0;

    computeTotients();
    return phi[number] == number - 1;
  }

  /**
   * List all primes from 1 to the known maximum.
   *
   * @return all primes until and including maximum given
   */
  public int[] primes() {
    return primes(2, maximum);
  }

  /**
   * List all primes within the given range.
   *
   * @param lower the lowest number to include, must start at 2.
   * @param upper the highest number to include, cannot be greater than maximum
   * @return all primes within the given range which must be below or equal to maximum
   */
  public int[] primes(final int lower, final int upper) {
    assert upper <= maximum;
    assert lower > 1;
    assert upper > lower;

    final int[] primes = new int[Math.max(1, countPrimes(lower, upper))];

    int index = 0;
    for (int number = Math.max(2, lower); number <= Math.min(upper, maximum); ++number) {
      if (isPrime(number)) {
        primes[index++] = number;
      }
    }

    return primes;
  }

  public int countPrimes(final int lower, final int upper) {
    assert upper <= maximum;
    assert lower > 0;
    assert upper > lower;

    int count = 0;
    for (int number = Math.max(2, lower); number <= Math.min(upper, maximum); ++number) {
      if (isPrime(number)) {
        ++count;
      }
    }

    return count;
  }

  /**
   * Compute all totients up to the upper number to preload calculation
   */
  private void computeTotients() {
    if (computed) return;

    // initialize so that all totients are too high indicating that that totient hasn't been evaluated yet
    IntStream.rangeClosed(1, maximum).forEach(i -> phi[i] = i);

    // run an advanved version of the Sieve of Erathosthenes
    for (int i = 2; i <= maximum; ++i) {
      // already evaluated?
      if (phi[i] < i) continue;

      // apply the efficient solution based on the product formula (see above)

      // The formula basically says that the value of Φ(n) is equal to n multiplied by product of (1 – 1/p) for all
      // prime factors p of n. For example value of Φ(6) = 6 * (1-1/2) * (1 – 1/3) = 2.
      for (int j = i; j <= maximum; j += i) {
        phi[j] -= phi[j] / i;
      }
    }

    computed = true;
  }
}
