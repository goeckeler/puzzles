package euler.p089;

import static euler.p089.Euler89.toDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RomanNumberToDecimalsTest
{
  @ParameterizedTest
  @CsvSource({
    "I, 1",
    "V, 5",
    "X, 10",
    "L, 50",
    "C, 100",
    "D, 500",
    "M, 1000"
  })
  void shouldUnderstandLiterals(final String roman, final int decimal) {
    assertEquals(decimal, toDecimal(roman));
  }

  @ParameterizedTest
  @CsvSource({
    "IV, 4",
    "IX, 9",
    "XL, 40",
    "XC, 90",
    "CD, 400",
    "CM, 900"
  })
  void shouldUnderstandSubstraction(final String roman, final int decimal) {
    assertEquals(decimal, toDecimal(roman));
  }

  @ParameterizedTest
  @CsvSource({
    "MM, 2000",
    "XLIX, 49",
    "MCDXCIII, 1493",
    "MMXX, 2020"
  })
  void shouldUnderstandComposition(final String roman, final int decimal) {
    assertEquals(decimal, toDecimal(roman));
  }

  @ParameterizedTest
  @CsvSource({
    "XIIIIII, 16",
    "XIIIIIIIII, 19",
    "XVIIII, 19",
    "XXXXIIIIIIIII, 49",
    "XXXXVIIII, 49",
    "XXXXIX, 49",
    "XXXXIX, 49",
    "XLVIIII, 49",
    "MCCCCCCVI, 1606"
  })
  void shouldUnderstandVerboseWriting(final String roman, final int decimal) {
    assertEquals(decimal, toDecimal(roman));
  }

  @ParameterizedTest
  @ValueSource(strings = {
    "MUM",
    "AMA",
    "1X",
    "CCZ"
  })
  void shouldFailOnInvalidCharacters(final String roman) {
    assertThrows(IllegalArgumentException.class, () -> {
      toDecimal(roman);
    });
  }
}
