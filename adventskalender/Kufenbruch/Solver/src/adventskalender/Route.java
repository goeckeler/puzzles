package adventskalender;

import java.util.*;

public class Route
  implements Comparable<Route>
{
  private List<Cell> cells = null;
  private int costs = 0;
  private Cell head = null;

  private Route() {
  }

  public static Route startWith(final Cell cell) {
    final Route path = new Route();
    path.cells = Collections.singletonList(cell);
    path.costs = cell.value();
    path.head = cell;

    return path;
  }

  public Route with(final Cell cell) {
    final Route path = new Route();
    path.cells = new ArrayList<>(this.cells.size() + 1);
    path.cells.addAll(cells);
    path.cells.add(cell);
    path.costs = this.costs + cell.value();
    path.head = cell;

    return path;
  }

  public int costs() {
    return costs;
  }

  public int length() {
    return cells.size();
  }

  public boolean contains(final Cell cell) {
    return cells.contains(cell);
  }

  public Cell head() {
    return head;
  }

  @Override
  public int hashCode() {
    return head.hashCode();
  }

  @Override
  public int compareTo(final Route that) {
    if (that == null) return -1;
    // cheaper ones first
    int cmp = this.costs() - that.costs();
    // longer ones first
    if (cmp == 0) cmp = that.length() - this.length();
    return cmp;
  }

  @Override
  public String toString() {
    final StringBuilder string = new StringBuilder();
    string.append(costs).append(" : ");

    boolean first = true;
    for (final Cell cell : cells) {
      if (!first) {
        string.append(" -> ");
      } else {
        first = false;
      }
      string.append(cell.value());
    }
    return string.toString();
  }
}
