import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day3One {
    public static void main(String[] args) {
        System.out.println("Advent of Code, Day #3.");
        System.out.println("Find doubled item in rucksacks.");

        String line;
        long score = 0;

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
          while ((line = console.readLine()) != null) {
            int size = line.length() >> 1;
            List<Character> l = new ArrayList<>(line.substring(0, size).chars().mapToObj( it -> (char) it).toList());
            List<Character> r = line.substring(size).chars().mapToObj( it -> (char) it).toList();
            
            l.retainAll(r);
            if (!l.isEmpty()) score += priority(l.get(0));

            // System.out.printf("Character %s (%d) included in %s.\n", l.get(0), priority(l.get(0)), line);
          }
        } catch (IOException ioex) {
            System.err.println("Cannot read from standard input.");
        }

        System.out.printf("You score %d points.\n", score);
    }

    public static int priority(final Character character) {
      return Character.isLowerCase(character) ? character - 'a' + 1 : character - 'A' + 27;
    }
}
