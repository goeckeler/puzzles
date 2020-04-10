package euler.p049;

import java.util.*;

import euler.objects.Pair;

class Sequencer
{
  final Set<Long> candidates;
  private List<Long> history = new ArrayList<>();
  private Bag<Long, Long> bag = new Bag<>();

  public Sequencer(final Set<Long> candidates) {
    this.candidates = candidates;
    candidates.forEach(i -> use(i));
  }

  private void use(final long number) {
    history.forEach(decimal -> {
      bag.put(number - decimal, number);
      bag.put(number - decimal, decimal);
    });
    history.add(number);
  }

  public String sequence() {
    final StringBuilder text = new StringBuilder();
    for (final Pair<Long, SortedSet<Long>> sequence : bag.entries()) {
      if (sequence.getValue().size() == 3) {
        text.append(sequence.getKey()).append(" : ").append(sequence.getValue().toString()).append("\n");
      }
    }
    return text.toString();
  }
}
