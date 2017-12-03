package euler.primes;

import static java.text.NumberFormat.getIntegerInstance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Ignore;
import org.junit.Test;

import euler.primes.Sieve;

public class SieveTest
{
  @Test
  public void testSieveOfEratosthenes() {
    List<Long> primes20 = Sieve.listPrimes(20);
    assertNotNull(primes20);
    assertEquals(8, primes20.size());
    assertTrue(primes20.contains(2L));
    assertTrue(primes20.contains(3L));
    assertTrue(primes20.contains(5L));
    assertTrue(primes20.contains(7L));
    assertTrue(primes20.contains(11L));
    assertTrue(primes20.contains(13L));
    assertTrue(primes20.contains(17L));
    assertTrue(primes20.contains(19L));
  }

  @Test
  public void testSieveIterator() {
    List<Long> primes20 = new Sieve(20).list();
    assertNotNull(primes20);
    assertEquals(8, primes20.size());
    assertTrue(primes20.contains(2L));
    assertTrue(primes20.contains(3L));
    assertTrue(primes20.contains(5L));
    assertTrue(primes20.contains(7L));
    assertTrue(primes20.contains(11L));
    assertTrue(primes20.contains(13L));
    assertTrue(primes20.contains(17L));
    assertTrue(primes20.contains(19L));
  }

  @Test
  @Ignore
  public void testMaximum() {
    int maximum = 100;
    List<Long> primes = new Sieve(maximum).list();
    System.out.println("Found " + getIntegerInstance().format(primes.size()) + " prime numbers.");
    for (Long prime : primes) {
      System.out.println(getIntegerInstance().format(prime));
    }
    System.out.println("Listed " + getIntegerInstance().format(primes.size()) + " prime numbers.");
  }

  @Test
  @Ignore
  public void testSumBelowTwoMillion() {
    int maximum = 1999999;
    long sum = 0;
    for (Long prime : new Sieve(maximum)) {
      sum += prime;
    }
    System.out.println("Sum below two million of all primes: " + getIntegerInstance().format(sum));
  }

  @Test
  @Ignore
  public void test10001stPrime() {
    int count = 10001;
    int maximum = count * 20;
    int size = 0;

    do {
      System.out.print("Searching until " + getIntegerInstance().format(maximum) + " ");
      Sieve sieve = new Sieve(maximum);
      size = sieve.count();
      System.out.println("containing " + getIntegerInstance().format(size) + " primes.");

      if (size >= count) {
        System.out.println(getIntegerInstance().format(count) + ". prime is " +
                           getIntegerInstance().format(sieve.list().get(count - 1)));
      } else {
        maximum += count;
      }
    } while (size < count);
  }

  /**
   * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves
   * prime.
   *
   * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
   *
   * How many circular primes are there below one million?
   */
  @Test
  @Ignore
  public void testCircularPrime() {
    int maximum = 999999;
    List<Long> primes = new Sieve(maximum).list();
    List<Long> circulars = new ArrayList<Long>(maximum / 5);
    System.out.println("Checking " + primes.size() + " prime numbers.");
    for (Long prime : primes) {
      if (circulars.contains(prime)) continue;

      // test if all rotations are also primes
      boolean circular = true;
      Set<Long> candidates = rotations(prime);
      for (Long candidate : candidates) {
        if (candidate.equals(prime)) continue;
        if (!primes.contains(candidate)) {
          circular = false;
          break;
        }
      }

      if (circular) {
        circulars.addAll(candidates);
      }
    }

    System.out.println("Found " + circulars.size() + " circular primes up to " + maximum);
    for (Long number : circulars) {
      System.out.println(number);
    }
    System.out.println("Found " + circulars.size() + " circular primes up to " + maximum);
  }

  @Test
  @Ignore
  public void testPermutations() {
    List<String> test = permutations("abc");

    assertNotNull(test);
    assertEquals(6, test.size());
    assertTrue(test.contains("abc"));
    assertTrue(test.contains("acb"));
    assertTrue(test.contains("bac"));
    assertTrue(test.contains("bca"));
    assertTrue(test.contains("cab"));
    assertTrue(test.contains("cba"));

    test = permutations("a");
    assertNotNull(test);
    assertEquals(1, test.size());
    assertTrue(test.contains("a"));

    test = permutations("123456");
    assertNotNull(test);
    assertEquals(720, test.size());
  }

  @Test
  public void testRotations() {
    Set<Long> numbers = rotations(197L);

    assertNotNull(numbers);
    assertEquals(3, numbers.size());
    assertTrue(numbers.contains(197L));
    assertTrue(numbers.contains(971L));
    assertTrue(numbers.contains(719L));

    numbers = rotations(2L);
    assertNotNull(numbers);
    assertEquals(1, numbers.size());
    assertTrue(numbers.contains(2L));

    numbers = rotations(21L);
    assertNotNull(numbers);
    assertEquals(2, numbers.size());
    assertTrue(numbers.contains(21L));
    assertTrue(numbers.contains(12L));
  }

  public static List<String> permutations(final String text) {
    if (text.length() <= 1) { return Collections.singletonList(text); }

    List<String> entries = null;
    for (int pos = 0; pos < text.length(); ++pos) {
      String header = text.substring(pos, pos + 1);
      String trailer = text.substring(0, pos) + text.substring(pos + 1);

      List<String> permuted = permutations(trailer);

      if (entries == null) {
        entries = new ArrayList<String>(text.length() * permuted.size());
      }

      for (String trailed : permuted) {
        StringBuilder entry = new StringBuilder(header);
        entry.append(trailed);
        entries.add(entry.toString());
      }
    }

    return entries;
  }

  public static List<Integer> permutations(final Integer number) {
    List<String> permuted = permutations(number.toString());
    List<Integer> numbers = new ArrayList<Integer>(permuted.size());
    for (String item : permuted) {
      numbers.add(Integer.valueOf(item));
    }

    permuted = null;
    return numbers;
  }

  public static Set<Long> rotations(final Long number) {
    int rotate = number.toString().length();
    Set<Long> numbers = new HashSet<Long>();

    String circular = number.toString();

    numbers.add(number);
    while (rotate > 1) {
      --rotate;
      String last = circular.substring(circular.length() - 1);
      circular = last + circular.substring(0, circular.length() - 1);
      numbers.add(Long.valueOf(circular).longValue());
    }

    return numbers;
  }

  public static boolean palindrome(final int number) {
    int reverse = Integer.valueOf(new StringBuilder().append(number).reverse().toString());
    return number == reverse;
  }
}
