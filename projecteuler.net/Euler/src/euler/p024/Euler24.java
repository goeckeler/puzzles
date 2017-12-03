package euler.p024;


/**
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2,
 * 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The
 * lexicographic permutations of 0, 1 and 2 are:
 *
 * 012 021 102 120 201 210
 *
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class Euler24
{
  static int count;

  public static void main(final String[] args) {
    System.out.println("Creating permutations ...");
    count = 0;
    permutations("", "0123456789");
    // permutations("", "0123456789", 0);
  }

  public static void permutations(final String current, final String text) {
    if (text.isEmpty()) {
      System.out.println(current.toString() + ", #" + count++);

      if (count > 1000000) System.exit(0);
      return;
    }

    for (int pos = 0; pos < text.length(); ++pos) {
      String header = text.substring(pos, pos + 1);
      String trailer = text.substring(0, pos) + text.substring(pos + 1);

      permutations(current + header, trailer);
    }
  }
}
