package euler.primes;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactorsTest
{
  @Test
  public void testDivisors() {
    Factors factors = new Factors(200L);
    factors.setNumber(12L);
    assertEquals(6, factors.divisors());
    factors.setNumber(17L);
    assertEquals(2, factors.divisors());
    factors.setNumber(16L);
    assertEquals(5, factors.divisors());
  }
}
