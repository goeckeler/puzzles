import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4One {
    public static void main(String[] args) {
        System.out.println("Advent of Code, Day #4.");
        System.out.println("Find sections completely contained in the other one.");

        String line;
        long score = 0;
        Pattern ranges = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
          while ((line = console.readLine()) != null) {
            Matcher matcher = ranges.matcher(line);
            
            if (matcher.find()) {
              int ll = Integer.valueOf(matcher.group(1));
              int lt = Integer.valueOf(matcher.group(2));
              int rl = Integer.valueOf(matcher.group(3));
              int rt = Integer.valueOf(matcher.group(4));

              // fully contained range
              if ((rl >= ll && rt <= lt) || (ll >= rl && lt <= rt)) ++score;
            }
          }
        } catch (IOException ioex) {
            System.err.println("Cannot read from standard input.");
        }

        System.out.printf("You score %d points.\n", score);
    }
}
