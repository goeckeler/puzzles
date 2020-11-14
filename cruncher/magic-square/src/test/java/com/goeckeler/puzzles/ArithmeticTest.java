package com.goeckeler.puzzles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ArithmeticTest 
{
    @Test
    public void shouldCalculateArithmeticSum()
    {
        assertEquals( 3 , Arithmetic.sum(2));
        assertEquals( 45 , Arithmetic.sum(9));
    }
}
