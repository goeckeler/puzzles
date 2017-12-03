package euler.p096;


public class Grid
  implements Cloneable
{
  public static final int SIZE = 9;
  private final int[][] grid;

  public Grid() {
    grid = new int[SIZE][SIZE];
  }

  public Pair<Integer, Integer> nextEmptyCell() {
    for (int row = 0; row < SIZE; ++row) {
      for (int col = 0; col < SIZE; ++col) {
        if (grid[row][col] == 0) { return new Pair<Integer, Integer>(row, col); }
      }
    }
    return null;
  }

  public boolean isEmpty() {
    for (int row = 0; row < SIZE; ++row) {
      for (int col = 0; col < SIZE; ++col) {
        if (grid[row][col] > 0) return false;
      }
    }
    return true;
  }

  public boolean isFull() {
    for (int row = 0; row < SIZE; ++row) {
      for (int col = 0; col < SIZE; ++col) {
        if (grid[row][col] == 0) return false;
      }
    }
    return true;
  }

  public boolean canSet(final int row, final int col, final int digit) {
    // digit on same column?
    for (int column = 0; column < SIZE; ++column) {
      if (column != col) {
        if (grid[row][column] == digit) return false;
      }
    }

    // digit on same row?
    for (int line = 0; line < SIZE; ++line) {
      if (line != row) {
        if (grid[line][col] == digit) return false;
      }
    }

    // digit in same square?
    int x = col - col % 3;
    int y = row - row % 3;

    for (int squareX = 0; squareX < 3; ++squareX) {
      for (int squareY = 0; squareY < 3; ++squareY) {
        if (x + squareX != col && y + squareY != row) {
          if (grid[squareY + y][squareX + x] == digit) return false;
        }
      }
    }

    return true;
  }

  @Override
  public Grid clone() {
    Grid copy = new Grid();

    for (int row = 0; row < SIZE; ++row) {
      for (int col = 0; col < SIZE; ++col) {
        copy.grid[row][col] = grid[row][col];
      }
    }

    return copy;
  }

  @Override
  public String toString() {
    StringBuilder text = new StringBuilder();
    for (int row = 0; row < SIZE; ++row) {
      for (int col = 0; col < SIZE; ++col) {
        text.append(grid[row][col]);
        if (col + 1 % 3 == 0) text.append("|");
      }
      text.append("\n");
      if (row + 1 % 3 == 0 && row < SIZE - 1) text.append("---+---+---");
    }

    return text.toString();
  }

  public Grid set(final int row, final int col, final int digit) {
    grid[row][col] = digit;
    return this;
  }

  public Grid clear(final int row, final int col) {
    grid[row][col] = 0;
    return this;
  }

  public int value(final int row, final int col) {
    return grid[row][col];
  }

  public Grid set(final int row, final String digits) {
    for (int c = 0; c < Math.min(SIZE, digits.length()); ++c) {
      set(row, c, Integer.valueOf(digits.substring(c, c + 1)));
    }
    return this;
  }

  public void clear() {
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        grid[r][c] = 0;
      }
    }
  }
}
