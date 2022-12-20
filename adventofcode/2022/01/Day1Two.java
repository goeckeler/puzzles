import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class Day1Two {
    public static void main(String[] args) {
        System.out.println("Advent of Code, Day #1");
        System.out.println("Find the top three loaded elves");

        String line;
        long current = 0;

        SortedSet<Long> loads = new TreeSet<>(Collections.reverseOrder());

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
          while ((line = console.readLine()) != null) {
              if (line.isEmpty()) {
                // next elf
                loads.add(current);
                current = 0;
              } else {
                // add to current elf
                current += Long.valueOf(line);
              }
          }
        } catch (IOException ioex) {
            System.err.println("Cannot read from standard input.");
        }

        // the last elf needs to be accounted for
        loads.add(current);

        Long sum = loads.stream().limit(3).mapToLong(Long::longValue).sum();
        System.out.printf("The top three loaded elves carry %d calories.\n", sum);
    }
}
