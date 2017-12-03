package timo;

public class Einmaleins
{
  public static void main(String[] args) {
    druckeKleinesEinmaleins();
    druckeGroßesEinmaleins();
  }

  private static void druckeKleinesEinmaleins() {
    for (int linkeZahl = 1; linkeZahl <= 10; ++linkeZahl) {
      for (int rechteZahl = 1; rechteZahl <= 10; ++rechteZahl) {
        druckeZeile(linkeZahl, rechteZahl);
      }
      System.out.println();
    }
  }

  private static void druckeGroßesEinmaleins() {
    for (int zahl = 11; zahl <= 25; ++zahl) {
      druckeZeile(zahl, zahl);
      if (zahl % 10 == 0) {
        System.out.println();
      }
    }
    System.out.println();
  }

  private static void druckeZeile(int linkeZahl, int rechteZahl) {
    System.out.println(String.format("%1$,2d * %2$,2d = %3$,3d", linkeZahl, rechteZahl, linkeZahl * rechteZahl));
  }
}
