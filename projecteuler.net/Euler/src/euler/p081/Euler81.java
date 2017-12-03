package euler.p081;

import java.io.*;
import java.util.*;

/**
 * Find the minimal path sum, in matrix.txt, a text file containing a 80 by 80 matrix, from the top left to the bottom
 * right by only moving right and down.
 */
public class Euler81
{
  static long smallest = Integer.MAX_VALUE;

  public static void main(final String[] args)
    throws FileNotFoundException, IOException
  {
    String[] files = { "p081.easy.txt", "p081.matrix.txt"
    };

    for (String file : files) {
      List<List<Integer>> square = new ArrayList<List<Integer>>(100);

      // easy.txt yields 2427
      // read data in
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String level;
      while ((level = reader.readLine()) != null) {
        String[] items = level.split(",");
        List<Integer> entries = new ArrayList<Integer>(items.length);
        for (int index = 0; index < items.length; ++index) {
          entries.add(Integer.valueOf(items[index]));
        }
        square.add(entries);
      }
      reader.close();

      System.out.println("Solving \"" + file + "\".");

      // try brute force
      /*
       * smallest = Integer.MAX_VALUE; solve(square, 0, 0, 0); System.out.println("Lowest sum is " + smallest);
       */
      // try pruning
      // solveCostBased(square);
      
      // separate in two triangles that meet in the middle
      solveBottomUp(new Matrix(square));      
    }
  }

  private static void solveBottomUp(final Matrix square) {
    int maxX = square.size() - 1;
    int maxY = square.size() - 1;
    int levels = maxX + maxY; // zero-based level
    int left;
    int right;
    
    // regard the square as a triangle!
    for (int level = levels - 1; level >= 0; --level) {
      int x = Math.max(level - maxX, 0);
      int y = Math.min(level, maxY);
      int endX = y;      
      while (x <= endX) {
        // left neighbor (one line down)
        if (y == maxY) {
          left = Integer.MAX_VALUE;
        } else {
          left = square.value()[x][y + 1];
        }
        // right neighbor (one cell to the right)
        if (x == maxX) {
          right = Integer.MAX_VALUE;
        } else {
          right = square.value()[x + 1][y];
        }
        // add the lower number of the right or left neighbor in the next level
        square.value()[x][y] += Math.min(right, left);
        
        // crawl on the diagonal
        ++x;
        --y;
      }
    }
    
    // solution is in (0, 0):
    System.out.println("Minimum path is " + square.value()[0][0]);
  }
  
  public static void solve(final List<List<Integer>> square, final int x, final int y, long sum) {
    sum += val(x, y, square);

    if (x == cols(square) - 1 && y == rows(square) - 1) {
      if (sum < smallest) {
        smallest = sum;
        System.out.println("Lowered to " + smallest);
      }

      return;
    }

    if (x < cols(square) - 1) {
      // try going left
      solve(square, x + 1, y, sum);
    }

    if (y < rows(square) - 1) {
      // try going down
      solve(square, x, y + 1, sum);
    }
  }

  public static void solveCostBased(final List<List<Integer>> square) {
    // start with left and down
    long sum = val(0, 0, square);
    SortedSet<Item> set = new TreeSet<Item>();
    Item left = new Item(1, 0, sum + val(1, 0, square));
    Item down = new Item(0, 1, sum + val(0, 1, square));

    set.add(left);
    set.add(down);

    while (true) {
      // let's start searching
      Item lowest = set.first();

      if (lowest.x == cols(square) - 1 && lowest.y == rows(square) - 1) {
        System.out.println("Lowest sum is " + lowest.value);
        return;
      }

      set.remove(lowest);
      if (canAdd(lowest.x + 1, lowest.y, set, square)) {
        set.add(new Item(lowest.x + 1, lowest.y, lowest.value + val(lowest.x + 1, lowest.y, square)));
      }

      if (canAdd(lowest.x, lowest.y + 1, set, square)) {
        set.add(new Item(lowest.x, lowest.y + 1, lowest.value + val(lowest.x, lowest.y + 1, square)));
      }
    }
  }

  private static boolean canAdd(final int x, final int y, final SortedSet<Item> set, final List<List<Integer>> square) {
    if (x >= cols(square) || y >= rows(square)) return false;

    // // if there is any entry more progressed then don't add this one
    // for (Item item : set) {
    // if ((item.x == x || item.x == x + 1) && (item.y == y || item.y == y + 1)) return false;
    // }

    // if all entries are more progressed then don't add this one
    for (Item item : set) {
      if ((item.x < x || item.y < y)) return true;
    }
    return false;
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

class Matrix 
{
  private int[][] matrix;
  
  public Matrix(final List<List<Integer>> square) {
    matrix = new int[square.size()][square.size()];
    for (int row = 0; row < square.size(); ++row) {
      for (int col = 0; col < square.size(); ++col) {
        matrix[col][row] = square.get(row).get(col);
      }
    }
  }
  
  public int size() {
    return matrix.length;
  }
  
  public int[][] value() {
    return matrix;
  }
}

class Item
  implements Comparable<Item>
{
  int x, y;
  long value;

  public Item(final int x, final int y, final long value) {
    this.x = x;
    this.y = y;
    this.value = value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Item other = (Item) obj;
    if (x != other.x) return false;
    if (y != other.y) return false;
    return true;
  }

  @Override
  public int compareTo(final Item other) {
    if (other == null) return 1;
    if (this == other) return 0;

    return this.value > other.value ? 1 : -1;
  }
}
