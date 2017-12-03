package euler.p041;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import euler.primes.Sieve;

/**
 * We shall say that an n-digit number is pandigital if it makes use of all the
 * digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is
 * also prime.
 * 
 * What is the largest n-digit pandigital prime that exists?
 * 
 * Reasoning: 1-9 and 1-8 digits are not possible, as the cross sum is divisable
 * by 3 (and thus not a prime). So the highest possible pandigital is 7654321.
 * 
 * There are two approaches: 
 * 
 * 1) Generate all primes up to this highest values and then check for pandigitally.
 * 2) Check for prime, go downwards and exclude all non pandigitals and all quickly divisables (like even numbers ..)
 */
public class PandigitalPrime {
	private static final long MAXIMUM = 7654321L;
	
	public static void main(String[] args) {
		solveByPrime(MAXIMUM);
	}
	
	public static void solveByPrime(final long maximum) {
		List<Long> primes = Sieve.listPrimes(maximum);
		long size = primes.size();
		System.out.println("Scanning " + size + " primes.");
		for (int index = Long.valueOf(size - 1).intValue(); index >= 0; --index) {
			if (pandigital(primes.get(index))) {
				System.out.println("Largest pandigital prime is " + primes.get(index));
				return;
			}
		}
		System.out.println("There is no pandigital prime until " + maximum);
	}
	
	public static boolean pandigital(final Long number) {
	   long digits = number.toString().length();
	   Set<Long> pan = new HashSet<Long>();
	   for (long digit = 1; digit <= digits; ++digit) {
		   pan.add(digit);
	   }
	   
	   digits = number;
	   while (digits > 0) {
		   if (!pan.remove(digits % 10)) return false;
		   digits /= 10;
	   }
	   
	   return pan.isEmpty();
	}
}
