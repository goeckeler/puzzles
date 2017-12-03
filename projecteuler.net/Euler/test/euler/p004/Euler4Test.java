package euler.p004;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import euler.p004.Euler4;

public class Euler4Test
{
  @Test
  public void testPalindrome() {
    assertTrue(Euler4.palindrome(11));
    assertTrue(Euler4.palindrome(101));
    assertTrue(Euler4.palindrome(9009));
    assertFalse(Euler4.palindrome(9001));
  }
}
