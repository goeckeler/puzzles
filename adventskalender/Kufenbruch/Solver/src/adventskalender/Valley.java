package adventskalender;

class Valley
{
  private final int width;
  private final int height;
  private final Integer[][] trees;

  public Valley(final Integer[][] trees) {
    assert trees != null;

    this.trees = trees;
    this.width = trees.length > 0 ? trees[0].length : 0;
    this.height = trees.length;
  }

  public boolean isEmpty() {
    return height == 0 || width == 0;
  }

  public Integer valueAt(final int row, final int column) {
    if ((row < 0 || row >= height) || (column < 0 || column >= width)) { return null; }

    return trees[row][column];
  }

  public Cell cellAt(final int row, final int column) {
    return new Cell(row, column, valueAt(row, column));
  }

  public boolean hasRight(final int row, final int column) {
    return valueAt(row + 1, column) != null;
  }

  public boolean hasRight(final Cell cell) {
    return hasRight(cell.row(), cell.column());
  }

  public boolean hasLeft(final Cell cell) {
    return hasLeft(cell.row(), cell.column());
  }

  public boolean hasNone(final Cell cell) {
    return !hasRight(cell) && !hasLeft(cell);
  }

  public Cell right(final Cell cell) {
    if (cell == null || !hasRight(cell)) return null;
    return cellAt(cell.row() + 1, cell.column());
  }

  public Cell left(final Cell cell) {
    if (cell == null || !hasLeft(cell)) return null;
    return cellAt(cell.row(), cell.column() + 1);
  }

  public boolean hasLeft(final int row, final int column) {
    return valueAt(row, column + 1) != null;
  }

  @Override
  public String toString() {
    final StringBuilder string = new StringBuilder();
    for (int row = 0; row < height; ++row) {
      for (int column = 0; column < width; ++column) {
        if (column > 0) string.append(" |");
        string.append(String.format("%4d", trees[row][column]));
      }
      string.append("\n");
    }
    return string.toString();
  }
}
