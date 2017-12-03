package euler.p265;

import java.util.BitSet;
import java.util.LinkedList;

/**
 * 2^N binary digits can be placed in a circle so that all the N-digit clockwise subsequences are distinct.
 *
 * For N=3, two such circular arrangements are possible, ignoring rotations.
 *
 * <ul>
 * <li>a) 00010111
 * <li>b) 00011101
 * </ul>
 *
 * For the first arrangement, the 3-digit subsequences, in clockwise order, are:
 *
 * 000, 001, 010, 101, 011, 111, 110 and 100.
 *
 * Each circular arrangement can be encoded as a number by concatenating the binary digits starting with the subsequence
 * of all zeros as the most significant bits and proceeding clockwise. The two arrangements for N=3 are thus represented
 * as 23 and 29:
 *
 * <ul>
 * <li>00010111 = 23
 * <li>00011101 = 29
 * </ul>
 *
 * Calling S(N) the sum of the unique numeric representations, we can see that S(3) = 23 + 29 = 52.
 *
 * Find S(5)
 */
public class Euler265
{
  private static long ringSum = 0;

  /**
   * @param args
   */
  public static void main(final String[] args) {
    int digits = 5;
    int bits = 1 << digits;
    LinkedList<Integer> set = new LinkedList<Integer>();

    ringSum = 0L;
    solve(set, digits, bits);
    System.out.println("\nThe sum of all rings is " + ringSum);
  }

  private static void solve(final LinkedList<Integer> set, final int digits, final int bits) {
    if (set.size() < digits) {
      set.add(0);
      solve(set, digits, bits);
    } else {
      if (set.size() < bits) {
        set.add(0);
        if (test(set, digits, bits)) {
          solve(set, digits, bits);
        }
        set.removeLast();

        set.add(1);
        if (test(set, digits, bits)) {
          solve(set, digits, bits);
        }
        set.removeLast();
      } else {
        StringBuilder binary = new StringBuilder();
        for (Integer digit : set) {
          binary.append(digit);
        }

        long decimal = Long.valueOf(binary.toString(), 2);
        System.out.println("RING " + binary.toString() + " IS " + decimal);
        ringSum += decimal;
      }
    }
  }

  private static boolean test(final LinkedList<Integer> set, final int digits, final int bits) {
    // there are as many permutations as there are digits in the solution
    // per default all are false, that is they are not contained till now
    BitSet sequences = new BitSet(bits);

    // check all previous sequences
    for (int last = set.size() - 1; last >= digits - 1; --last) {
      int index = toInt(set, last, digits);

      if (sequences.get(index)) {
        return false;
      }

      // each sequence can be present once and only once in the end
      sequences.set(index);
    }

    // finally on completion of the ring test forward as well
    if (set.size() == bits) {
      for (int last = 0; last < digits - 1; ++last) {
        int index = toInt(set, last, digits);

        if (sequences.get(index)) {
          return false;
        }

        // each sequence can be present once and only once in the end
        sequences.set(index);
      }
    }

    return true;
  }

  private static int toInt(final LinkedList<Integer> set, final int last, final int digits) {
    StringBuilder number = new StringBuilder();

    int index = last;
    for (int n = 0; n < digits; ++n) {
      number.append(set.get(index));
      --index;
      if (index < 0) index += set.size();
    }

    number.reverse();
    return Integer.valueOf(number.toString(), 2);
  }
}
