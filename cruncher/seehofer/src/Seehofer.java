import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Seehofer
{
  public static void main(final String[] args) {
    new Seehofer();
  }

  public Seehofer() {
    this.solve();
  }

  public void solve() {
    final List<Integer> digits = new ArrayList<>();
    IntStream.rangeClosed(1, 9).forEach(n -> digits.add(n));
    solve(digits, 0);
  }

  private void solve(final List<Integer> digits, int solution) {
    if (digits.isEmpty()) {
      System.out.println(solution);
      return;
    }

    for (final Integer digit : digits) {
      solution = 10 * solution + digit;
      final int divisor = ("" + solution).length();

      if (solution % divisor == 0) {
        final List<Integer> lessDigits = new ArrayList<>(digits);
        lessDigits.remove(digit);

        solve(lessDigits, solution);
      }

      solution /= 10;
    }
  }
}
