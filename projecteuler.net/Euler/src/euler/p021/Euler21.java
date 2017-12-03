package euler.p021;

import java.util.List;

import euler.utils.Divisors;

/**
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n). If d(a) = b
 * and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 * 
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The
 * proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * 
 * Evaluate the sum of all the amicable numbers under 10000.
 */
public class Euler21
{
  public static void main(String[] args) {
    int max = 10000;
    long amicable = 0L;
    
    for (long number = 1; number <= max; ++number) {
      Long key = sum(Divisors.divisors(number));
      
      if (key != number) {
        Long value = sum(Divisors.divisors(key));
        if (value == number) {
          System.out.println("(" + number + ", " + key + ")");
          amicable += number + key;
        }
      }
    }
    
    amicable /= 2;
    
    System.out.println("sum(amicables) = " + amicable);
  }
  
  public static long sum(List<Long> numbers) {
    long sum = 0;
    for (Long number : numbers) {
      sum += number;
    }
    return sum;
  }
}
