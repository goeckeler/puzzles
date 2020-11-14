package com.goeckeler.puzzles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.List;

public class MagicSquareDisplayTest
{
    @Test
    public void shouldDisplayTwoByTwo() {
        MagicSquare square = new MagicSquare(2);
        assertEquals("  1  2\n  3  4\n", square.toString(List.of(1,2,3,4)));
    }

    @Test
    public void shouldDisplayMissingElements() {
        MagicSquare square = new MagicSquare(2);
        assertEquals("  1  2\n  3  -\n", square.toString(List.of(1,2,3)));
    }

    @Test
    public void shouldDisplayEmptySquare() {
        MagicSquare square = new MagicSquare(2);
        assertEquals("  -  -\n  -  -\n", square.toString(List.of()));
    }

    @Test
    public void shouldDisplayNullSquare() {
        MagicSquare square = new MagicSquare(2);
        assertEquals("  -  -\n  -  -\n", square.toString(null));
    }
}