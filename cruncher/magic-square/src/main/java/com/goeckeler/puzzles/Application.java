package com.goeckeler.puzzles;

public class Application
{
    public static void main(String[] args) {
        for (int size = 3; size <= 6; ++size) {
            MagicSquare square = new MagicSquare(size);
            System.out.println("\n\nMAGIC SQUARE OF SIZE " + size +"\n");
            System.out.println(square.toString());
        }
    }
}