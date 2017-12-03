package euler.p017;

public class SpellNumber
{
  private static final String[] SMALLS =
      {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
        "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
      };

  private static final String[] TENS =
      { "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
      };

  public static String spell(final int number) {
    if (number < 100) return spell2(number);
    if (number < 1000) return spell3(number);

    if (number >= 1000 && number < 1000000) {
      int prefix = number / 1000;
      int suffix = number % 1000;

      StringBuilder spell = new StringBuilder();
      spell.append(spell3(prefix));
      spell.append(" thousand ");
      if (suffix > 0) {
        spell.append(spell(suffix));
      }

      return spell.toString();
    }
    return "";
  }

  private static String spell2(final int number) {
    if (number >= 100) return "";
    if (number < 20) return SMALLS[number];
    StringBuilder spell = new StringBuilder(TENS[number / 10]);
    int remainder = number % 10;
    if (remainder > 0) {
      spell.append(" ");
      spell.append(SMALLS[remainder]);
    }
    return spell.toString();
  }

  private static String spell3(final int number) {
    if (number >= 10000) return "";
    if (number < 100) return spell2(number);
    int prefix = number / 100;
    int suffix = number % 100;

    StringBuilder spell = new StringBuilder();
    spell.append(spell2(prefix));
    spell.append(" hundred");
    if (suffix > 0) {
      spell.append(" and ");
      spell.append(spell2(suffix));
    }

    return spell.toString();
  }
}
