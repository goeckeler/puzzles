package adventskalender;

import java.io.*;
import java.util.*;

public class Kufenbruch
{
  public static void main(final String[] args)
    throws IOException
  {
    final Lines input = new Lines();

    final LineNumberReader reader = new LineNumberReader(new InputStreamReader(System.in));
    String line = null;
    do {
      line = reader.readLine();
      input.add(line);
    } while (reader.ready());

    System.out.println(new Kufenbruch().solve(input.toValley()));
  }

  public String solve(final Valley valley) {
    if (valley.isEmpty()) { return "0 :"; }

    final Set<Route> routes = new HashSet<>();
    final Route bestRoute = Route.startWith(valley.cellAt(0, 0));
    routes.add(bestRoute);
    return solve(valley, routes, bestRoute);
  }

  private String solve(final Valley valley, final Set<Route> paths, final Route bestRoute) {
    Route route = bestRoute;
    final Cell head = route.head();
    if (valley.hasNone(head)) {
      // that is the cheapest route ...
      return route.toString();
    }

    paths.remove(route);

    if (valley.hasRight(head)) {
      final Cell dive = valley.right(head);
      final Route path = route.with(dive);
      addIfCheaper(paths, path, dive);
    }

    if (valley.hasLeft(head)) {
      final Cell dive = valley.left(head);
      final Route path = route.with(dive);
      addIfCheaper(paths, path, dive);
    }

    route = null;
    for (final Route that : paths) {
      if (route == null || that.costs() < route.costs()) {
        route = that;
      }
    }
    return solve(valley, paths, route);
  }

  private void addIfCheaper(final Set<Route> paths, final Route path, final Cell head) {
    final Optional<Route> visited = paths.stream().filter(r -> r.contains(head)).findFirst();
    if (visited.isPresent()) {
      if (visited.get().costs() <= path.costs()) return;
      paths.remove(visited.get());
    }
    paths.add(path);
  }
}
