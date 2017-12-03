package adventskalender;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class KufenbruchMeanTest
{
  private Kufenbruch application;

  @Before
  public void setup() {
    application = new Kufenbruch();
  }

  @Test
  public void shouldFailFastOnEmptyValley() {
    final Integer[][] trees = new Integer[0][0];
    final Valley valley = new Valley(trees);

    assertThat(application.solve(valley), equalTo("0 :"));
  }

  @Test
  public void shouldDetectSingleCell() {
    final Integer[][] trees = new Integer[1][1];
    trees[0][0] = 8;
    final Valley valley = new Valley(trees);

    assertThat(application.solve(valley), equalTo("8 : 8"));
  }

  @Test
  public void shouldDetectOnlyHorizontalCells() {
    final Lines lines = new Lines();
    lines.add(" 3 | 4 | 2");

    assertThat(application.solve(lines.toValley()), equalTo("9 : 3 -> 4 -> 2"));
  }

  @Test
  public void shouldDetectOnlyVerticalCells() {
    final Lines lines = new Lines();
    lines.add(" 3 ");
    lines.add(" 4 ");
    lines.add(" 2 ");

    assertThat(application.solve(lines.toValley()), equalTo("9 : 3 -> 4 -> 2"));
  }
}
