package puzzle.cesar.encoder;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Cesar
{
  private static Map<Character, String> surrogates = new HashMap<>();
  
  static {
    surrogates.put('ä', "ae");
    surrogates.put('Ä', "AE");
    surrogates.put('ö', "oe");
    surrogates.put('Ö', "OE");
    surrogates.put('ü', "ue");
    surrogates.put('Ü', "UE");
    surrogates.put('ß', "ss");
  }
  
  private Cesar() {
  }

  public static String encode(String text) {
    int shift = rotation();
    StringBuilder code = new StringBuilder();
    for (Character character : StringUtils.defaultString(text).toCharArray()) {
      if (isUmlaut(character)) {
        code.append(encode(surrogates.get(character)));
        continue;
      }
      
      Character chiffre = character;
      
      if (isAlphabet(character)) {
        Character base = isLower(character) ? 'a' : 'A';
        chiffre = (char) ((character - base + shift) % 26 + base);
      }
      
      code.append(chiffre);
    }
    return code.toString();
  }

  public static int rotation() {
    return Integer.parseInt(System.getProperty("rotate", "3"));
  }
  
  public String decode(String text, int shift) {
    StringBuilder code = new StringBuilder();
    for (Character character : text.toCharArray()) {
      if (isAlphabet(character)) {
        Character base = isLower(character) ? 'a' : 'A';
        int offset = character - base;
        code.append((char) ((offset < shift) ? character + 26 - shift : character - shift));
      } else if (isUmlaut(character)) {
      } else {
        code.append(character);
      }
    }
    return code.toString();
  }

  private static boolean isLower(Character character) {
    return (character >= 'a' && character <= 'z');
  }

  private static boolean isUpper(Character character) {
    return (character >= 'A' && character <= 'Z');
  }

  private static boolean isAlphabet(Character character) {
    return isLower(character) || isUpper(character);
  }
  
  private static boolean isUmlaut(Character character) {
    return "äöüßÄÖÜ".contains(Character.toString(character));
  }
}
