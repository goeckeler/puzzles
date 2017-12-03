package euler.p011;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * What is the greatest product of four adjacent numbers in any direction (up, down, left, right, or diagonally) in the
 * 20*20 grid?
 */
public class Euler11
{
  public static void main(final String[] args)
    throws FileNotFoundException, IOException
  {
    String[] files = { "p011.square.txt"
    };

    for (String file : files) {
      List<List<Integer>> square = new ArrayList<List<Integer>>(100);

      // read data in
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String level;
      while ((level = reader.readLine()) != null) {
        String[] items = level.split(" ");
        List<Integer> entries = new ArrayList<Integer>(items.length);
        for (int index = 0; index < items.length; ++index) {
          entries.add(Integer.valueOf(items[index]));
        }
        square.add(entries);
      }
      reader.close();

      // try simple approach
      System.out.println("Solving \"" + file + "\".");
      solve(square);
    }
  }

  private static void solve(final List<List<Integer>> square) {
    // brute force, first horizontal, then vertical, and left down, right down
    int count = 4;
    int largest = 0;

    // horizontal
    for (int y = 0; y < rows(square); ++y) {
      for (int x = 0; x < cols(square) - count; ++x) {
        int prod = val(x, y, square) * val(x + 1, y, square) * val(x + 2, y, square) * val(x + 3, y, square);
        if (prod > largest) largest = prod;
      }
    }

    // vertical
    for (int x = 0; x < cols(square); ++x) {
      for (int y = 0; y < rows(square) - count; ++y) {
        int prod = val(x, y, square) * val(x, y + 1, square) * val(x, y + 2, square) * val(x, y + 3, square);
        if (prod > largest) largest = prod;
      }
    }

    // right down
    for (int x = 0; x < cols(square) - count; ++x) {
      for (int y = 0; y < rows(square) - count; ++y) {
        int prod = val(x, y, square) * val(x + 1, y + 1, square) * val(x + 2, y + 2, square) * val(x + 3, y + 3, square);
        if (prod > largest) largest = prod;
      }
    }

    // left down
    for (int x = count - 1; x < cols(square); ++x) {
      for (int y = 0; y < rows(square) - count; ++y) {
        int prod = val(x, y, square) * val(x - 1, y + 1, square) * val(x - 2, y + 2, square) * val(x - 3, y + 3, square);
        if (prod > largest) largest = prod;
      }
    }

    System.out.println("Largest product is " + largest);
  }

  private static int cols(final List<List<Integer>> square) {
    return square.get(0).size();
  }

  private static int rows(final List<List<Integer>> square) {
    return square.size();
  }

  private static int val(final int col, final int row, final List<List<Integer>> square) {
    return square.get(row).get(col);
  }
}
