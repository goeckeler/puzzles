package euler.p043;

import euler.utils.Utils;

/**
 * Sub-string divisibility
 *
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some
 * order, but it also has a rather interesting sub-string divisibility property.
 *
 * Let d<sub>1</sub> be the 1<sup>st</sup> digit, d<sub>2</sub> be the 2<sup>nd</sup> digit, and so on. In this way, we
 * note the following:
 *
 * d<sub>2</sub>d<sub>3</sub>d<sub>4</sub>=406 is divisible by 2
 * d<sub>3</sub>d<sub>4</sub>d<sub>5</sub>=063 is divisible by 3
 * d<sub>4</sub>d<sub>5</sub>d<sub>6</sub>=635 is divisible by 5
 * d<sub>5</sub>d<sub>6</sub>d<sub>7</sub>=357 is divisible by 7
 * d<sub>6</sub>d<sub>7</sub>d<sub>8</sub>=572 is divisible by 11
 * d<sub>7</sub>d<sub>8</sub>d<sub>9</sub>=728 is divisible by 13
 * d<sub>8</sub>d<sub>9</sub>d<sub>10</sub>=289 is divisible by 17
 *
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */
public class Euler43
{
  public static void main(final String[] args) {
    // either count from 1023456789 to 9876543210 and check if the number is pandigital or simply create all
    // pandigital numbers and check for the above conditions
    long sum = 0;
    for (final String pandigital : Utils.permutations("1234567890")) {
      if (pandigital.startsWith("0")) continue;
      if (Integer.valueOf(pandigital.substring(7, 10)) % 17 != 0) continue;
      if (Integer.valueOf(pandigital.substring(6, 9)) % 13 != 0) continue;
      if (Integer.valueOf(pandigital.substring(5, 8)) % 11 != 0) continue;
      if (Integer.valueOf(pandigital.substring(4, 7)) % 7 != 0) continue;
      if (Integer.valueOf(pandigital.substring(3, 6)) % 5 != 0) continue;
      if (Integer.valueOf(pandigital.substring(2, 5)) % 3 != 0) continue;
      if (Integer.valueOf(pandigital.substring(1, 4)) % 2 != 0) continue;
      System.out.println(pandigital);
      sum += Long.valueOf(pandigital);
    }
    System.out.format("\nThe sum of all these pandigitals is %d.", sum);
  }
}
