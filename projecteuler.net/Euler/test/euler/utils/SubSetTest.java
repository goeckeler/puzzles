package euler.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class SubSetTest
{
  @Test
  public void testList() {
    String[] alphabet = { "a", "b", "c", "d" };
    List<String> list = Arrays.asList(alphabet);
    assertNotNull(list);
    SubSet<String> set = new SubSet<String>(list, 2);
    List<Set<String>> subsets = set.list();
    assertNotNull(subsets);
    assertFalse(subsets.isEmpty());
    assertEquals(6, subsets.size());
    
    for (Set<String> subset : subsets) {
      assertNotNull(subset);
      assertFalse(subset.isEmpty());
      assertEquals(2, subset.size());
      System.out.println(subset.toString());
      assertFalse(subset.toArray()[0].equals(subset.toArray()[1]));
    }
  }
}
