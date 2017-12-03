package euler.utils;

import java.util.LinkedList;
import java.util.List;

public class Divisors {
	public static List<Long> divisors(long number) {
		List<Long> divisors = new LinkedList<Long>();
		divisors.add(1L);
		
		// if (isPrime(number) ...
		
		long max = number / 2L;
		for (long dividend = 2L; dividend <= max; ++dividend) {
			if (number % dividend == 0) {
				divisors.add(dividend);
			}
		}
		
		return divisors;
	}
}
