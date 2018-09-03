package com.goeckeler.games.einmaleins;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

public class EinMalEins {
	private static EinMalEins application;

	private int challenges = 40;
	private int challenge;
	private int mistakes;

	private Instant timeStarted;
	private Instant timeFinished;

	private Random generator = new Random();

	public static void main(String[] args) {
		application = new EinMalEins();
		application.start();

		while (!application.done()) {
			application.play();
		}

		application.finish();
	}

	private boolean done() {
		return challenge == challenges;
	}

	private void start() {
		System.out.println("Das große und kleine 1x1.");
		System.out.println("=========================\n\nBist du bereit? Dann drücke <RETURN> ... ");
		readLine();

		System.out.println("Spiel: Einfaches Kopfrechnen");
		challenge = 1;
		mistakes = 0;
		timeStarted = Instant.now();
	}

	private void finish() {
		System.out.println("\n=========================\n\nDu gewinnst dieses Spiel.\n");

		timeFinished = Instant.now();
		Duration elapsedTime = Duration.between(timeStarted, timeFinished);

		System.out.println(String.format("Du hast %d Herausforderungen mit %d Fehlern in %d Sekunden gemeistert.",
				challenge, mistakes, elapsedTime.getSeconds()));
	}

	private void play() {
		int leftNumber = 1 + generator.nextInt(10);
		int rightNumber = 1 + generator.nextInt(10);
		int result = leftNumber * rightNumber;
		int guess = 0;

		do {
			System.out.print(String.format("%2d x %2d = ", leftNumber, rightNumber));
			String answer = readLine();

			try {
				guess = Integer.parseInt(answer);
			} catch (NumberFormatException nfe) {
				System.err.println("\nZu schnell getippt? Versuche es noch einmal.\n");
				++mistakes;
				continue;
			}

			if (guess != result) {
				System.err.println("\nLeider falsch. Versuche es noch einmal.\n");
				++mistakes;
			}
		} while (guess != result);

		if (challenge % 10 == 0) {
			Duration elapsedTime = Duration.between(timeStarted, Instant.now());
			System.out.println(String.format("\n%2d%%, %d von %d Herausforderungen in %d Sekunden.\n",
					Math.round(100 * challenge / challenges), challenge, challenges, elapsedTime.getSeconds()));
		}

		++challenge;
	}

	@SuppressWarnings("resource")
	private String readLine() {
		Scanner console = new Scanner(System.in);
		return console.nextLine();
	}
}
