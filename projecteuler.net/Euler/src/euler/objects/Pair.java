package euler.objects;

import java.io.Serializable;
import java.util.Map;

/**
 * Pair value holder default implementation
 *
 * Use this if you need to store pairs and you don't want to make use of Map.Entry all the time. This version is
 * immutable and thus read-only after construction.
 *
 * @author Thorsten Goeckeler
 *
 * @param <K> the type of the key value
 * @param <V> the type of the value object
 */
public class Pair<K, V> implements Serializable
{
  private static final long serialVersionUID = -6738464225756530661L;

  protected K key;
  protected V value;

  /** construct an empty pair */
  public Pair() {
    this((K) null);
  }

  /**
   * construct a pair with an empty value
   *
   * @param key the key to be used for this pair, can be null
   */
  public Pair(final K key) {
    this(key, null);
  }

  /**
   * construct a pair with a key and a value
   *
   * @param key the key to be used for this pair, can be null
   * @param value the value to be used for this pair, can be null
   */
  public Pair(final K key, final V value) {
    this.key = key;
    this.value = value;
  }

  /**
   * construct this pair as a shallow copy of an existing map entry
   *
   * @param entry the entry whose values are to be copied over
   */
  public Pair(final Map.Entry<K, V> entry) {
    if (entry == null) {
      this.key = null;
      this.value = null;
    } else {
      this.key = entry.getKey();
      this.value = entry.getValue();
    }
  }

  /** @return the key of this pair, can be null */
  public K getKey() {
    return key;
  }

  /**
   * @return the current value of this pair, can be null
   */
  public V getValue() {
    return value;
  }

  /**
   * compute the hash code of this pair
   *
   * @return a unique hash code made up from the key alone
   */
  @Override
  public int hashCode() {
    return (key != null ? key.hashCode() : 11);
  }

  /**
   * check for same values against any other pair
   *
   * @param other the other pair to check against
   *
   * @return true if the given objects and all values of this pair match, otherwise false
   */
  @Override
  public boolean equals(final Object other) {
    if (other == null) return false;
    if (this == other) return true;
    if (!this.getClass().isAssignableFrom(other.getClass())) return false;

    final Pair<?, ?> that = (Pair<?, ?>) other;

    boolean equals = true;
    if (this.getKey() == null || that.getKey() == null) {
      equals = this.getKey() == null && that.getKey() == null;
    } else {
      equals = this.getKey().toString().equals(that.getKey().toString());
    }

    if (equals) {
      if (this.getValue() == null || that.getValue() == null) {
        equals = this.getValue() == null && that.getValue() == null;
      } else {
        equals = this.getValue().toString().equals(that.getValue().toString());
      }
    }

    return equals;
  }

  @Override
  public String toString() {
    final StringBuilder text = new StringBuilder();
    text.append("(").append(getKey() == null ? "?" : getKey()).append(", ");
    text.append(getValue() == null ? "?" : getValue()).append(")");
    return text.toString();
  }
}
