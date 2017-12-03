package euler.p025;

import java.math.BigInteger;

/**
 * What is the first term in the Fibonacci sequence to contain 1000 digits?
 */
public class Euler25 {
	public static void main(String[] args) {
		// the last fibonacci number
		BigInteger fibonacci = BigInteger.ONE;
		// the previous fibonacci number as fib(n) = fib(n-1) + fib(n-2)
		BigInteger fibonacci1 = BigInteger.ONE;
		;

		int term = 2;
		// the length should be until 1000 digits
		while (fibonacci.toString().length() < 1000) {
			BigInteger fib = fibonacci.add(fibonacci1);
			fibonacci1 = fibonacci;
			fibonacci = fib;
			++term;
		}

		System.out.println("fibonacci(" + term + ") = "	+ fibonacci.toString() + " contains " + fibonacci.toString().length() + " digits");
	}
}