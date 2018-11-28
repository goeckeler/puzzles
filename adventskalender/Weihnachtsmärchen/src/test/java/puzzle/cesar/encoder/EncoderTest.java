package puzzle.cesar.encoder;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class EncoderTest
{
  @Test
  public void shouldEncodeNothing() {
    assertThat("Handle no input", Encoder.encode(null), is(""));
  }
}
