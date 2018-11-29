package puzzle.cesar.encoder;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class CesarTest
{
  @Test
  public void shouldEncodeNothing() {
    assertThat("Handle no input", Cesar.encode(null), is(""));
  }
  
  @Test
  public void shouldRotateByDefault() {
    System.clearProperty("rotate");
    assertThat("Cesar rotated 3 characters", Cesar.rotation(), is(3));
  }

  @Test
  public void shouldRotateByThirteen() {
    System.setProperty("rotate", "13");
    assertThat("ROT13 rotates 13 characters", Cesar.rotation(), is(13));
    System.clearProperty("rotate");
  }

  @Test
  public void shouldEncodeWord() {
    assertThat("Word encoded", Cesar.encode("Froh"),is("Iurk"));
    assertThat("Rotates alphabet", Cesar.encode("zu"), is("cx"));
  }

  @Test
  public void shouldLeavePunctuation() {
    assertThat("Keep punctuation", Cesar.encode("z.u!"), is("c.x!"));
  }
  
  @Test
  public void shouldCopeWithUmlauts() {
    assertThat("Convert umlauts", Cesar.encode("äöüßÄÖÜ"), is("dhrhxhvvDHRHXH"));
  }
  
  @Test
  public void shouldPreserveWhiteSpace() {
    assertThat("Preserve white space", Cesar.encode("Zu froh!"), is("Cx iurk!"));
  }
  
  @Test
  public void shouldPreserveLines() {
    assertThat("Preserve lines", Cesar.encode("Zu\nfroh!"), is("Cx\niurk!"));
  }
}
