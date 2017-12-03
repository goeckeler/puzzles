package euler.p023;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import euler.utils.Divisors;

/**
 * Sum of integers that cannot be written as the sum of two abundant numbers
 * 
 * All integers above 28123 can be certainly written as the sum of two abundant
 * numbers.
 * 
 * 12 is the smallest abundant number, the lowest sum is thus 24.
 * 
 * @author Thorsten Göckeler
 */
public class Euler023 {
    public static int MAX_INTEGER = 28123;
    public static int MIN_ABUNDANT = 12;
    
	/**
	 * slow but sufficient, takes about 3 minutes
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting to find all non-abundant sums ...");
		// how many abundants numbers are we talking about?
		List<Integer> abundants = abundants(MAX_INTEGER);
		System.out.println("There are " + abundants.size() + " abundant numbers.");
        boolean sums[] = new boolean[MAX_INTEGER + 1];
        // mark all entries as being a sum not to be made of abundant numbers
        Arrays.fill(sums, true);
		System.out.println("Marking sums of abundants numbers ...");
		for (int lower = 0; lower < abundants.size(); ++lower) {
			System.out.println(">> Starting with #" + lower + "(" + abundants.get(lower) + ")");
			// if twice the lower bound is already larger than the maximum number we are done anyway
			if (abundants.get(lower) * 2 > MAX_INTEGER) break;
			for (int upper = lower; upper < abundants.size(); ++upper) {
				int aLower = abundants.get(lower);
				int aUpper = abundants.get(upper);
				int suma = aLower + aUpper;
				// if the sum is already larger that our maximum number, then continue with next lower border
				if (suma > MAX_INTEGER) break;
				sums[suma] = false;
			}
		}
		System.out.println("Here are all the numbers that cannot be made a sum of abundant numbers ...");
		long sum = 0;
		for (int i = 0; i <= MAX_INTEGER; ++i) {
			if (sums[i]) {
				System.out.println(i);
				sum += i;
			}
		}
		System.out.println("Sum of all non-abundant sums is " + sum);
	}
	
	public static List<Integer> abundants(int maximum) {
		LinkedList<Integer> abundants = new LinkedList<Integer>();
		int start = Math.min(MIN_ABUNDANT, maximum);
		for (int number = start; number <= maximum; ++number) {
		    if (isAbundant(number)) {
		    	abundants.add(number);
		    }
		}
		return abundants;
	}

	public static boolean isAbundant(long number) {
		long sum = 0;
		for (Long divisor : Divisors.divisors(number)) {
			sum += divisor;
			if (sum > number) return true;
		}
		
		return sum > number;
	}
}
