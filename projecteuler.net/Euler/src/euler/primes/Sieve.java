package euler.primes;

import java.util.*;

/** Span a sieve of prime numbers up to a maximum */
public class Sieve
  implements Iterable<Long>
{
  private final BitSet sieve;
  private final long maximum;
  private boolean computed;

  /**
   * Create a sieve up to and including the maximum number
   *
   * The sieve will be initialized lazily. So all the primes will be computed only when you request a list of primes.
   *
   * @param maximum the last number to be included in the sieve, up to MAX_INTEGER.
   */
  public Sieve(final long maximum) {
    this.maximum = maximum;
    sieve = new BitSet(Long.valueOf(maximum / 2 + 1).intValue());
    computed = false;
  }

  public long getMaximum() {
    return maximum;
  }

  private final boolean isComposite(final long number) {
    assert number >= 3 && (number % 2) == 1;
    return sieve.get(Long.valueOf((number - 3) / 2).intValue());
  }

  public final boolean isPrime(final long number) {
    compute();
    if (number == 2) return true;
    if (number == 1 || (number % 2 == 0)) return false;
    return !isComposite(number);
  }

  private final void setComposite(final long number) {
    assert number >= 3 && (number % 2) == 1;
    sieve.set(Long.valueOf((number - 3) / 2).intValue());
  }

  private final void compute() {
    if (computed) return;

    for (long number = 3; number * number <= getMaximum(); number += 2) {
      if (isComposite(number)) continue;

      // We increment by 2*i to skip even multiples of i
      for (long multiple = number * number; multiple <= getMaximum(); multiple += 2 * number) {
        setComposite(multiple);
      }
    }

    computed = true;
  }

  public Iterator<Long> iterator() {
    return new SieveIterator(this);
  }

  /** @return the number of primes within this range */
  public int count() {
    int count = 0;
    for (Iterator<Long> it = iterator(); it.hasNext(); it.next()) {
      ++count;
    }
    return count;
  }

  public List<Long> list() {
    List<Long> primes = new ArrayList<Long>(count());
    for (Long prime : this) {
      primes.add(prime);
    }
    return primes;
  }

  public static List<Long> listPrimes(final long max) {
    Sieve sieve = new Sieve(max);
    return sieve.list();
  }
}

class SieveIterator
  implements Iterator<Long>
{
  private long next;
  private final Sieve sieve;

  public SieveIterator(final Sieve sieve) {
    this.sieve = sieve;
    next = 2;
  }

  @Override
  public boolean hasNext() {
    return next > 0;
  }

  @Override
  public Long next() {
    long nextPrime = next;

    if (next == 2) {
      next = 3;
    } else {
      for (long number = next + 2; number <= sieve.getMaximum(); number += 2) {
        if (sieve.isPrime(number)) {
          next = number;
          break;
        }
      }
    }

    // check if we found one more prime
    if (next == nextPrime) {
      next = -1;
    }

    return nextPrime;
  }

  @Override
  public void remove() {
    // forget it
    throw new IllegalStateException();
  }
}
