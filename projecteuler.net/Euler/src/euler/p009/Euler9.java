package euler.p009;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for
 * which, a^(2) + b^(2) = c^(2)
 * 
 * For example, 3^(2) + 4^(2) = 9 + 16 = 25 = 5^(2).
 * 
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000. Find
 * the product abc.
 */
public class Euler9 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a, b, c = 500;

		while (c > 334) {
			for (b = c - 1; b > c / 2; --b) {
				a = 1000 - c - b;
				if (a < 1)
					continue;
				if (a > b)
					break;

				if (a * a + b * b == c * c) {
					System.out.println("a = " + a + ", b = " + b + ", c = " + c
							+ ", product is " + a * b * c);
					return;
				}
			}
			--c;
		}

		System.out.println("Assumptions where wrong, no solution found.");
	}
}
