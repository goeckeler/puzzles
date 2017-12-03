package euler.p206;

import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0, where each "_" is a single digit.
 */
public class Euler206
{
  private static final Pattern PATTERN = Pattern.compile("1.2.3.4.5.6.7.8.9.0");

  public static void main(String[] args) {
    System.out.println("Integer is " + solve());
  }
  
  /** works , but takes an hour or so */
  public static void bruteForce() {
    long lower = 1010101020L;
    long upper = 1389026620L;

    long integer = lower;
    while (integer <= upper) {
      BigInteger number = BigInteger.valueOf(integer);
      String asNumber = number.multiply(number).toString();

      System.out.println("Current is " + integer + "^2 = " + asNumber);

      if (asNumber.length() < 19) {
        integer += 10;
        continue;
      }

      boolean found = true;
      for (int i = 1; found && i < 10; ++i) {
        int digit = Integer.valueOf(asNumber.substring((i - 1) * 2, (i - 1) * 2 + 1));
        found = (digit == i);

        /*
         * if (digit > i) { // well, we need to increase the digit before integer += (i % 2 == 0 ? 3 : 1) * Math.pow(10,
         * 9 - i); } else { integer += 10; }
         */
      }

      if (found) {
        System.out.println("Got it! Solution is " + integer + "^2 = " + asNumber);
        break;
      }

      integer += 10;
    }
  }

  public static void binary() {
    long lower = 1010101010L;
    long upper = 1389026623L;

    // modified binary search
    // ### idiot 122 could be regarded as too high whereas 192 is the solution!
    long current = (lower + upper) / 2;
    boolean found = false;
    while (!found && lower < upper) {
      BigInteger number = BigInteger.valueOf(current);
      String asNumber = number.multiply(number).toString();

      System.out.println("Current is " + current + " [" + lower + ", " + upper + "] = " + asNumber);

      if (asNumber.length() < 19) {
        lower = current;
        current = (lower + upper) / 2;
        continue;
      }

      found = true;
      for (int i = 1; found && i < 10; ++i) {
        int digit = Integer.valueOf(asNumber.substring((i - 1) * 2, (i - 1) * 2 + 1));
        found = (digit == i);

        if (!found) {
          if (digit > i) {
            upper = current;
          } else {
            lower = current;
          }

          long now = current;
          while (current == now && lower <= upper) {
            current = (lower + upper) / 2;
            if (current == now) ++lower;
          }
        }
      }
    }
  }

  /** nicked from mvordeme from problem thread */
  public static final long solve() {
    for (long base = 10000000; base <= 14142135; base++) {
      long solution = base * 100 + 30;
      if (PATTERN.matcher(Long.toString(solution * solution)).matches()) return solution;
      solution += 40;
      if (PATTERN.matcher(Long.toString(solution * solution)).matches()) return solution;
    }
    return 0;
  }
}
