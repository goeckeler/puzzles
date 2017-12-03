package euler.primes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple prime factors computation
 */
public class Factors
{
  private long maximum;
  private Sieve sieve;
  private List<Long> primes;
  private long number;
  List<Long> factors = null;
  private long lastNumber;
  
  public Factors(final long maximum) {
    this.maximum = maximum;
    this.number = 0;
    this.lastNumber = -1;
    sieve = new Sieve(maximum);
    primes = sieve.list();
  }

  public long getNumber() {
    return number;
  }
  
  public void setNumber(final long number) {
    this.number = number;
  }
  
  public List<Long> primeFactors() {
    if (number == lastNumber && factors != null) return factors;

    lastNumber = number;
    long value = Math.min(number, maximum);
    factors = new ArrayList<Long>();

    if (sieve.isPrime(value)) {
      factors.add(value);
    } else {
      int index = 0;
      while (value > 1) {
        long prime = primes.get(index);
        if (value % prime == 0) {
          factors.add(prime);
          value /= prime;
        } else {
          ++index;
        }
      }
    }
    return factors;
  }
  
  public List<Long> primeFactors(final long number) {
    setNumber(number);
    return primeFactors();
  }
  
  public long divisors() {
    if (factors == null || number != lastNumber) primeFactors();
    Map<Long, Integer> dictionary = new HashMap<Long, Integer>(101);
    
    for (Long factor : factors) {
      Integer count = dictionary.get(factor);
      if (count == null) {
        dictionary.put(factor, 1);
      } else {
        dictionary.put(factor, count + 1);
      }
    }
 
    long divisors = 1;
    for (Integer exponent : dictionary.values()) {
      if (exponent != null && exponent > 0) {
        divisors *= (exponent + 1);
      }
    }
    
    return divisors;
  }
}
