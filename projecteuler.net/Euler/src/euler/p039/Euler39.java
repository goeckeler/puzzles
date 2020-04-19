package euler.p039;

import java.util.*;

import euler.objects.Bag;
import euler.objects.Pair;

/**
 * Integer right triangles
 * 
 * If <code>p</code> is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly
 * three solutions for <code>p = 120</code>.
 * 
 * <pre>
 * {20,48,52}, {24,45,51}, {30,40,50}
 * </pre>
 * 
 * For which value of <code>p ≤ 1000</code>, is the number of solutions maximised?
 */
public class Euler39
{
  /**
   * (1) a² + b² = c² - right angle triangle
   * (2) a + b + c = p - perimeter
   * (3) a <= b - symmetric solutions
   * 
   * Let's check what are the bounds of the sides and the hypotenuse.
   * 
   * (4) a = 1 => (1) 1 + b² = c² ≈ b² = c² ≈ b = c => (2) 1 + c + c = p ≈ 2c = p => c = p/2
   * (5) a = b => (1) a² + a² = c² => (2) a + a + a√2 = p => a (2 + √2) = p => a < p/3
   * 
   * Okay, now that we know that 1 ≤ a < p/3 and c ≤ p/2 what's the ideas?
   * 
   * There are not too many perfect squares that fit in the above equation. So what about computing all squares, then
   * starting from top substract any square from the bottom and see if it leaves a known square. Take the roots, add
   * them up, and remember them all.
   */
  public static void main(String[] args) {
    final int MAX_PERIMETER = 1000;
    final int MAX_C = MAX_PERIMETER / 2;

    Bag<Integer, Triple> solutions = new Bag<>(MAX_PERIMETER);
    Map<Integer, Integer> squares = new HashMap<>(15 * MAX_C / 10);

    for (int root = 1; root <= MAX_C; ++root) {
      squares.put(root * root, root);
    }

    for (int c = MAX_C; c > 1; --c) {
      for (int a = 1; a < Math.min(MAX_PERIMETER / 3, c); ++a) {
        int b2 = c * c - a * a;

        if (b2 < 1 || b2 < a * a) {
          continue;
        }

        Integer b = squares.get(b2);

        // now if there is a matching square we have a triple
        if (b != null && a + b + c <= MAX_PERIMETER) {
          Triple match = new Triple(a, b, c);
          solutions.put(match.perimeter(), match);
        }
      }
    }

    int count = 0;
    int top = 0;
    for (Pair<Integer, SortedSet<Triple>> pair : solutions.entries()) {
      if (pair.getValue().size() > count) {
        count = pair.getValue().size();
        top = pair.getKey();
      }
      System.out.format("%d : %s\n", pair.getKey(), pair.getValue());
    }

    System.out.format("\n\nPerimeter %d has %d solutions.", top, count);
  }
}

class Triple implements Comparable<Triple>
{
  private int a;
  private int b;
  private int c;

  public Triple() {
    this(0, 0, 0);
  }

  public Triple(int a, int b, int c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  public int getB() {
    return b;
  }

  public void setB(int b) {
    this.b = b;
  }

  public int getC() {
    return c;
  }

  public void setC(int c) {
    this.c = c;
  }

  public int perimeter() {
    return a + b + c;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + a;
    result = prime * result + b;
    result = prime * result + c;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Triple other = (Triple) obj;
    if (a != other.a) return false;
    if (b != other.b) return false;
    if (c != other.c) return false;
    return true;
  }

  @Override
  public int compareTo(final Triple that) {
    int cmp = this.perimeter() - that.perimeter();
    if (cmp == 0) {
      cmp = this.getA() - that.getA();
    } ;
    return cmp;
  }

  @Override
  public String toString() {
    return "(" + a + ", " + b + ", " + c + ")";
  }
}
