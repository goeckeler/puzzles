package euler.p032;

import java.util.Set;
import java.util.TreeSet;

import euler.utils.Utils;

/**
 * Pandigital products
 *
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example,
 * the 5-digit number, 15234, is 1 through 5 pandigital.
 *
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1
 * through 9 pandigital.
 *
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9
 * pandigital. HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 *
 * Some thoughts:
 *
 * The product must have 4 digits. If it would have 5 digits, the remaining 4 digits cannot yield such a number, as
 * 97*86 is the highest possible product. 9 * 876 is lower. If it would have 3 digits, the other terms must have 3
 * digits as well, that doesn't work out either. The smallest product would be 2*1345.
 *
 * If we simply check all permutations of 1-9 that would be 9! = 362.880‬ numbers to check. The other path would be to
 * combine prime factors of each four digit number in the given range.
 *
 * All prime numbers can be excluded right away as 1 * nn = nn ... always one 1 too many.
 */
public class Euler32
{
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

  public static void main(final String[] args) {
    final Set<Integer> products = new TreeSet<>();
    for (final Integer numeric : Utils.permutations(123456789)) {
      // last 4 numbers
      final int product = numeric % 10000;

      for (int length = 1; length < 5; ++length) {
        final int divisor = powerOfTen(9 - length);
        final int multiplicand = numeric / divisor;
        final int multiplier = (numeric - multiplicand * divisor) / 10000;

        if (multiplier * multiplicand == product) {
          products.add(product);
          break;
        }
      }
    }
    System.out.println("Pandigital product sum is " + products.stream().reduce(Integer::sum).get());
  }
}
