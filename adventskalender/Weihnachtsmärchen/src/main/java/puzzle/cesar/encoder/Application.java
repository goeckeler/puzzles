package puzzle.cesar.encoder;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Application {

	public static void main(String[] args) {
		Application application = new Application();
		application.run(args);
	}

	public void run(String[] args) {
		String text = "";
		if (args.length == 0) {
			System.out.println("Enter text which I am supposed to decode.\n");
			text = readLine();
			System.out.println("");
		} else {
			text = StringUtils.join(args, " ");
		}

		for (int shift = 0; shift < 26; ++shift) {
          System.out.println(String.format("[%2d] %s", shift, decode(text, shift)));
		}
	}

	public String decode(String text, int shift) {
		StringBuilder code = new StringBuilder();
		for (Character character : text.toCharArray()) {
			if (isAlphabet(character)) {
				Character base = isLower(character) ? 'a' : 'A';
				int offset = character - base;
				code.append((char)((offset < shift) ? character + 26 - shift : character - shift));
			} else {
				code.append(character);
			}
		}
		return code.toString();
	}
	
	private boolean isLower(Character character) {
		return (character >= 'a' && character <= 'z');
	}
	
	private boolean isUpper(Character character) {
		return (character >= 'A' && character <= 'Z');
	}
	
	private boolean isAlphabet(Character character) {
		return isLower(character) || isUpper(character);
	}

	@SuppressWarnings("resource")
	private String readLine() {
		Scanner console = new Scanner(System.in);
		return console.nextLine();
	}

}
