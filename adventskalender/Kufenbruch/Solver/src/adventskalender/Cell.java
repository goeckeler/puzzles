package adventskalender;

public class Cell
  implements Comparable<Cell>
{
  private int row;
  private int column;
  private int value;

  public Cell(final int row, final int column, final int value) {
    this.row = row;
    this.column = column;
    this.value = value;
  }

  @Override
  public int compareTo(final Cell that) {
    if (that == null) return 1;
    if (this == that) return 0;
    return this.value - that.value;
  }

  public int row() {
    return row;
  }

  public int column() {
    return column;
  }

  public int value() {
    return value;
  }

  @Override
  public int hashCode() {
    return 10007 * row + column;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (other == null) return false;
    return toString().equals(other.toString());
  }

  @Override
  public String toString() {
    return "[" + row + ", " + column + "] " + value;
  }
}
