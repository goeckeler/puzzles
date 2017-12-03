package euler.p004;

import static java.text.NumberFormat.getIntegerInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is
 * 9009 = 91 � 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class Euler4
{
  public static void main(final String[] args) {
    List<Integer> range = new ArrayList<Integer>(998001);

    System.out.println("Generating products of 3-digit numbers.");
    // all products of 3-digit numbers
    for (int n1 = 999; n1 >= 100; --n1) {
      for (int n2 = 999; n2 >= 100; --n2) {
        range.add(n1 * n2);
      }
    }

    System.out.println("Checking " + getIntegerInstance().format(range.size()) + " products of 3-digit numbers.");

    // now check if they are palindromes and remember the largest
    int largest = -1;
    for (Integer number : range) {
      if (palindrome(number)) {
        if (number > largest) {
          largest = number;
          System.out.println("Found larger product " + getIntegerInstance().format(number) + ".");
        }
      }
    }

    System.out.println("Largest palindrome is " + getIntegerInstance().format(largest));
  }

  public static boolean palindrome(final int number) {
    int reverse = Integer.valueOf(new StringBuilder().append(number).reverse().toString());
    return number == reverse;
  }
}
