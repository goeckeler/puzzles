package adventskalender;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.*;

@Ignore("Takes roughly 90 seconds with -Xss4096k")
public class KufenbruchLargeFileTest
{
  private Kufenbruch application;

  @Before
  public void setup() {
    application = new Kufenbruch();
  }

  @Test
  public void shouldFindPathInHundredByHundredGrid()
    throws IOException
  {
    final Lines lines = new Lines();
    Filer.readLines("grid-100x100.txt").forEach(s -> lines.add(s));
    assertThat(application.solve(lines.toValley()),
        equalTo("7425 : 8 -> 18 -> 99 -> 4 -> 15 -> 36 -> 10 -> 58 -> 52 -> 57 -> 38 -> 59 -> 15 -> 16 -> 28 -> 94"
                + " -> 13 -> 9 -> 54 -> 45 -> 64 -> 1 -> 2 -> 37 -> 20 -> 3 -> 21 -> 9 -> 40 -> 8 -> 70 -> 39 -> 77"
                + " -> 12 -> 30 -> 50 -> 18 -> 51 -> 5 -> 71 -> 5 -> 8 -> 55 -> 63 -> 94 -> 48 -> 48 -> 7 -> 23"
                + " -> 14 -> 21 -> 41 -> 15 -> 8 -> 25 -> 70 -> 38 -> 2 -> 55 -> 50 -> 67 -> 18 -> 87 -> 45 -> 64"
                + " -> 30 -> 1 -> 37 -> 87 -> 16 -> 15 -> 42 -> 57 -> 19 -> 89 -> 92 -> 15 -> 64 -> 28 -> 23 -> 90"
                + " -> 24 -> 28 -> 89 -> 42 -> 55 -> 16 -> 34 -> 24 -> 28 -> 23 -> 17 -> 47 -> 52 -> 6 -> 8 -> 58"
                + " -> 64 -> 19 -> 37 -> 23 -> 16 -> 72 -> 24 -> 89 -> 3 -> 74 -> 97 -> 27 -> 33 -> 35 -> 59 -> 6"
                + " -> 8 -> 24 -> 48 -> 1 -> 15 -> 31 -> 48 -> 26 -> 11 -> 6 -> 5 -> 75 -> 14 -> 11 -> 1 -> 23 -> 21"
                + " -> 71 -> 21 -> 28 -> 57 -> 91 -> 42 -> 51 -> 6 -> 9 -> 23 -> 47 -> 84 -> 13 -> 26 -> 19 -> 3 -> 84"
                + " -> 29 -> 38 -> 24 -> 31 -> 48 -> 59 -> 34 -> 37 -> 92 -> 37 -> 30 -> 37 -> 28 -> 11 -> 83 -> 14"
                + " -> 22 -> 34 -> 7 -> 36 -> 63 -> 18 -> 8 -> 40 -> 77 -> 32 -> 64 -> 86 -> 79 -> 35 -> 96 -> 4 -> 7"
                + " -> 73 -> 5 -> 0 -> 27 -> 82 -> 77 -> 13 -> 57 -> 45 -> 63 -> 4 -> 11 -> 91 -> 52 -> 21 -> 33 -> 34"
                + " -> 12 -> 81"));
  }
}
