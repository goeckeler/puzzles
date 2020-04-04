package euler.p005;

import java.util.ArrayList;
import java.util.List;

/**
 * Smallest multiple
 *
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Euler5
{
  public static void main(final String[] args) {
    final List<Long> numbers = new ArrayList<>(101);
    final int MAX = 20;

    // factorizes all divisors and add all divisors previously unknown
    for (long divisor = MAX; divisor > 1; --divisor) {
      final List<Long> primeFactors = PrimeFactors.primeFactorsOf(divisor);

      for (final Long exists : numbers) {
        primeFactors.remove(exists);
      }

      numbers.addAll(primeFactors);
    }

    // the smallest positive number are these factors multiplied
    final Long smallestNumber = numbers.stream().reduce(1L, (total, factor) -> total * factor);
    System.out.println("The smallest number divisable by all number from 1 to " + MAX + " is " + smallestNumber + ".");
  }
}

class PrimeFactors
{
  // primes below 1000
  // @formatter:off
  static final long[] PRIME_SEED = {
      2,   3,   5,   7,  11,  13,  17,  19,  23,  29,  31,  37,  41,  43,  47,  53,  59,  61,  67,  71,  73,  79,  83,
     89,  97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
    211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347,
    349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479,
    487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631,
    641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787,
    797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947,
    953, 967, 971, 977, 983, 991, 997
  };
  // @formatter:on

  public static List<Long> primeFactorsOf(final Long number) {
    if (number == 1) return List.of(number);
    Long partition = number;

    final List<Long> primes = new ArrayList<>();

    for (int index = 0; partition >= PRIME_SEED[index]; ++index) {
      final long prime = PRIME_SEED[index];

      while (partition % prime == 0) {
        primes.add(prime);
        partition /= prime;
      }
    }

    return primes;
  }
}
