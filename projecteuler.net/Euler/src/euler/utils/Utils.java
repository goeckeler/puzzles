package euler.utils;

import java.math.BigInteger;
import java.util.*;

import euler.primes.Sieve;

public class Utils
{
  public static long checksum(final BigInteger integer) {
    return checksum(integer.toString());
  }

  public static long checksum(final String number) {
    long checksum = 0;
    for (int digit = 0; digit < number.length(); ++digit) {
      checksum += Long.valueOf(number.substring(digit, digit + 1));
    }
    return checksum;
  }

  public static long divisors(final long number) {
    if (number < 2) return 1;
    long divisors = 2; // 1 and self
    final long max = number / 2;
    for (long divisor = 2; divisor <= max; ++divisor) {
      if (number % divisor == 0) ++divisors;
    }
    return divisors;
  }

  public static BigInteger faculty(final BigInteger number) {
    if (BigInteger.ONE.equals(number)) { return number; }
    BigInteger faculty = BigInteger.ONE;

    for (BigInteger current = number; BigInteger.ONE.compareTo(current) < 1;
         current = current.subtract((BigInteger.ONE)))
    {
      faculty = faculty.multiply(current);
    }

    return faculty;
  }

  public static long faculty(final long n) {
    if (n < 2) return 1;

    long factorial = 1;
    for (long i = 2; i <= n; ++i) {
      factorial *= i;
    }
    return factorial;
  }

  public static boolean palindrome(final int number) {
    final int reverse = Integer.valueOf(new StringBuilder().append(number).reverse().toString());
    return number == reverse;
  }

  public static boolean palindrome(final String text) {
    if (text == null) return false;
    return text.equals(new StringBuilder(text).reverse().toString());
  }

  public static List<Integer> permutations(final Integer number) {
    List<String> permuted = permutations(number.toString());
    final List<Integer> numbers = new ArrayList<>(permuted.size());
    for (final String item : permuted) {
      numbers.add(Integer.valueOf(item));
    }

    permuted = null;
    return numbers;
  }

  public static List<String> permutations(final String text) {
    if (text.length() <= 1) { return Collections.singletonList(text); }

    List<String> entries = null;
    for (int pos = 0; pos < text.length(); ++pos) {
      final String header = text.substring(pos, pos + 1);
      final String trailer = text.substring(0, pos) + text.substring(pos + 1);

      final List<String> permuted = permutations(trailer);

      if (entries == null) {
        entries = new ArrayList<>(text.length() * permuted.size());
      }

      for (final String trailed : permuted) {
        final StringBuilder entry = new StringBuilder(header);
        entry.append(trailed);
        entries.add(entry.toString());
      }
    }

    return entries;
  }

  public static List<Long> primeFactors(final long number) {
    long value = number;
    final List<Long> primes = Sieve.listPrimes(value);
    final List<Long> factors = new ArrayList<>();

    int index = 0;
    while (value > 1) {
      final long prime = primes.get(index);
      if (value % prime == 0) {
        factors.add(prime);
        value /= prime;
      } else {
        ++index;
      }
    }
    return factors;
  }

  public static Set<Integer> rotations(final Integer number) {
    int rotate = number.toString().length();
    final Set<Integer> numbers = new HashSet<>();

    String circular = number.toString();

    numbers.add(number);
    while (rotate > 1) {
      --rotate;
      final String last = circular.substring(circular.length() - 1);
      circular = last + circular.substring(0, circular.length() - 1);
      numbers.add(Integer.valueOf(circular));
    }

    return numbers;
  }

  public static long gcd(final long a, final long b) {
    if (b == 0) { return a; }
    return gcd(b, a % b);
  }

  private static final int[] POWERS_OF_10 = {
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
