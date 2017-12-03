package euler.p031;

/**
 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
 * 
 * <pre>
 *   1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 * </pre>
 * 
 * It is possible to make £2 in the following way:
 * 
 * <pre>
 *   1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 * </pre>
 * 
 * How many different ways can £2 be made using any number of coins?
 */
public class Euler31
{
  public static void main(String[] args) {
    simple();
  }

  public static void simple() {
    final int total = 200;

    int[] coins = { 1, 2, 5, 10, 20, 50, 100, 200
    };
    int[] ways = new int[total + 1];
    ways[0] = 1;

    for (int coin : coins)
      for (int j = coin; j <= total; j++)
        ways[j] += ways[j - coin];

    System.out.println("There are " + ways[total] + " to change " + total + " pence.");
  }

  public static void twoPounds() {
    Changer changer = new Changer();
    Change coins = changer.findAllPossibleCombinations(200, new int[] { 1, 2, 5, 10, 20, 50, 100, 200
    });
    // coins.printAllPossibleCombos();
    System.out.println("There are " + coins.allPossibleChanges.size() + " solutions.");
  }

  public static void smokeTest() {
    Changer changer = new Changer();
    Change coins = changer.findAllPossibleCombinations(5, new int[] { 1, 2, 5
    });
    coins.printAllPossibleCombos();
    System.out.println("There are " + coins.allPossibleChanges.size() + " solutions.");
  }

  public static void originalTest() {
    Changer changer = new Changer();

    // target is 15 and change is 1,6,7
    Change coins = changer.findAllPossibleCombinations(15, new int[] { 1, 6, 7
    });
    coins.printAllPossibleCombos();

    // example 2
    coins = changer.findAllPossibleCombinations(4, new int[] { 1, 2, 3
    });
    coins.printAllPossibleCombos();

    // example 3
    coins = changer.findAllPossibleCombinations(15, new int[] { 1, 3, 5, 10
    });
    coins.printAllPossibleCombos();

    // optimal solution for target=15
    // soln = ch.findOptimalChange(15, new int[] {1,5,3,10});
    coins = changer.findOptimalChange(4, new int[] { 1, 2, 3
    });
    System.out.println(coins.getOptimalChange());
  }
}
