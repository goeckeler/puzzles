package euler.p052;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class Euler052Test
{
  @Test
  public void testDigits() {
    assertEquals("112-12--1-", Euler052.digits(51428520));
    assertFalse(Euler052.okay(51428520));
  }
}
