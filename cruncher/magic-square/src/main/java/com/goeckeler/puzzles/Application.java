package com.goeckeler.puzzles;

public class Application
{
    public static void main(String[] args) {
        int size = 3;
        if (args.length > 0) try {
            size = Integer.valueOf(args[0]);
        } catch (NumberFormatException nfe) {
            size = 1;
        }

        System.out.println("\n\nMAGIC SQUARE OF SIZE " + size +"\n");
        MagicSquare square = new MagicSquare(size);
        System.out.println(square.toString());
    }
}