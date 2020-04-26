package euler.p045;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TWO;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Triangular, pentagonal, and hexagonal
 *
 * Triangle: T<sub>n</sub>=n(n+1)/2 => 1, 3, 6, 10, 15, ...
 * Pentagonal: P<sub>n</sub>=n(3n−1)/2 => 1, 5, 12, 22, 35, ...
 * Hexagonal: H<sub>n</sub>=n(2n−1) => 1, 6, 15, 28, 45, ...
 *
 * It can be verified that T<sub>285</sub> = P<sub>165</sub> = H<sub>143</sub> = 40755.
 *
 * Find the next triangle number that is also pentagonal and hexagonal.
 *
 */
public class Euler45
{
  public static void main(final String[] args) {
    final Map<BigInteger, Integer> pv = new HashMap<>(100_000);
    final Map<BigInteger, Integer> hv = new HashMap<>(100_000);

    // triangle grow slower than the others so their numbers are already known
    for (int n = 1;; ++n) {
      final BigInteger t = triangle(n);

      pv.put(pentagonal(n), n);
      hv.put(hexagonal(n), n);

      if (pv.get(t) != null && hv.get(t) != null) {
        System.out.format("%d = t(%d), p(%d), h(%d)\n", t, n, pv.get(t), hv.get(t));
        if (n > 285) break;
      }
    }
  }

  private static BigInteger triangle(final int n) {
    final BigInteger v = BigInteger.valueOf(n);
    return v.multiply(v.add(ONE)).divide(TWO);
  }

  private final static BigInteger THREE = BigInteger.valueOf(3);

  private static BigInteger pentagonal(final int n) {
    final BigInteger v = BigInteger.valueOf(n);
    return v.multiply(v.multiply(THREE).subtract(ONE)).divide(TWO);
  }

  private static BigInteger hexagonal(final int n) {
    final BigInteger v = BigInteger.valueOf(n);
    return v.multiply(v.multiply(TWO).subtract(ONE));
  }
}
