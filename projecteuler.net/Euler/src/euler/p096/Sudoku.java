package euler.p096;

import java.util.*;

public class Sudoku
{
  private final Grid puzzle;

  public Sudoku() {
    puzzle = new Grid();
  }

  public Sudoku set(final int row, final int col, final int digit) {
    puzzle.set(row, col, digit);
    return this;
  }

  public Sudoku set(final int row, final String digits) {
    for (int c = 0; c < Math.min(Grid.SIZE, digits.length()); ++c) {
      set(row, c, Integer.valueOf(digits.substring(c, c + 1)));
    }
    return this;
  }

  public List<Grid> solve() {
    return solve(puzzle.clone());
  }

  private List<Grid> solve(final Grid grid) {
    Pair<Integer, Integer> cell = grid.nextEmptyCell();
    if (cell == null) {
      // found another solution
      return Collections.singletonList(grid.clone());
    }

    List<Grid> solutions = new ArrayList<Grid>();
    for (int digit=1; digit <= Grid.SIZE; ++digit) {
      if (grid.canSet(cell.getKey(), cell.getValue(), digit)) {
        grid.set(cell.getKey(), cell.getValue(), digit);
        solutions.addAll(solve(grid));
        grid.clear(cell.getKey(), cell.getValue());
      }
    }

    return solutions;
  }

  @Override
  public String toString() {
    return puzzle.toString();
  }
}

