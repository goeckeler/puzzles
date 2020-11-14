package com.goeckeler.puzzles;

import java.util.List;

public class MagicSquare {
    private final int size;
    private final int magic;

    public MagicSquare(int size) {
        this.size = size;
        this.magic = Arithmetic.sum(size * size) / size;
    }

    public String toString(final List<Integer> square) {
      StringBuilder string = new StringBuilder();

      int last = size * size;
      for (int index = 0; index < last; ++index) {
          if (index >= square.size()) {
              string.append("  -");
          } else {
            int current = square.get(index);
            string.append(String.format("%3d", current));
          }
          
          if ((index + 1) % size == 0) {
              string.append("\n");
          }
      }

      return string.toString();
    }
}
