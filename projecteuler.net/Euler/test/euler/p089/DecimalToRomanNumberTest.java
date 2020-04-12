package euler.p089;

import static euler.p089.Euler89.toRoman;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DecimalToRomanNumberTest
{
  @ParameterizedTest
  @CsvSource({
    "MM, 2000",
    "XLIX, 49",
    "MCDXCIII, 1493",
    "MMXX, 2020",
    "XVI, 16",
    "XIX, 19",
    "XLIX, 49",
    "MDCVI, 1606",
    "I, 1"
  })
  void shouldUseLeastDigitsRepresentation(final String roman, final int decimal) {
    assertEquals(roman, toRoman(decimal));
  }
}
