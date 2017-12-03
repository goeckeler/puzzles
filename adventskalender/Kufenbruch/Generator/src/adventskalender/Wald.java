package adventskalender;

public class Wald
{
  private static final int MAX = 100;

  public static void main(final String[] args) {
    for (int y = 1; y <= MAX; ++y) {
      for (int x = 1; x <= MAX; ++x) {
        final long trees = Math.min(99, Math.round(100.0 * Math.random()));
        System.out.print(String.format("%3d", trees));
        if (x < MAX) System.out.print(" |");
      }
      System.out.println();
    }
  }
}
