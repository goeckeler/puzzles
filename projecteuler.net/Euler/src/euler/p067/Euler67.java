package euler.p067;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from
 * top to bottom is 23.
 *
 * <pre>
 *     3
 *    7 4
 *   2 4 6
 *  8 5 9 3
 * </pre>
 *
 * That is, 3 + 7 + 4 + 9 = 23.
 *
 * Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'), a 15K text file
 * containing a triangle with one-hundred rows.
 */
public class Euler67
{
  public static void main(final String[] args)
    throws FileNotFoundException, IOException
  {
    String[] files = { "p067.easy.txt", "p067.18.txt", "p067.triangle.txt"
    };

    for (String file : files) {
      List<List<Integer>> triangle = new ArrayList<List<Integer>>(100);

      // 18.txt yields 1074, easy.txt yields 23
      // read data in
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String level;
      while ((level = reader.readLine()) != null) {
        String[] items = level.split(" ");
        List<Integer> entries = new ArrayList<Integer>(items.length);
        for (int index = 0; index < items.length; ++index) {
          entries.add(Integer.valueOf(items[index]));
        }
        triangle.add(entries);
      }
      reader.close();

      // try simple approach
      System.out.println("Solving \"" + file + "\".");
      solve(triangle);
      solve2(triangle);
    }
  }

  private static void solve(final List<List<Integer>> triangle) {
    /*
     * simple algorithm: if there is only one line left, pick the maximum value and you are done, otherwise add the left
     * number plus the next left number add the left number plus the next middle number add the right number plus the
     * next middle number add the right number plus the next right number Take either the left or right depending on the
     * larger sum.
     */

    int level = 0;
    int index = 0;
    int lastLevel = triangle.size() - 1;
    long sum = triangle.get(level).get(index);

    while (level < lastLevel) {
      ++level;
      if (level == lastLevel) {
        // only need to pick the largest available number
        int pickL = triangle.get(level).get(index);
        int pickR = triangle.get(level).get(index + 1);

        sum += Math.max(pickL, pickR);
      } else if (level == lastLevel - 1) {
        // apply simple algorithm
        int pickL = triangle.get(level).get(index);
        int pickR = triangle.get(level).get(index + 1);

        int candL = triangle.get(level + 1).get(index);
        int candM = triangle.get(level + 1).get(index + 1);
        int candR = triangle.get(level + 1).get(index + 2);

        int left = Math.max(pickL + candL, pickL + candM);
        int right = Math.max(pickR + candR, pickR + candM);

        if (right == left) {
          System.out.println("Level #" + level + ", index #" + index + " no hint!");
        }

        if (right > left) {
          sum += pickR;
          ++index; // move right
        } else {
          sum += pickL; // move left
        }
      } else {
        // apply three level estimate
        int pickL = triangle.get(level).get(index);
        int pickR = triangle.get(level).get(index + 1);

        int candL = triangle.get(level + 1).get(index);
        int candM = triangle.get(level + 1).get(index + 1);
        int candR = triangle.get(level + 1).get(index + 2);

        int cll = triangle.get(level + 2).get(index);
        int clr = triangle.get(level + 2).get(index + 1);
        int cml = clr;
        int cmr = triangle.get(level + 2).get(index + 2);

        int crl = cmr;
        int crr = triangle.get(level + 2).get(index + 3);

        int left = Math.max(pickL + candL + Math.max(cll, clr), pickL + candM + Math.max(cml, cmr));
        int right = Math.max(pickR + candR + Math.max(crl, crr), pickR + candM + Math.max(cml, cmr));

        if (right == left) {
          System.out.println("Level #" + level + ", index #" + index + " no hint!");
        }

        if (right > left) {
          sum += pickR;
          ++index; // move right
        } else {
          sum += pickL; // move left
        }
      }
    }

    // 6683 for triangle.txt
    System.out.println("SUM IS " + sum);
  }

  private static void solve2(final List<List<Integer>> triangle) {
    // prune algorithm, start from the bottom
    for (int level = triangle.size() - 1; level > 0; --level) {
      for (int index = 0; index < triangle.get(level).size() - 1; ++index) {
        int value = triangle.get(level -1).get(index);
        value += Math.max(triangle.get(level).get(index), triangle.get(level).get(index + 1));
        triangle.get(level -1).set(index, value);
      }
    }

    System.out.println("PRUNING SUM IS " + triangle.get(0).get(0));
  }
}
