package euler.p028;

/**
 * Starting with the number 1 and moving to the right in a clockwise direction a
 * 5 by 5 spiral is formed as follows:
 * 
 * <pre>
 * 21* 22  23  24  25*
 * 20   7*  8   9* 10
 * 19   6   1*  2  11
 * 18   5*  4   3* 12
 * 17* 16  15  14  13*
 * </pre>
 * 
 * It can be verified that the sum of the numbers on the diagonals is 101.
 * 
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral
 * formed in the same way?
 */
public class Spiral {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
      System.out.println("sum(3x3) = " + sum(3) + ", should be 25.");
      System.out.println("sum(5x5) = " + sum(5) + ", should be 101.");
      System.out.println("sum(1001x1001) = " + sum(1001) + ".");
	}

	public static long sum(int size) {
		long steps = (size - 1) / 2;
		return 2 * deltaSum(steps) - 1;
	}
	
	public static long deltaSum(long steps) {
		long sum = 1;
		long corner = 1;
		for (int n = 1; n <= steps; ++n) {
			long delta = 2 * corner + 10 * n;
			corner += 8 * n;
			sum += delta;
		}
		
		return sum;
	}
}
