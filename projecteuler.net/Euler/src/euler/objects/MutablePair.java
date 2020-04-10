package euler.objects;

import java.io.Serializable;
import java.util.Map;

/**
 * Mutable Pair value holder
 *
 * Use this if you need to store pairs and you don't want to make use of Map.Entry all the time. This instance can
 * change its values.
 *
 * @author Thorsten Goeckeler
 *
 * @param <K> the type of the key value
 * @param <V> the type of the value object
 */
public class MutablePair<K, V> extends Pair<K, V> implements Serializable
{
  private static final long serialVersionUID = -7209573161679142251L;

  /** construct an empty pair */
  public MutablePair() {
    super((K) null);
  }

  /**
   * construct a pair with an empty value
   *
   * @param key the key to be used for this pair, can be null
   */
  public MutablePair(final K key) {
    super(key, null);
  }

  /**
   * construct a pair with a key and a value
   *
   * @param key the key to be used for this pair, can be null
   * @param value the value to be used for this pair, can be null
   */
  public MutablePair(final K key, final V value) {
    super(key, value);
  }

  /**
   * construct this pair as a shallow copy of an existing map entry
   *
   * @param entry the entry whose values are to be copied over
   */
  public MutablePair(final Map.Entry<K, V> entry) {
    super(entry);
  }

  /**
   * set a new key for this pair
   *
   * Attention, setting a new key will also affect stored values in a map, so whenever you want to set the key, do it
   * before you use a pair otherwise. Never change the key of an existing pair if you don't remove it from all
   * collections beforehand.
   *
   * @param key the new key for this pair, can be null
   */
  public void setKey(final K key) {
    this.key = key;
  }

  /**
   * set a new value for this pair
   *
   * Setting a new value has no side effects, opposite to setting a new key.
   *
   * @param value the new value for this pair, can be null
   */
  public void setValue(final V value) {
    this.value = value;
  }

  /**
   * define this pair as a deep copy of an existing map entry
   *
   * @param entry the entry whose values are to be copied over
   * @return this pair to facilitate <code>new Pair().setEntry(...).getKey();</code> and such
   */
  public MutablePair<K, V> setEntry(final Map.Entry<K, V> entry) {
    if (entry == null) {
      setKey(null);
      setValue(null);
    } else {
      setKey(entry.getKey());
      setValue(entry.getValue());
    }

    return this;
  }
}
