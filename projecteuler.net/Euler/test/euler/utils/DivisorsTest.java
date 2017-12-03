package euler.utils;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DivisorsTest {

	@Test
	public void testDivisors() {
		List<Long> divisors = Divisors.divisors(1L);
        assertNotNull(divisors);
        assertEquals(1, divisors.size());
        
        divisors = Divisors.divisors(2L);
        assertEquals(1, divisors.size());
       
        divisors = Divisors.divisors(6L);
        assertEquals(3, divisors.size());
        assertArrayEquals(new Long[] { 1L, 2L, 3L }, divisors.toArray());
        
        divisors = Divisors.divisors(7);
        assertEquals(1, divisors.size());
        assertArrayEquals(new Long[] { 1L }, divisors.toArray());
        
        divisors = Divisors.divisors(12);
        assertEquals(5, divisors.size());
        assertArrayEquals(new Long[] { 1L, 2L, 3L, 4L, 6L }, divisors.toArray());
	}

	@Test
	public void testDivisorTime() {
		List<Long> divisors = Divisors.divisors(10000L);
		assertNotNull(divisors);
	}
}
