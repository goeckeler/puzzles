package adventskalender;

import java.util.LinkedList;
import java.util.List;

class Lines
{
  private List<String> lines = new LinkedList<>();

  public void add(final String line) {
    lines.add(line);
  }

  public Valley toValley() {
    final int height = lines.size();
    final int width = lines.isEmpty() ? 0 : lines.get(0).split("\\|").length;

    final Integer[][] array = new Integer[height][width];

    if (!lines.isEmpty()) {
      for (int row = 0; row < height; ++row) {
        final String[] values = lines.get(row).split("\\|");
        if (values.length != width) {
          // wrong input
          throw new RuntimeException(String.format("Tree map is unbalanced in row #%d, got %d values instead of %d.",
              row + 1, values.length, width));
        }

        for (int column = 0; column < width; ++column) {
          final Integer value = Integer.parseUnsignedInt(values[column].trim());
          array[row][column] = value;
        }
      }
    }

    final Valley valley = new Valley(array);
    return valley;
  }
}