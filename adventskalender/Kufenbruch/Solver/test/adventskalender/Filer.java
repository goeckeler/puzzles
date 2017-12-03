package adventskalender;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.List;

public class Filer
{
  private static final Charset UTF8 = Charset.forName("UTF-8");

  public static boolean exists(final String file) {
    final String root = System.getProperty("user.dir");
    return Files.exists(FileSystems.getDefault().getPath(root, file));
  }

  public static List<String> readLines(final String file)
    throws IOException
  {
    return Files.readAllLines(getPath(file), UTF8);
  }

  public static Path getPath(final String file) {
    final String root = System.getProperty("user.dir");
    return FileSystems.getDefault().getPath(root, file);
  }
}
