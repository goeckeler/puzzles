package euler.p017;

import static org.junit.Assert.assertEquals;

import java.text.NumberFormat;

import org.junit.Test;

import euler.p017.SpellNumber;

public class SpellNumberTest
{
  @Test
  public void testSpell() {
    assertEquals("zero", SpellNumber.spell(0));
    assertEquals("one", SpellNumber.spell(1));
    assertEquals("sixteen", SpellNumber.spell(16));
    assertEquals("fifty", SpellNumber.spell(50));
    assertEquals("fifty two", SpellNumber.spell(52));
    assertEquals("one hundred", SpellNumber.spell(100));
    assertEquals("one hundred and eighteen", SpellNumber.spell(118));
    assertEquals("three thousand nine hundred and fifteen", SpellNumber.spell(3915));
  }

  @Test
  public void testWordCount() {
    int count = 0;
    for (int number = 1; number <= 1000; ++number) {
      String spoken = SpellNumber.spell(number);
      System.out.print(spoken);

      int letters = 0;
      for (String word : spoken.split(" ")) {
        letters += word.length();
      }

      System.out.println(" (" + NumberFormat.getIntegerInstance().format(letters) + ") letters");
      count += letters;
    }

    System.out.println("That are " + NumberFormat.getIntegerInstance().format(count) + " letters.");
  }
}
