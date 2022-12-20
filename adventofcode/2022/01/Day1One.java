import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day1One {
    public static void main(String[] args) {
        System.out.println("Advent of Code, Day #1.");
        System.out.println("Find the elf with the most load.");

        String line;
        long max = 0;
        long current = 0;

        int elf = 1;
        int maxElf = 0;

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
          while ((line = console.readLine()) != null) {
              if (line.isEmpty()) {
                // next elf
                if (current > max) {
                    max = current;
                    maxElf = elf;
                }
                current = 0;
                ++elf;
              } else {
                // add to current elf
                current += Long.valueOf(line);
              }
          }
        } catch (IOException ioex) {
            System.err.println("Cannot read from standard input.");
        }

        // the last elf might be the one with the heaviest load
        if (current > max) {
            max = current;
            maxElf = elf;
        }

        System.out.printf("Elf #%d carries the heaviest load with %d calories.\n", maxElf, max);
    }
}
