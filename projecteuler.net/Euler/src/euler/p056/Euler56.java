package euler.p056;

import java.math.BigInteger;

/**
 * A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^(100) is almost unimaginably large: one
 * followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.
 * 
 * Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?
 */
public class Euler56
{
  public static void main(String[] args) {
    long largestChecksum = 0;

    for (int power = 99; power > 90; --power) {
      for (long base = 99; base > 1; --base) {
        BigInteger x = BigInteger.valueOf(base);
        BigInteger f = x.pow(power);

        long checksum = 0;
        String digits = f.toString();
        for (int index = 0; index < digits.length(); ++index) {
          checksum += Integer.valueOf(digits.substring(index, index + 1));
        }

        if (checksum > largestChecksum) {
          largestChecksum = checksum;
          System.out.println(base + "^" + power + " has a checksum of " + largestChecksum);
        }
      }
    }
  }
}
