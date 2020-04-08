package euler.p070;

import java.util.Arrays;

import euler.primes.Totient;

/**
 * Totient permutation
 *
 * Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of positive
 * numbers less than or equal to n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less
 * than nine and relatively prime to nine, φ(9)=6.
 * The number 1 is considered to be relatively prime to every positive number, so φ(1)=1.
 *
 * Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
 *
 * Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum.
 */
public class Euler70
{

  public static void main(final String[] args) {
    final int maximum = 10_000_000;
    final Totient totient = new Totient(maximum);

    double currentRatio = Double.MAX_VALUE;
    int currentNumber = 0;

    for (int number = 2; number < maximum; ++number) {
      final int phi = totient.totient(number);
      if (arePermutations(number, phi)) {
        final double ratio = 1.0 * number / phi;

        if (ratio < currentRatio) {
          currentRatio = ratio;
          currentNumber = number;
        }
      }
    }

    System.out.println(String.format("Teilerärmste permutierte Zahl bis %d ist %d mit einem Wert von %f.", maximum,
                                     currentNumber, currentRatio));
  }

  /**
   * Missing system test.
   *
   * System.out.println("87109 is a permutation of 79180 : " + arePermutations(87109, 79180));
   * System.out.println("12345678 is a permutation of 87654321 : " + arePermutations(12345678, 87654321));
   * System.out.println("2 is a permutation of 2 : " + arePermutations(2, 2));
   * System.out.println("23 is not a permutation of 24 : " + arePermutations(23, 24));
   */
  static boolean arePermutations(final int n1, final int n2) {
    return Bucket.of(n1).equals(Bucket.of(n2));
  }
}

class Bucket
{
  private final int[] bucket = new int[10];

  public static Bucket of(final int number) {
    return new Bucket(number);
  }

  private Bucket(int number) {
    assert number >= 0;

    while (number > 0) {
      ++bucket[number % 10];
      number /= 10;
    }
  }

  @Override
  public int hashCode() {
    return Arrays.stream(bucket).sum();
  }

  @Override
  public boolean equals(final Object that) {
    if (this == that) return true;
    if (that == null) return false;
    if (getClass() != that.getClass()) return false;

    return Arrays.equals(bucket, ((Bucket) that).bucket);
  }

  @Override
  public String toString() {
    return Arrays.toString(bucket);
  }
}
