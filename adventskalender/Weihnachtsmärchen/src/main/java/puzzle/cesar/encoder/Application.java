package puzzle.cesar.encoder;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class Application {

	public static void main(String[] args) throws IOException {
		Application application = new Application();
		application.run(args);
	}

	public void run(String[] args) throws IOException {
		String text = "";
		if (args.length == 0) {
			System.out.println("Enter text which I am supposed to encode.\n");
			text = readLine();
			System.out.println("");
		} else {
			text = FileUtils.readFileToString(new File(args[0]), "UTF-8");
		}

		System.out.println(Cesar.encode(text));
	}

	@SuppressWarnings("resource")
	private String readLine() {
		Scanner console = new Scanner(System.in);
		return console.nextLine();
	}
}
