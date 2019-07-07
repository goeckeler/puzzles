package euler.p096;

import java.util.List;

/**
 * Solve all fifty Sudoku puzzles. Find the sum of the 3-digit numbers found in the top left corner of each solution
 * grid; for example, 483 is the 3-digit number found in the top left corner of the first grid above.
 */
public class TestSudoku
{
  public static final String[] test = {
    "003020600",
    "900305001",
    "001806400",

    "008102900",
    "700000008",
    "006708200",

    "002609500",
    "800203009",
    "005010300"
  };

  public static final String[] pressewoche = {
    "530001000",
    "900040000",
    "080009700",

    "050010006",
    "706050308",
    "300080040",

    "003200060",
    "000030002",
    "000700093"
  };

  public static final String[] multipleSolutions = {
    "007060002",
    "080900034",
    "010050900",

    "050010600",
    "040008070",
    "002390080",

    "006040010",
    "500007040",
    "400030700"
  };

  public static void main(final String[] args) {
    show(test);
    show(pressewoche);
    show(multipleSolutions);
  }

  public static void show(final String[] puzzle) {
    final Sudoku sudoku = new Sudoku();
    for (int row = 0; row < puzzle.length; ++row) {
      sudoku.set(row, puzzle[row]);
    }

    System.out.println("\n=SUDOKU==\n");
    System.out.println(sudoku.toString());
    System.out.println("=========\n");
    final List<Grid> solved = sudoku.solve();
    for (final Grid grid : solved) {
      System.out.println(grid.toString());
    }
  }
}
