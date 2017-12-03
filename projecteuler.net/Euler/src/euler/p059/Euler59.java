package euler.p059;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isWhitespace;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import euler.utils.SubSet;
import euler.utils.Utils;

/**
 * Each character on a computer is assigned a unique code and the preferred standard is ASCII (American Standard Code
 * for Information Interchange). For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 * 
 * A modern encryption method is to take a text file, convert the bytes to ASCII, then XOR each byte with a given value,
 * taken from a secret key. The advantage with the XOR function is that using the same encryption key on the cipher
 * text, restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
 * 
 * For unbreakable encryption, the key is the same length as the plain text message, and the key is made up of random
 * bytes. The user would keep the encrypted message and the encryption key in different locations, and without both
 * "halves", it is impossible to decrypt the message.
 * 
 * Unfortunately, this method is impractical for most users, so the modified method is to use a password as a key. If
 * the password is shorter than the message, which is likely, the key is repeated cyclically throughout the message. The
 * balance for this method is using a sufficiently long password key for security, but short enough to be memorable.
 * 
 * Your task has been made easy, as the encryption key consists of three lower case characters. Using cipher1.txt (right
 * click and 'Save Link/Target As...'), a file containing the encrypted ASCII codes, and the knowledge that the plain
 * text must contain common English words, decrypt the message and find the sum of the ASCII values in the original
 * text.
 */
public class Euler59
{
  public static void main(String[] args)
    throws FileNotFoundException, IOException
  {
    System.out.println(asString("65,66,97"));
    BufferedReader reader = new BufferedReader(new FileReader("p059.cipher.txt"));
    StringBuilder input = new StringBuilder();
    String line = null;
    while ((line = reader.readLine()) != null) {
      input.append(line);
    }
    reader.close();
    String cipher = asString(input.toString());

    String test = "Help me, bastard.";
    String password = "abc";

    String coded = decode(test, password);
    String decoded = decode(coded, password);

    System.out.println("Original \"" + test + "\", decoded again \"" + decoded + "\".");
    crack(decode("Help the people", "abc"));
    crack(cipher);
    
    // password is "god"
    String solution = decode(cipher, "god");
    int sum = 0;
    for (int index = 0; index < solution.length(); ++index) {
      sum += solution.charAt(index);
    }
    
    System.out.println("Character sum of solution is " + sum);
  }

  private static String asString(String cipher) {
    StringBuilder string = new StringBuilder();
    String[] characters = cipher.split(",");
    for (String character : characters) {
      string.append((char) Integer.valueOf(character).intValue());
    }
    return string.toString();
  }

  private static String crack(String cipher) {
    String[] alphabet =
        {
          "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
          "w", "x", "y", "z"
        };

    SubSet<String> passwords = new SubSet<String>(Arrays.asList(alphabet), 3);

    StringBuilder cracks = new StringBuilder();
    List<Set<String>> combinations = passwords.list();

    System.out.println("Brute force attack with " + combinations.size() * 6 + " words.");
    for (Set<String> letters : combinations) {
      StringBuilder password = new StringBuilder();
      for (String letter : letters) {
        password.append(letter);
      }

      for (String phrase : Utils.permutations(password.toString())) {
        if (crack(cipher, phrase)) {
          cracks.append("[").append(phrase).append("]");
        }
      }
    }
    return cracks.toString();
  }

  public static boolean crack(String cipher, String password) {
    String decoded = decode(cipher, password);
    if (decoded.contains("the") || decoded.contains("THE")) {
      int errors = decoded.length();
      for (int index = 0; index < decoded.length(); ++index) {
        char character = decoded.charAt(index);
        if (isLetterOrDigit(character) || isWhitespace(character)) --errors;
      }

      if (errors < 2 * decoded.length() / 10) {
        System.out.println("[" + password + "] " + decoded);
        return true;
      }
    }

    return false;
  }

  public static String decode(String cipher, String password) {
    StringBuilder decoded = new StringBuilder();
    for (int index = 0; index < cipher.length(); ++index) {
      int character = cipher.charAt(index);
      character ^= password.charAt(index % password.length());
      decoded.append((char) character);
    }
    return decoded.toString();
  }
}
