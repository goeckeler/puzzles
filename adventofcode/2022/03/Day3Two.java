import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day3Two {
    public static void main(String[] args) {
        System.out.println("Advent of Code, Day #3.");
        System.out.println("Find badges in every three lines.");

        String line;
        long score = 0;

        String[] lines = new String[3];
        int row = 0;

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
          while ((line = console.readLine()) != null) {
            lines[row % 3] = line;
            ++row;

            if (row % 3 == 0) {
              List<Character> l1 = new ArrayList<>(lines[0].chars().mapToObj( it -> (char) it).toList());
              List<Character> l2 = new ArrayList<>(lines[1].chars().mapToObj( it -> (char) it).toList());
              List<Character> l3 = new ArrayList<>(lines[2].chars().mapToObj( it -> (char) it).toList());

              l1.retainAll(l2);
              l1.retainAll(l3);
              if (!l1.isEmpty()) {
                Character badge = l1.get(0);
                score += priority(badge);
                // System.out.printf("Character %s (%d)\n", badge, priority(badge));
              } 
            }
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