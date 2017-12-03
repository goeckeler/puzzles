package euler.p036;

/**
 * The decimal number, 585 = 1001001001_(2) (binary), is palindromic in both bases.
 * 
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 */
public class Euler36
{
  public static void main(String[] args) {
    debug(585);
    debug(1);
    debug(10);
    debug(11);
    
    long sum = 0;
    for (int number=1; number < 1000000; ++number) {
      if (palindrome(number) && binaryPalindrome(number)) {
        sum += number;
      }
    }
    
    System.out.println("SUM IS " + sum);
  }

  public static void debug(int number) {
    System.out.println(number + " (" + Integer.toBinaryString(number) + ") is " + palindrome(number) + " & " +
                       binaryPalindrome(number));
  }

  public static boolean palindrome(final int number) {
    int reverse = Integer.valueOf(new StringBuilder().append(number).reverse().toString());
    return number == reverse;
  }

  public static boolean binaryPalindrome(final int number) {
    String forward = Integer.toBinaryString(number);
    String reverse = new StringBuilder(forward).reverse().toString();
    return forward.equals(reverse);
  }
}
