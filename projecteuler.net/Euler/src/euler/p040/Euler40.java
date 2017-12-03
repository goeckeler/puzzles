package euler.p040;

/**
 * An irrational decimal fraction is created by concatenating the positive integers:
 * 
 * 0.123456789101112131415161718192021...
 * 
 * It can be seen that the 12th digit of the fractional part is 1.
 * 
 * If d(n) represents the n-th digit of the fractional part, find the value of the following expression.
 * 
 * d(1) * d(10) * d(100) * d(1000) * d(10000) * d(100000) * d(1000000)
 */
public class Euler40
{
  public static void main(String[] args) {
    long product = 1L;
    long index = 0L;
    long next = 1L;
    long upper = 1000000;
    for (long number = 1; next <= upper && number < upper; ++number) {
      String part = new StringBuilder().append(number).toString();
      index += part.length();
      if (index >= next) {
        int offset = Long.valueOf(index - next).intValue();
        int digit = Integer.valueOf(part.substring(part.length() - offset - 1, part.length() - offset));
        System.out.println("d(" + next + ") = " + digit);
        product *= digit;
        next *= 10;
      }
    }
    System.out.println("product = " + product);
  }
}
