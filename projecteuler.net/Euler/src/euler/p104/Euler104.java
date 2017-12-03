package euler.p104;

import java.util.HashSet;
import java.util.Set;

/**
 * Fibonacci sequence with pandigital header and trailer
 * 
 * The Fibonacci sequence is defined by the recurrence relation:
 * 
 * F(n) = F(n−1) + F(n−2), where F(1) = 1 and F(2) = 1.
 * 
 * It turns out that F(541), which contains 113 digits, is the first Fibonacci number for which the last nine digits are
 * 1-9 pandigital (contain all the digits 1 to 9, but not necessarily in order). And F(2749), which contains 575 digits,
 * is the first Fibonacci number for which the first nine digits are 1-9 pandigital.
 * 
 * Given that F(k) is the first Fibonacci number for which the first nine digits AND the last nine digits are 1-9
 * pandigital, find k.
 * 
 * Brute force doesn't do the trick this time. Either use clever maths or try approximation of first 9 digits.
 * 
 * @author Thorsten Göckeler
 */
public class Euler104
{
  public static void main(String[] args) {
    new Pandigital().solve();
  }
}

class Pandigital
{
  private static final long HIGH = 987654321L;
  private static final long LOW = 123456789L;
  private static final long CUT = 1000000000L;
  private static final long PRECISION = 1000000L;
  private static final long PRUNE = CUT * PRECISION;

  private Set<Integer> digits = new HashSet<Integer>();

  public void solve() {
    solve(3L, 1L, 1l, 1l, 1l);
  }

  private void solve(long index, long topUpper, long topLower, long lowUpper, long lowLower) {
    while (true) {
      boolean pandigital = false;

      long top = topUpper + topLower;
      if (top > PRUNE) {
        topUpper /= 10;
        top /= 10;
      }
      long low = (lowUpper + lowLower) % CUT;

      if (rightPandigital(low)) {
        System.out.println("Trailing pandigital fibonacci(" + index + ").");
        pandigital = true;
      }

      if (leftPandigital(top)) {
        System.out.println("Leading pandigital fibonacci(" + index + ").");
        if (pandigital) {
          System.out.println("Double sided pandigital (" + index + ").");
          return;
        }
      }

      ++index;
      topLower = topUpper;
      topUpper = top;
      lowLower = lowUpper;
      lowUpper = low;

      // if (index > 3000) return;
    }
  }

  public boolean rightPandigital(long number) {
    // check if the last nine digits consists of 1 to 9
    long trailer = number % CUT;
    return isPandigital(trailer);
  }

  public boolean leftPandigital(long number) {
    // check if the first nine digits consists of 1 to 9
    long value = number;
    while (value > LOW) {
      if (value > CUT) {
        // simply reduce the number that only the first nine digits
        // remain
        value /= 10L;
      } else {
        if (value <= HIGH) { return isPandigital(value); }
        return false;
      }
    }

    return false;
  }

  public boolean isPandigital(long number) {
    // checks if the digits 1 to 9 occur exactly once
    digits.clear();
    for (int i = 1; i <= 9; ++i) {
      digits.add(i);
    }

    while (number > 0) {
      if (!digits.remove(Long.valueOf(number % 10).intValue())) { return false; }
      number /= 10;
    }

    return digits.isEmpty();
  }
}
