package euler.utils;

import static euler.utils.Utils.faculty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/** takes all subsets of a given size from a given collection */
public class SubSet<T>
{
  private Collection<T> collection;
  private int size = 1;
  
  public SubSet(Collection<T> collection) {
    this(collection, 1);
  }
  
  public SubSet(Collection<T> collection, int sizeOfSubSet) {
    this.collection = collection;
    this.size = Math.max(sizeOfSubSet, 1);
  }
  
  public void setSize(int sizeOfSubSet) {
    this.size = Math.max(sizeOfSubSet, 1);
  }
  
  public int getSize() { return this.size; }
  
  /** list all subsets in one go, may be very memory consuming */
  public List<Set<T>> list() {
    BigInteger total = BigInteger.valueOf(collection.size());
    BigInteger n = faculty(total).divide(faculty(BigInteger.valueOf(getSize())));
    int elements = n.divide(faculty(BigInteger.valueOf(collection.size() - getSize()))).intValue();
    
    List<Set<T>> list = new ArrayList<Set<T>>(elements);
    // deep copy the collection as we will modify it all the time
    LinkedList<T> items = new LinkedList<T>(collection);
    
    select(0, items, new TreeSet<T>(), list);
    return list;
  }
  
  private void select(int start, LinkedList<T> items, Set<T> subset, List<Set<T>> subsets) {
    if (subset.size() == getSize()) {
      // add deep copy as the subset is modified all the time
      subsets.add(new TreeSet<T>(subset));
      return;
    }
    
    for (int index = start; index < items.size(); ++index) {
      T item = items.remove(index);
      subset.add(item);
      select(index, items, subset, subsets);
      subset.remove(item);
      items.add(index, item);
    }
  }
}
