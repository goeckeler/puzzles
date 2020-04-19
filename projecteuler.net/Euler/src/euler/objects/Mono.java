package euler.objects;

import java.io.Serializable;

/**
 * Mono value holder default implementation
 *
 * Use this if you need to store a single value. This version is immutable and thus read-only after construction.
 * Probably makes more sense if you respect tuples as collections of mono values.
 *
 * @author Thorsten Goeckeler
 *
 * @param <T> the value type
 */
public class Mono<T> implements Serializable
{
  private static final long serialVersionUID = 3005690755403697559L;

  protected T value;

  /** construct an empty value */
  public Mono() {
    this((T) null);
  }

  /**
   * construct a mono with the given value
   *
   * @param value the value of this item, can be null
   */
  public Mono(final T value) {
    this.value = value;
  }

  /**
   * @return the current value, can be null
   */
  public T getValue() {
    return value;
  }

  /**
   * compute the hash code of this value
   *
   * @return a unique hash code
   */
  @Override
  public int hashCode() {
    return (value != null ? value.hashCode() : 17);
  }

  /**
   * check for same values against any other mono value
   *
   * @param other the other mono to check against
   *
   * @return true if the values match, otherwise false
   */
  @Override
  public boolean equals(final Object other) {
    if (other == null) return false;
    if (this == other) return true;
    if (!this.getClass().isAssignableFrom(other.getClass())) return false;

    final Mono<?> that = (Mono<?>) other;

    boolean equals = true;
    if (this.getValue() == null || that.getValue() == null) {
      equals = this.getValue() == null && that.getValue() == null;
    } else {
      equals = this.getValue().toString().equals(that.getValue().toString());
    }

    return equals;
  }

  @Override
  public String toString() {
    return getValue() == null ? "?" : getValue().toString();
  }
}
