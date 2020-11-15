package com.goeckeler.puzzles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MagicSquareTest
{
    @Test
    public void shouldSolveSquareWithSizeOne() {
        assertEquals("  1\n", new MagicSquare(1).toString());
    }
 
    @Test
    public void shouldSolveSquareWithSizeThree() {
        MagicSquare square = new MagicSquare(3);
        assertTrue(square.isValid(), String.format("Unbalanced magic square\n%s", square.toString()));
    }
 }