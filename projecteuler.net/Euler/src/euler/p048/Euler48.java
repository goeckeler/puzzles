package euler.p048;

import java.math.BigInteger;

/**
 * The series, 1^(1) + 2^(2) + 3^(3) + ... + 10^(10) = 10405071317.
 * 
 * Find the last ten digits of the series, 1^(1) + 2^(2) + 3^(3) + ... +
 * 1000^(1000).
 */
public class Euler48 {
	public static void main(String[] args) {
		BigInteger sum = BigInteger.ZERO;

		// 1000^1000 is irrelevant
		for (int base = 1; base < 1000; ++base) {
			// from 10^10 onwards at least 10 0s will be added
			if (base % 10 != 0) {
				BigInteger factor = BigInteger.valueOf(base);
				sum = sum.add(factor.pow(base));
			}
		}
		
		String summed = sum.toString();
		System.out.println(summed);
		
		if (summed.length() > 10) {
			System.out.println(summed.substring(summed.length() - 10));
		}
	}
}
