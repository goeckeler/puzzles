package euler.p001;

import java.text.NumberFormat;

/**
 * Add all the natural numbers below one thousand that are multiples of 3 or 5.
 * 
 * @author goeckeler
 */
public class Euler1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long sum = 0;
		for (long i = 1; i < 1000; i++) {
			if (i % 3 == 0 || i % 5 == 0) {
				sum += i;
			}
		}
		
		System.out.println(NumberFormat.getIntegerInstance().format(sum));
	}
}
