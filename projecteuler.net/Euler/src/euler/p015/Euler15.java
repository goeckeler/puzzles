package euler.p015;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Starting in the top left corner of a 2x2 grid, there are 6 routes (without backtracking) to the bottom right corner,
 * whereas <code>l</code> denotes "left" and <code>d</code> stands for "down".
 * 
 * <ol>
 * <li>l l d d
 * <li>l d l d
 * <li>l d d l
 * <li>d l l d
 * <li>d l d l
 * <li>d d l l
 * </ol>
 * 
 * How many routes are there through a 20*20 grid?
 * 
 * Solution:
 * 
 * f(1,n) = 1
 * f(2,n) = n
 *           m
 * f(n,m) =  Σ f(n-1, i)
 *          i=1
 */

public class Euler15
{
  private static Map<Integer, BigInteger> cache = new HashMap<Integer, BigInteger>(20 * 30);
  
  public static void main(String[] args) {
    // okay, as I can imagine middle of squares better than edges, that 2x2 grid is the same as a 3x3 in my mind, so we
    // are searching for a 21x21 grid
    
    System.out.println("f(1,1) = " + compute(1,1) + ", expected " + 1);
    System.out.println("f(2,4) = " + compute(2,4) + ", expected " + 4);
    System.out.println("f(3,2) = " + compute(3,2) + ", expected " + 3);
    System.out.println("f(3,3) = " + compute(3,3) + ", expected " + 6);
    System.out.println("f(4,4) = " + compute(4,4) + ", expected " + 20);
    System.out.println("f(5,5) = " + compute(5,5) + ", expected " + 70);
    System.out.println("f(6,6) = " + compute(6,6) + ", expected " + 252);
    System.out.println("f(21,21) = " + compute(21,21) + ", expected 137846528820");
  }
  
  public static BigInteger compute(int cols, int rows) {
    if (cols == 1 || rows == 1) return BigInteger.ONE;
    if (cols == 2 || rows == 2) return BigInteger.valueOf(cols).max(BigInteger.valueOf(rows));
    
    int index = Math.max(cols, rows) + 1000 * Math.min(cols, rows);
    BigInteger sum = cache.get(index);
    
    if (sum == null) {
      // well, compute it anew
      sum = BigInteger.ZERO;
      for (int n = 1; n <= rows; ++n) {
        sum = sum.add(compute(cols - 1, n));
      }
      cache.put(index, sum);
    }
    
    return sum;
  }
}
