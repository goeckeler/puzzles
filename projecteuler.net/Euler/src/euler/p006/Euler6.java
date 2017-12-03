package euler.p006;

import java.text.NumberFormat;

/**
 * What is the difference between the sum of the squares and the square of the
 * sums for the first 100 natural numbers?
 */
public class Euler6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long n = 100;
		long sumNumbers = (1 + n) * n / 2;
		long sumSquareOfSum = sumNumbers * sumNumbers;
		long sumSquares = 0;
		
		for (long i = 1; i <= n; i++) {
			sumSquares += i * i;
		}

		System.out.println("Square of sums until " + NumberFormat.getIntegerInstance().format(n) + " is "+ NumberFormat.getIntegerInstance().format(sumSquareOfSum));
		System.out.println("Sum of squares until " + NumberFormat.getIntegerInstance().format(n) + " is "+ NumberFormat.getIntegerInstance().format(sumSquares));
		System.out.println("Difference of sums is " + NumberFormat.getIntegerInstance().format(sumSquareOfSum - sumSquares));
	}
}
