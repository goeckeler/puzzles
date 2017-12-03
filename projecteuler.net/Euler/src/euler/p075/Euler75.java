package euler.p075;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import euler.p096.Pair;
import euler.utils.Utils;

/**
 * It turns out that 12cm is the smallest length of wire that can be bent to form an integer sided right angle triangle
 * in exactly one way, but there are many more examples.
 * 
 * <ul>
 * <li>12cm: (3,4,5)
 * <li>24cm: (6,8,10)
 * <li>30cm: (5,12,13)
 * <li>36cm: (9,12,15)
 * <li>40cm: (8,15,17)
 * <li>48cm: (12,16,20)
 * </ul>
 * 
 * In contrast, some lengths of wire, like 20 cm, cannot be bent to form an integer sided right angle triangle, and
 * other lengths allow more than one solution to be found; for example, using 120 cm it is possible to form exactly
 * three different integer sided right angle triangles.
 * 
 * <ul>
 * <li>120cm: (30,40,50), (20,48,52), (24,45,51)
 * </ul>
 * 
 * Given that L is the length of the wire, for how many values of L ≤ 1,500,000 can exactly one integer sided right
 * angle triangle be formed?
 */
public class Euler75
{
  public static void main(String[] args) {
    generated();
  }

  public static void generated() {
    // generated uses the generator for Pythagorean triples
    System.out.println("Generating pythagorean triples ...");
    int wire = 1500000;

    int maxV = Double.valueOf((Math.sqrt((wire + 0.25) + 0.5) / 2)).intValue();
    int maxU = Double.valueOf((Math.sqrt(2 * wire + 1) - 1) / 2).intValue();

    // remember past lengths
    int[] lenght = new int[wire + 1];

    // will generate approximately wire / 2 triples
    for (int v = 1; v <= maxV; ++v) {
      if (v % 100 == 0) System.out.println("V");
      for (int u = v + 1; u <= maxU; ++u) {
        if (u % 100 == 0) System.out.print(".");

        int u2 = u * u;
        int v2 = v * v;

        // create primitive triples only
        if ((u % 2 + v % 2 == 1) && Utils.gcd(u, v) == 1) {
          int a = u2 - v2;
          int b = 2 * u * v;
          int c = u2 + v2;

          int l = a + b + c;

          // System.out.println("[" + u + "," + v + "] (" + a + ", " + b + ", " + c + ") = " + l);
          
          for (int n = 1; n * l <= wire; ++n) {
            // continue with next v
            ++lenght[n* l];
          }
        }
      }
    }

    System.out.println("\nCounting unique pythagorean triples ...");
    Map<Integer, Integer> statistics = new HashMap<Integer, Integer>();
    int unique = 0;
    for (int index = 0; index <= wire; ++index) {
      Integer count = statistics.get(lenght[index]);
      if (count == null) count = 0;
      statistics.put(lenght[index], count + 1);
      if (lenght[index] == 1) ++unique;
    }
    System.out.println("Found " + unique + " pythagorean triples.");
    System.out.println("Complete statistics is " + statistics.toString());
  }

  public static void naive() {
    int length = 1500000;
    int maxN = length / 2;
    Map<Integer, List<Pair<Integer, Integer>>> squares = new HashMap<Integer, List<Pair<Integer, Integer>>>(maxN);

    System.out.println("Generating squares ...");
    int n = 1;
    int square = 1;
    do {
      squares.put(square, null);
      ++n;
      square = n * n;
    } while (n < maxN);

    int lastN = n - 1;

    for (int a = 1; a <= lastN; ++a) {
      System.out.println("Computing all pairs for " + a + " of " + lastN);
      for (int b = a; b <= lastN; ++b) {
        // check the length first
        square = a * a + b * b;
        int c = Double.valueOf(Math.sqrt(square)).intValue();
        if (a + b + c > length) continue;

        // that's the trick ... is there another square that is equal to Pythagoras?
        if (squares.containsKey(square)) {
          Pair<Integer, Integer> pair = new Pair<Integer, Integer>(a, b);
          List<Pair<Integer, Integer>> pairs = squares.get(square);
          if (pairs == null) {
            pairs = new ArrayList<Pair<Integer, Integer>>();
            squares.put(square, pairs);
          }
          pairs.add(pair);
        }
      }
    }

    int solutions = 0;
    for (List<Pair<Integer, Integer>> pairs : squares.values()) {
      boolean counts = false;

      if (pairs == null) continue;

      if (pairs.size() == 1) {
        Pair<Integer, Integer> pair = pairs.get(0);
        // now the length can still be too large
        int a = pair.getKey();
        int b = pair.getValue();
        int c = Double.valueOf(Math.sqrt(a * a + b * b)).intValue();
        if (a + b + c < length) {
          ++solutions;
          counts = true;
        }
      }
      System.out.println((counts ? ">" : ".") + pairs.toString());
    }

    System.out.println("\nThere are " + solutions + " unique solutions.");
  }
}
