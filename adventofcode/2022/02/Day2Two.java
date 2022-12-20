import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Rock Paper Scissors Tournament
 * 
 * The Elves begin to set up camp on the beach. To decide whose tent gets to be
 * closest to the snack storage, a giant Rock Paper Scissors tournament is
 * already in progress. 
 * 
 * Rock Paper Scissors is a game between two players. Each game contains many
 * rounds; in each round, the players each simultaneously choose one of Rock,
 * Paper, or Scissors using a hand shape. Then, a winner for that round is
 * selected: 
 * 
 * Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock. 
 * If both players choose the same shape, the round instead ends in a draw.
 * 
 * Appreciative of your help yesterday, one Elf gives you an encrypted strategy
 * guide (your puzzle input) that they say will be sure to help you win. 
 * "The first column is what your opponent is going to play: A for Rock, 
 *  B for Paper, and C for Scissors. The second column says how the round needs
 *  to end: X means you need to lose, Y means you need to end the round in a
 *  draw, and Z means you need to win. Good luck!"
 * 
 * The winner of the whole tournament is the player with the highest score. 
 * Your total score is the sum of your scores for each round. The score for
 * a single round is the score for the shape you selected (1 for Rock, 2 for
 * Paper, and 3 for Scissors) plus the score for the outcome of the round 
 * (0 if you lost, 3 if the round was a draw, and 6 if you won).
 * 
 * Since you can't be sure if the Elf is trying to help you or trick you, 
 * you should calculate the score you would get if you were to follow the 
 * strategy guide.
 */
public class Day2Two {
    public static void main(String[] args) {
        System.out.println("Advent of Code, Day #2.");
        System.out.println("Calculate Stone Paper Scissors Score.");

        String line;
        long total = 0;

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
          while ((line = console.readLine()) != null) {
            char opponent = handOf(line.charAt(0));
            char me = pick(line.charAt(2), opponent);

            total += valueOf(me) + points(me, opponent);

            if (total < 0) System.err.printf("%s:%s - %d for pick, %d for game, %d totally.\n", opponent, me, valueOf(me), points(me, opponent), valueOf(me) + points(me, opponent));
          }
        } catch (IOException ioex) {
            System.err.println("Cannot read from standard input.");
        }

        System.out.printf("You score %d points.\n", total);
    }

    private static char handOf(char hand) {
      // A,X for Rock, B,Y for Paper, and C,Z for Scissors
      switch (hand) {
        case 'A', 'X' : return 'R';
        case 'B', 'Y' : return 'P';
        case 'C', 'Z' : return 'S';
      }

      return '-';
    }

    private static long valueOf(char hand) {
      switch (hand) {
        case 'R': return 1l;
        case 'P': return 2l;
        case 'S': return 3l;
      }

      return 0l;
    }

    private static long points(char me, char opponent) {
      if (me == opponent) return 3;
      if ( (me == 'R' && opponent == 'S') || (me == 'P' && opponent == 'R') || (me == 'S' && opponent == 'P') ) {
          return 6;
      }
      return 0;
    }

    private static char pick(char directive, char opponent) {
      // draw
      if (directive == 'Y') return opponent;

      // loose
      if (directive == 'X') switch (opponent) {
        case 'R' : return 'S';
        case 'P' : return 'R';
        case 'S' : return 'P';
      }

      // win
      switch (opponent) {
        case 'R' : return 'P';
        case 'P' : return 'S';
        case 'S' : return 'R';
      }

      return '-';
    }
}
