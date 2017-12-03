package timo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Zahlenraten
{
  private static final long HÖCHSTE_ZAHL = 100;
  private static final int MAXIMALE_VERSUCHE = 6;
  
  public static void main(String args[])
    throws IOException
  {
    long geheimeZahl = Math.max(1, Math.round(Math.random() * HÖCHSTE_ZAHL));
    int versuche = MAXIMALE_VERSUCHE;
    long zahl = -1;

    System.out.println("ZAHLENRATEN");
    System.out.println("===========");
    System.out.println();

    BufferedReader eingabeZeile = new BufferedReader(new InputStreamReader(System.in));

    do {
      System.out.print(String.format("Was ist meine Zahl von 1 bis %1$d? ", HÖCHSTE_ZAHL));
      String eingabe = eingabeZeile.readLine();

      try {
        zahl = Long.valueOf(eingabe);
      } catch (NumberFormatException nfe) {
        System.err.println("Das ist keine Zahl. Versuche es noch einmal.");
        continue;
      }

      if (zahl == geheimeZahl) {
        System.out.println("Genau, das war richtig!");
      } else {
        --versuche;
        System.out.println(zeigeVersuche(versuche));

        if (versuche == 0) {
          System.out.println(String.format("Du hast leider verloren, meine Zahl war die %1$d.", geheimeZahl));
        } else {
          if (zahl < geheimeZahl) {
            System.out.println("Meine Zahl ist größer.");
          } else {
            System.out.println("Meine Zahl ist kleiner.");
          }
        }
      }
    } while (versuche > 0 && zahl != geheimeZahl);
  }

  private static String zeigeVersuche(int versuche) {
    StringBuilder string = new StringBuilder("Du hast ");
    
    switch (versuche) {
      case 0 :
        string.append("keinen Versuch mehr");
        break;
      case 1 :
        string.append("einen letzten Versuch");
        break;
      default :
        string.append("noch ").append(versuche).append(" Versuche");
        break;    
    }
    
    string.append(".");
    return string.toString();
  }
}
