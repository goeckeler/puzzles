package euler.primes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class TotientTest
{
  private static Totient totient;

  @BeforeAll
  static void setup() {
    totient = new Totient(1000);
  }

  @ParameterizedTest
  @CsvSource({
    " 1,   1",
    " 2,   1",
    " 3,   2",
    " 4,   2",
    " 5,   4",
    " 6,   2",
    " 7,   6",
    " 8,   4",
    " 9,   6",
    "10,   4",
    "11,  10",
    "12,   4",
    "13,  12",
    "14,   6",
    "15,   8",
    "16,   8",
    "17,  16",
    "18,   6",
    "19,  18",
    "20,   8",
    "21,  12",
    "22,  10",
    "23,  22",
    "24,   8",
    "25,  20"
  })
  void testTotient(final int number, final int totients) {
    assertEquals(totients, totient.totient(number));
  }

  @ParameterizedTest
  @ValueSource(ints = {
    2,
    3,
    5,
    7,
    983
  })
  void testValidPrimes(final int number) {
    assertTrue(totient.isPrime(number));
  }

  @ParameterizedTest
  @ValueSource(ints = {
    1,
    9,
    987
  })
  void testInvalidPrimes(final int number) {
    assertFalse(totient.isPrime(number));
  }
}
