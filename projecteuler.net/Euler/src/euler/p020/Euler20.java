package euler.p020;

import java.math.BigInteger;

/**
 * Find the sum of the digits of 100!
 */
public class Euler20 {
	public static void main(String[] args) {
		System.out.println("2! = " + faculty(new BigInteger("2")));
		System.out.println("6! = " + faculty(new BigInteger("6")));
		System.out.println("50! = " + faculty(new BigInteger("50")));
		System.out.println("100! = " + faculty(new BigInteger("100")));
		
		String fac100 = faculty(new BigInteger("100")).toString();
		long sum = 0;
		for (int digit = 0; digit < fac100.length(); ++digit) {
			sum += Integer.valueOf(fac100.substring(digit, digit + 1));
		}
		System.out.println("Digit sum is " + sum);
	}

	public static BigInteger faculty(final BigInteger number) {
		if (BigInteger.ONE.equals(number)) {
			return number;
		}
		BigInteger faculty = BigInteger.ONE;

		for (BigInteger current = number; BigInteger.ONE.compareTo(current) < 1; current = current.subtract((BigInteger.ONE))) {
			faculty = faculty.multiply(current);
		}

		return faculty;
	}
}
