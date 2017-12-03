package euler.p052;

/**
 * It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different
 * order.
 * 
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
 * 
 * @author Thorsten Göckeler
 */
public class Euler052
{
  public static final long FACTOR = 6;

  public static void main(String[] args) {
    brute();
  }

  public static void brute() {
    long number = 1;
    do {
      if (okay(number)) {
        for (long factor = FACTOR; factor >= 1; --factor) {
          System.out.print(number * factor);
          System.out.print(" | ");
          System.out.println(digits(number * factor));
        }
        System.exit(0);
      }
      ++number;
    } while (true);
  }

  public static boolean okay(long number) {
    String base = digits(number);
    for (int factor = 2; factor <= FACTOR; ++factor) {
      String current = digits(number * factor);
      if (!base.equals(current)) { return false; }
    }
    return true;
  }

  public static String digits(long number) {
    int[] digits = new int[10];

    while (number > 0) {
      int digit = Long.valueOf(number % 10).intValue();
      ++digits[digit];
      number /= 10;
    }

    StringBuilder text = new StringBuilder();
    for (int i = 0; i < 10; ++i) {
      if (digits[i] == 0) {
        text.append("-");
      } else {
        text.append(digits[i]);
      }
    }

    return text.toString();
  }
}
