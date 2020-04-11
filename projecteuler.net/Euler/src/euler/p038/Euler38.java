package euler.p038;

import java.util.HashSet;
import java.util.Set;

/**
 * Pandigital multiples
 *
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 *
 * 192 × 1 = 192
 * 192 × 2 = 384
 * 192 × 3 = 576
 *
 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated
 * product of 192 and (1,2,3)
 *
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645,
 * which is the concatenated product of 9 and (1,2,3,4,5).
 *
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer
 * with (1,2, ... , n) where n > 1?
 */
public class Euler38
{
  public static void main(final String[] args) {
    // there are only 9 digits available and the factor must be at least 2 - 9/2 = 4. Thus no more than 4 digits.
    final int maximum = 9999;

    int largest = 0;
    int seed = 0;
    int factor = 0;

    for (int number = 2; number <= maximum; ++number) {
      final StringBuilder pandigital = new StringBuilder();
      for (int multiplicator = 1; multiplicator <= 9; ++multiplicator) {
        pandigital.append(number * multiplicator);
        if (pandigital.length() == 9) {
          // is this really pandigital?
          if (isPandigital(pandigital.toString())) {
            final int value = Integer.valueOf(pandigital.toString());
            if (value > largest) {
              largest = value;
              seed = number;
              factor = multiplicator;

              System.out.println(String.format("%d with (1 .. %d) yields %d.", seed, factor, largest));
            }
          }
        } else if (pandigital.length() > 9) {
          break;
        }
      }
    }

    System.out.println(String.format("\n\n%d with (1 .. %d) yields %d.", seed, factor, largest));
  }

  public static boolean isPandigital(final String pandigital) {
    final Set<Character> digits = new HashSet<>(Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9'));

    for (final Character character : pandigital.toCharArray()) {
      digits.remove(character);
    }

    return digits.isEmpty();
  }
}
