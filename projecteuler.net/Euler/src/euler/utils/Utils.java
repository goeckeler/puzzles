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

  // @formatter:off
  private final static int[] SQUARE_ROOT_OFFSETS = {
    0,    16,  22,  27,  32,  35,  39,  42,  45,  48,  50,  53,  55,  57,
    59,   61,  64,  65,  67,  69,  71,  73,  75,  76,  78,  80,  81,  83,
    84,   86,  87,  89,  90,  91,  93,  94,  96,  97,  98,  99, 101, 102,
    103, 104, 106, 107, 108, 109, 110, 112, 113, 114, 115, 116, 117, 118,
    119, 120, 121, 122, 123, 124, 125, 126, 128, 128, 129, 130, 131, 132,
    133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 144, 145,
    146, 147, 148, 149, 150, 150, 151, 152, 153, 154, 155, 155, 156, 157,
    158, 159, 160, 160, 161, 162, 163, 163, 164, 165, 166, 167, 167, 168,
    169, 170, 170, 171, 172, 173, 173, 174, 175, 176, 176, 177, 178, 178,
    179, 180, 181, 181, 182, 183, 183, 184, 185, 185, 186, 187, 187, 188,
    189, 189, 190, 191, 192, 192, 193, 193, 194, 195, 195, 196, 197, 197,
    198, 199, 199, 200, 201, 201, 202, 203, 203, 204, 204, 205, 206, 206,
    207, 208, 208, 209, 209, 210, 211, 211, 212, 212, 213, 214, 214, 215,
    215, 216, 217, 217, 218, 218, 219, 219, 220, 221, 221, 222, 222, 223,
    224, 224, 225, 225, 226, 226, 227, 227, 228, 229, 229, 230, 230, 231,
    231, 232, 232, 233, 234, 234, 235, 235, 236, 236, 237, 237, 238, 238,
    239, 240, 240, 241, 241, 242, 242, 243, 243, 244, 244, 245, 245, 246,
    246, 247, 247, 248, 248, 249, 249, 250, 250, 251, 251, 252, 252, 253,
    253, 254, 254, 255
  };
  // @formatter:on

  /**
   * Integer square root
   *
   * A faster replacement for (int)(java.lang.Math.sqrt(x)). Completely accurate for x < 2,147,483,648 (i.e. 2^31). See
   * <a href="http://atoms.alife.co.uk/sqrt/SquareRoot.java">http://atoms.alife.co.uk/</a> for credentials and more
   * details.
   *
   * Unfortunately there must be something wrong as 2.147.395.600 yields 46.341 and not 46.340.
   */
  public static int isqrt(final int x) {
    int xn;

    if (x >= 0x10000) {
      if (x >= 0x1000000) {
        if (x >= 0x10000000) {
          if (x >= 0x40000000) {
            xn = SQUARE_ROOT_OFFSETS[x >> 24] << 8;
          } else {
            xn = SQUARE_ROOT_OFFSETS[x >> 22] << 7;
          }
        } else {
          if (x >= 0x4000000) {
            xn = SQUARE_ROOT_OFFSETS[x >> 20] << 6;
          } else {
            xn = SQUARE_ROOT_OFFSETS[x >> 18] << 5;
          }
        }

        xn = (xn + 1 + (x / xn)) >> 1;
        xn = (xn + 1 + (x / xn)) >> 1;
        return ((xn * xn) > x) ? --xn : xn;
      } else {
        if (x >= 0x100000) {
          if (x >= 0x400000) {
            xn = SQUARE_ROOT_OFFSETS[x >> 16] << 4;
          } else {
            xn = SQUARE_ROOT_OFFSETS[x >> 14] << 3;
          }
        } else {
          if (x >= 0x40000) {
            xn = SQUARE_ROOT_OFFSETS[x >> 12] << 2;
          } else {
            xn = SQUARE_ROOT_OFFSETS[x >> 10] << 1;
          }
        }

        xn = (xn + 1 + (x / xn)) >> 1;

        return ((xn * xn) > x) ? --xn : xn;
      }
    } else {
      if (x >= 0x100) {
        if (x >= 0x1000) {
          if (x >= 0x4000) {
            xn = (SQUARE_ROOT_OFFSETS[x >> 8]) + 1;
          } else {
            xn = (SQUARE_ROOT_OFFSETS[x >> 6] >> 1) + 1;
          }
        } else {
          if (x >= 0x400) {
            xn = (SQUARE_ROOT_OFFSETS[x >> 4] >> 2) + 1;
          } else {
            xn = (SQUARE_ROOT_OFFSETS[x >> 2] >> 3) + 1;
          }
        }

        return ((xn * xn) > x) ? --xn : xn;
      } else {
        if (x >= 0) { return SQUARE_ROOT_OFFSETS[x] >> 4; }
      }
    }

    throw new IllegalArgumentException("Cannot determine the square root of negative number " + x + ".");
  }
}
