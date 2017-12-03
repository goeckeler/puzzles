package euler.p042;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The n-th term of the sequence of triangle numbers is given by, t(n) = n(n+1)/2; so the first ten triangle numbers
 * are:
 * 
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * 
 * By converting each letter in a word to a number corresponding to its alphabetical position and adding these values we
 * form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t(10). If the word value is a triangle
 * number then we shall call the word a triangle word.
 * 
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common
 * English words, how many are triangle words?
 */
public class Euler42
{
  public static void main(final String[] args)
    throws FileNotFoundException, IOException
  {
    String[] files = { "p042.words.txt"
    };

    for (String file : files) {
      Map<String, Integer> words = new HashMap<String, Integer>(2101);

      // read data in
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String level;
      while ((level = reader.readLine()) != null) {
        String[] items = level.split(",");

        for (int index = 0; index < items.length; ++index) {
          String word = items[index].substring(1, items[index].length() - 1);
          words.put(word, wordValue(word));
        }
      }
      reader.close();

      System.out.println("Solving \"" + file + "\".");

      // what is n of the highest possible triangle word?
      int top = top(words);
      int max = Long.valueOf(Math.round((Math.sqrt(1 + 8 * top) - 1) / 2.0)).intValue();
      
      // create all respective triangle numbers (f(n))
      Set<Integer> triangles = new HashSet<Integer>(max);
      for (int n = 1; n <= max; ++n) {
        triangles.add(n * (n + 1) / 2);
      }
      
      int count = 0;
      for (Integer wordValue : words.values()) {
        if (triangles.contains(wordValue)) ++count;
      }
      
      System.out.println("There are " + count + " triangle words.");
    }
  }

  private static int top(final Map<String, Integer> words) {
    int highest = 0;
    for (Integer value : words.values()) {
      if (value > highest) highest = value;
    }

    System.out.println("Highest word value is " + highest);
    return highest;
  }

  private static int wordValue(final String word) {
    int sum = 0;
    for (int index = 0; index < word.length(); ++index) {
      sum += word.charAt(index) - 64;
    }
    return sum;
  }
}
