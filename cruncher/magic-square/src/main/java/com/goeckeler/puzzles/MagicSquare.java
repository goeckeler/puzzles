package com.goeckeler.puzzles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MagicSquare {
    private final int size;
    private final int magic;
    private List<Integer> solution;

    public MagicSquare(int size) {
        this.size = size;
        this.magic = Arithmetic.sum(size * size) / size;

        solve(magic, 
              List.of(),
              Set.copyOf(IntStream.rangeClosed(1, size * size)
                                     .boxed()
                                     .collect(Collectors.toSet())
                        )
             );
    }

    public boolean isValid() {
        return sumUp(this.solution).stream().allMatch(n -> n == magic) && this.solution.size() == size * size;
    }

    private Set<Integer> sumUp(List<Integer> candidate) {
        if (candidate == null) return Set.of();

        Set<Integer> sums = new HashSet<>(size + size + 2);

        int sumDiagonalDown = 0;
        int sumDiagonalUp = 0;
        for (int row = 0; row < size; ++row) {
            int sumRow = 0;
            int sumCol = 0;

            sumDiagonalDown += valueAt(row * size + row, candidate);
            sumDiagonalUp += valueAt(row * size + size - row - 1, candidate);

            for (int col = 0; col < size; ++col) {
                sumRow += valueAt(col * size + row, candidate);
                sumCol += valueAt(row * size + col, candidate);
            }

            sums.add(sumRow);
            sums.add(sumCol);
        }

        sums.add(sumDiagonalDown);
        sums.add(sumDiagonalUp);

        return sums;
    }
 
    private int valueAt(int index, List<Integer> list) {
        if (index >= list.size()) return 1;
        return list.get(index);
    }

    private boolean solve(int magic, List<Integer> candidate, Set<Integer> numbers) {
        Set<Integer> sums = sumUp(candidate);

        // we have a fully qualified solution candidate
        if (candidate.size() == size * size) {
            if (sums.stream().allMatch(n -> n == magic)) {
                solution = List.copyOf(candidate);

                System.out.println(this.toString(candidate));
                System.out.println();

                return true;
            }

            return false;
        }

        // we need to dive deeper
        for (Integer number : numbers) {
            if (solve(magic, add(candidate, number), take(numbers, number))) {
                return true;
            }
        }

        return false;
    }

    private Set<Integer> take(final Set<Integer> set, final Integer number) {
      Set<Integer> copy = new HashSet<>(set);
      copy.remove(number);
      return copy;
    }

    private List<Integer> add(final List<Integer> list, final Integer number) {
        List<Integer> copy = new ArrayList<>(1 + list.size());
        copy.addAll(list);
        copy.add(number);
        return copy;
    }

    public String toString(final List<Integer> square) {
        StringBuilder string = new StringBuilder();

        int last = size * size;
        for (int index = 0; index < last; ++index) {
            if (square == null || index >= square.size()) {
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

    @Override
    public String toString() {
        return toString(solution);
    }
}
