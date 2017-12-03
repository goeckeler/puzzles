package euler.p076;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import euler.p096.Pair;
import euler.utils.SubSet;

/**
 * It is possible to write five as a sum in exactly six different ways:
 * 
 * <pre>
 *   4 + 1
 *   3 + 2
 *   3 + 1 + 1
 *   2 + 2 + 1
 *   2 + 1 + 1 + 1
 *   1 + 1 + 1 + 1 + 1
 * </pre>
 * 
 * How many different ways can one hundred be written as a sum of at least two positive integers?
 */
public class Euler76
{
  public static int steps = 0;

  public static void simple(int number) {
    final int total = number;
    int[] coins = new int[number - 1];
    for (int coin = 0; coin < number - 1; ++coin) {
      coins[coin] = coin + 1;
    }
    int[] ways = new int[total + 1];
    ways[0] = 1;

    for (int coin : coins)
      for (int j = coin; j <= total; j++)
        ways[j] += ways[j - coin];

    System.out.println("There are " + (ways[total]) + " ways to sum up " + total + ".");
  }

  public static void main(String[] args) {
    int n = 100;
    // solve(n);
    // solve2(n);
    simple(n);
  }

  public static void solve(int number) {
    Map<Integer, Integer> way = new HashMap<Integer, Integer>();
    Set<Map<Integer, Integer>> solutions = new HashSet<Map<Integer, Integer>>(1000003);
    way.put(1, number);
    steps = 0;
    solutions.add(way);
    reduce(way, solutions);

    System.out.println("\nThere are " + solutions.size() + " possibilities in " + steps + " steps.");
    System.out.println(solutions);
  }

  public static void reduce(final Map<Integer, Integer> way, final Set<Map<Integer, Integer>> solutions) {
    // the smallest sum has two terms and cannot be reduced further
    if (count(way) == 2) return;

    // check if we can at least sum up two 1s
    Integer counts = way.get(1);
    if (counts != null && counts > 1) {
      Map<Integer, Integer> solution = reduce(way, 1, 1, solutions);
      if (solution != null) reduce(solution, solutions);
    }

    // now reduce over each pair, only 1 is allowed to add in its own group
    SubSet<Integer> sets = new SubSet<Integer>(way.keySet(), 2);
    for (Set<Integer> pairs : sets.list()) {
      Integer[] pair = pairs.toArray(new Integer[2]);
      Map<Integer, Integer> solution = reduce(way, pair[0], pair[1], solutions);
      if (solution != null) reduce(solution, solutions);
    }
  }

  public static void solve2(int number) {
    Map<Integer, Integer> way = new HashMap<Integer, Integer>();
    Set<Map<Integer, Integer>> solutions = new HashSet<Map<Integer, Integer>>(100003);
    way.put(1, number);
    steps = 0;
    solutions.add(way);
    reduce2(way, solutions);

    System.out.println("\nThere are " + solutions.size() + " possibilities in " + steps + " steps.");
    System.out.println(solutions);
  }

  public static void reduce2(final Map<Integer, Integer> way, final Set<Map<Integer, Integer>> solutions) {
    // the smallest sum has two terms and cannot be reduced further
    if (count(way) == 2) return;

    // check if we can at least sum up two 1s
    Integer count = way.get(1);
    if (count != null && count > 1) {
      Map<Integer, Integer> solution = reduce(way, 1, 1, solutions);
      if (solution != null) reduce2(solution, solutions);
    }

    Pair<Integer, Integer> low = low(way);
    Pair<Integer, Integer> high = high(way);
    if (low != null && high != null) {
      if (high.equals(low)) {
        low = null;
      } else if (low.getKey() + low.getValue() > high.getKey()) {
        low = null;
      }
    }

    if (low != null) {
      Map<Integer, Integer> solution = reduce(way, low.getKey(), low.getValue(), solutions);
      if (solution != null) reduce2(solution, solutions);
    }

    // now reduce the highest pair, only 1 is allowed to add in its own group
    if (high != null) {
      Map<Integer, Integer> solution = reduce(way, high.getKey(), high.getValue(), solutions);
      if (solution != null) reduce2(solution, solutions);
    }
  }

  private static Map<Integer, Integer> clone(final Map<Integer, Integer> way) {
    return new HashMap<Integer, Integer>(way);
  }

  private static Map<Integer, Integer> reduce(final Map<Integer, Integer> way, int high, int low,  final Set<Map<Integer, Integer>> solutions) {
    Map<Integer, Integer> solution = clone(way);

    // remove first number
    Integer count = solution.get(high);
    if (count == 1) {
      solution.remove(high);
    } else {
      solution.put(high, count - 1);
    }

    // remove second number
    count = solution.get(low);
    if (count == 1) {
      solution.remove(low);
    } else {
      solution.put(low, count - 1);
    }

    // add sum
    int reduced = high + low;
    count = solution.get(reduced);
    if (count == null) count = 0;
    solution.put(reduced, count + 1);

    System.out.print('.');
    if (++steps % 50 == 0) {
      System.out.println(" " + solutions.size());
    }
    
    if (solutions.contains(solution)) {
      // System.out.println("\nHIT " + solution);
      return null;
    }
    solutions.add(solution);

    return solution;
  }

  private static int count(final Map<Integer, Integer> way) {
    int count = 0;
    for (Integer n : way.values()) {
      count += n;
    }
    return count;
  }

  private static Pair<Integer, Integer> high(final Map<Integer, Integer> way) {
    int next = 0;
    int max = 0;
    for (Integer n : way.keySet()) {
      if (n > max) {
        if (max > next) next = max;
        max = n;
      } else if (n < max && n > next) {
        next = n;
      }
    }

    if (next == 0 || max == 0) return null;
    return new Pair<Integer, Integer>(max, next);
  }

  private static Pair<Integer, Integer> low(final Map<Integer, Integer> way) {
    int next = Integer.MAX_VALUE;
    int max = Integer.MAX_VALUE;
    for (Integer n : way.keySet()) {
      if (n < max) {
        if (max < next) next = max;
        max = n;
      } else if (n > max && n < next) {
        next = n;
      }
    }

    if (next == Integer.MAX_VALUE || max == Integer.MAX_VALUE) return null;
    return new Pair<Integer, Integer>(next, max);
  }
}
