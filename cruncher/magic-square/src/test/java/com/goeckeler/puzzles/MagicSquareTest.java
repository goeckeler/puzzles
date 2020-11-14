package com.goeckeler.puzzles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.List;

public class MagicSquareTest
{
    @Test
    public void shouldSolveSquareWithSizeOne() {
        assertEquals("  1\n", new MagicSquare(1).toString());
    }
 
    @Test
    public void shouldSolveSquareWithSizeThree() {
        assertEquals("  2  7  6\n  9  5  1\n  4  3  8\n", new MagicSquare(3).toString());
    }
}