package euler.utils;

import static euler.utils.Utils.isqrt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class IntegerSquareRootsTest
{

  @Test
  void shouldFailOnNegativeNumbers() {
    assertThrows(IllegalArgumentException.class, () -> {
      isqrt(-4);
    });
  }

  @ParameterizedTest
  @MethodSource("provideSquaredIntegers")
  void shouldComputeSquareRoots(final int expected, final int number) {
    assertEquals(expected, isqrt(number));
  }

  private static Stream<Arguments> provideSquaredIntegers() {
    // @formatter:off
    return Stream.of(
      Arguments.of(1, 1),
      Arguments.of(2, 4),
      Arguments.of(5, 25),
      Arguments.of(16, 256),
      Arguments.of(15535, 241_336_225),
      Arguments.of(46340, 2_147_395_600)
    );
    // @formatter:on
  }

  @ParameterizedTest
  @CsvSource({
    "5, 26",
    "5, 27",
    "5, 28",
    "5, 29",
    "5, 35",
    "6, 38"
  })
  void shouldApproximateSquareRootsToLowerBound(final int expected, final int number) {
    assertEquals(expected, isqrt(number));
  }
}
