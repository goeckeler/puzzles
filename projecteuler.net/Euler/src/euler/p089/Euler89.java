package euler.p089;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import euler.objects.MutablePair;

/**
 * Roman numerals
 *
 * For a number written in Roman numerals to be considered valid there are basic rules which must be followed. Even
 * though the rules allow some numbers to be expressed in more than one way there is always a "best" way of writing a
 * particular number.
 *
 * For example, it would appear that there are at least six ways of writing the number sixteen:
 *
 * IIIIIIIIIIIIIIII
 * VIIIIIIIIIII
 * VVIIIIII
 * XIIIIII
 * VVVI
 * XVI
 *
 * However, according to the rules only XIIIIII and XVI are valid, and the last example is considered to be the most
 * efficient, as it uses the least number of numerals.
 *
 * The 11K text file p089.roman.txt contains one thousand numbers written in valid, but not necessarily minimal, Roman
 * numerals. See https://projecteuler.net/about=roman_numerals for the definitive rules for this problem.
 *
 * Find the number of characters saved by writing each of these in their minimal form.
 *
 * Note: You can assume that all the Roman numerals in the file contain no more than four consecutive identical units.
 */
public class Euler89
{

  public static void main(final String[] args) throws IOException {
    try (Stream<String> stream = Files.lines(Paths.get("p089.roman.txt"))) {
      final MutablePair<Integer, Integer> lengths = new MutablePair<>(0, 0);

      stream.forEach(line -> {
        lengths.setKey(lengths.getKey() + line.length());
        lengths.setValue(lengths.getValue() + toRoman(toDecimal(line)).length());
      });

      System.out.format("%d characters saved.", lengths.getKey() - lengths.getValue());
    }
  }

  private static final String[] ROMANS = {
    "M",
    "CM",
    "D",
    "CD",
    "C",
    "XC",
    "L",
    "XL",
    "X",
    "IX",
    "V",
    "IV",
    "I"
  };
  private static final int[] DECIMALS = {
    1000,
    900,
    500,
    400,
    100,
    90,
    50,
    40,
    10,
    9,
    5,
    4,
    1
  };

  public static int toDecimal(String roman) throws NumberFormatException {
    if (roman == null || roman.isEmpty()) { throw new NumberFormatException("Cannot convert empty roman numbers."); }
    if (!roman.matches("[MDCLXVI]*")) { throw new NumberFormatException("Invalid roman literals in '" + roman + "'"); }

    int decimal = 0;
    int denominator = 0;
    while (!roman.isEmpty()) {
      while (roman.startsWith(ROMANS[denominator])) {
        decimal += DECIMALS[denominator];
        roman = roman.substring(ROMANS[denominator].length());
      }
      denominator++;
    }

    return decimal;
  }

  public static String toRoman(int decimal) throws NumberFormatException {
    if (decimal <= 0) { throw new NumberFormatException("Unknown negative or zero number in roman literals."); }
    final StringBuilder roman = new StringBuilder();

    int denominator = 0;
    while (decimal > 0) {
      while (decimal - DECIMALS[denominator] >= 0) {
        roman.append(ROMANS[denominator]);
        decimal -= DECIMALS[denominator];
      }
      denominator++;
    }
    return roman.toString();
  }
}
