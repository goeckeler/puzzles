package euler.p096;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Euler96
{
  public static void main(String[] args) throws FileNotFoundException, IOException {
    String[] files = { "p096.sudoku.txt"
    };

    for (String file : files) {
      System.out.println("Solving \"" + file + "\".");
      // read data in
      long sum = 0;
      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line = null;
      do {
        line = reader.readLine();
        if (line != null && line.toLowerCase().startsWith("grid")) {
          Sudoku sudoku = new Sudoku();
          for (int row = 0; row < Grid.SIZE; ++row) {
            line = reader.readLine();
            sudoku.set(row, line);
          }
          System.out.println("\n=SUDOKU==\n");
          System.out.println(sudoku.toString());
          System.out.println("=========\n");
          List<Grid> solutions = sudoku.solve();
          if (solutions == null || solutions.size() != 1) {
            System.out.println("None or more than one solution!");
            System.exit(1);
          }
          Grid solution = solutions.get(0);
          System.out.println(solution.toString());
          
          long value = solution.value(0, 0) * 100 + solution.value(0, 1) * 10 + solution.value(0, 2);
          sum += value;
        }
      } while (line != null);
      reader.close();
      System.out.print("\nThree digit sum is " + sum);
    }
  }
}
