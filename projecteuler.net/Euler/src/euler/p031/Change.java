package euler.p031;

import java.util.ArrayList;

/**
 * @author Vijay Kumar : email kumar.vijay@gmail.com Distributed under Apache 2.0 License
 * 
 *         The following class contains the final result after running the coin change function.
 */
public class Change
{
  /**
   * The optimal solution for i,j where i is the denomination and j is the current target value. The final optimal
   * solution is the number of coins required to reach the 'target. The final solution will be the cell with the highest
   * index eg:- for a target amount of 12 and denomination of 1,6,10 the highest index will contain the integer value 2
   * which corresponds to 2 coins required to make a target of 12 (using 6,6).
   */
  public int OPT[][];
  /**
   * similar to the OPT structure, except that the elements are strings of change for the i,j optimal solution eg:- for
   * a target amount of 12 and denomination of 1,6,10 the highest index will contain the string "6 6".
   */
  public String optimalChange[][];

  /**
   * List of all possible solutions for the target
   */
  public ArrayList<String> allPossibleChanges = new ArrayList<String>();

  /**
   * The target value.
   */
  private int target;

  /**
   * Copy of the denominations that was used to generate this solution
   */
  public int[] denoms;

  public Change(int target, int[] denoms) {
    this.target = target;
    this.denoms = denoms;
    optimalChange = new String[denoms.length][target + 1];
    OPT = new int[denoms.length][target + 1];
  }

  public void printAllPossibleCombos() {
    if (allPossibleChanges.size() > 0) {
      System.out.println("All possible change(s) Target=" + target + ", Denominations=" + toString());
      int i = 1;
      for (String s : allPossibleChanges) {
        System.out.println(i + ") " + s);
        i++;
      }
      System.out.println();
    } else {
      System.out.println("No change for target=" + target + ", Denominations=" + toString());
    }

  }

  public String getOptimalChange() {
    int i = optimalChange.length;
    int j = optimalChange[0].length;
    String str =
        "Optimal solution for Target=" + target + ", Denominations=" + toString() + " is (" +
            optimalChange[i - 1][j - 1].trim() + ")";
    return str;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i : denoms) {
      sb.append(i + " ");
    }
    return sb.toString();
  }
}
