package euler.objects;

import java.util.*;
import java.util.stream.Collectors;

public class Bag<K, V extends Comparable<V>>
{
  final Map<K, SortedSet<V>> bag;

  public Bag() {
    this(10);
  }

  public Bag(final int capacity) {
    bag = new HashMap<>(capacity);
  }

  public void put(final K key, final V value) {
    SortedSet<V> values = bag.get(key);

    if (values == null) {
      values = new TreeSet<>();
      bag.put(key, values);
    }

    values.add(value);
  }

  public Collection<SortedSet<V>> values() {
    return bag.values();
  }

  public Collection<Pair<K, SortedSet<V>>> entries() {
    return bag.entrySet().stream().map(entry -> new Pair<>(entry)).collect(Collectors.toSet());
  }
}
