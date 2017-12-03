package adventskalender;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class KufenbruchDataTest
{
  private Kufenbruch application;

  @Before
  public void setup() {
    application = new Kufenbruch();
  }

  @Test
  public void shouldFindPathInTwoByTwoGrid()
    throws IOException
  {
    final Lines lines = new Lines();
    Filer.readLines("grid-2x2.txt").forEach(s -> lines.add(s));
    assertThat(application.solve(lines.toValley()), equalTo("7 : 1 -> 2 -> 4"));
  }

  @Test
  public void shouldFindPathInFourByFourGrid()
    throws IOException
  {
    final Lines lines = new Lines();
    Filer.readLines("grid-4x4.txt").forEach(s -> lines.add(s));
    assertThat(application.solve(lines.toValley()), equalTo("64 : 0 -> 1 -> 5 -> 27 -> 18 -> 6 -> 7"));
  }
}
