package euler.p099;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * Comparing two numbers written in index form like 2^11 and 3^7 is not difficult, as any calculator would confirm that
 * 2^11 = 2048 < 3^7 = 2187.
 * 
 * However, confirming that 632382^518061 > 519432^525806 would be much more difficult, as both numbers contain over
 * three million digits.
 * 
 * Using base_exp.txt (right click and 'Save Link/Target As...'), a 22K text file containing one thousand lines with a
 * base/exponent pair on each line, determine which line number has the greatest numerical value.
 * 
 * NOTE: The first two lines in the file represent the numbers in the example given above.
 */
public class LargestExponent
{
  /**
   * @param args
   */
  public static void main(String[] args)
    throws FileNotFoundException, IOException
  {
    double acut = cut(632382, 518061);
    double bcut = cut(519432, 525806);

    System.out.print("632382^518061 is ");
    if (acut > bcut) {
      System.out.print("greater");
    } else {
      System.out.print("less");
    }
    System.out.println(" than 519432^525806");

    LineNumberReader reader = new LineNumberReader(new FileReader("p099.base_exp.txt"));
    reader.setLineNumber(1);

    String line;
    double largest = 0.0;
    int largestExponent = 0;
    int largestBase = 0;
    int lineNo = 0;

    while ((line = reader.readLine()) != null) {
      String[] number = line.split(",");
      int base = Integer.parseInt(number[0]);
      int exponent = Integer.parseInt(number[1]);

      double cut = cut(base, exponent);
      if (cut > largest) {
        largest = cut;
        largestBase = base;
        largestExponent = exponent;
        lineNo = reader.getLineNumber();
      }
    }

    reader.close();

    System.out.println("Largest value is " + largestBase + "^" + largestExponent + " in line " + lineNo);
  }

  public static double cut(long base, long exponent) {
    return exponent * Math.log10(base);
  }
}
