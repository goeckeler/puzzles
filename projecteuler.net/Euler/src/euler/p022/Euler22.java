package euler.p022;

import java.io.*;
import java.util.*;

/**
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first
 * names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply
 * this value by its alphabetical position in the list to obtain a name score.
 *
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the
 * 938th name in the list. So, COLIN would obtain a score of 938 � 53 = 49714.
 *
 * What is the total of all the name scores in the file?
 */
public class Euler22
{

  /**
   * @param args
   */
  public static void main(final String[] args) throws FileNotFoundException, IOException {
    BufferedReader reader = new BufferedReader(new FileReader("p022.names.txt"));
    String namesInFile = reader.readLine();
    reader.close();

    List<String> names = new ArrayList<String>(800);
    for (String name : namesInFile.split(",")) {
      // remove ""
      names.add(name.substring(1, name.length() - 1));
    }
    Collections.sort(names);

    long line = 0;
    long score = 0L;
    for (String name : names) {
      ++line;
      long nameScore = 0;
      for (int index = 0; index < name.length(); ++index) {
        nameScore += name.charAt(index) - 64;
      }
      score += line * nameScore;
    }

    System.out.println("Name score is " + score);
  }

}
