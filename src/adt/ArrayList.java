package adt;

//import for using loop through collections, which is ArrayList
import java.util.Iterator; 

public class ArrayList<T> implements ListInterface<T>{

  private T[] array;
  private int numberOfEntries;
  private static final int DEFAULT_CAPACITY = 20;

  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayList(int initialCapacity) {
    numberOfEntries = 0;
    array = (T[]) new Object[initialCapacity];
  }

  @Override
  public boolean add(T newEntry) {
      array[numberOfEntries] = newEntry;
      numberOfEntries++;
      return true;
  }

  @Override
  public int getNumberOfEntries() {
    return numberOfEntries;
  }
  
  @Override
  public T getEntry(int givenPosition) {
    T result = null;

    if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
      result = array[givenPosition - 1];
    }
    return result;
  }
  
  @Override
  public Iterator<T> getIterator() {
    return new ArrayListIterator();
  }
  
  private class ArrayListIterator implements Iterator<T> {
    private int nextIndex;

    private ArrayListIterator() {
      nextIndex = 0;
    }

    @Override
    public boolean hasNext() {
      return nextIndex < numberOfEntries;
    }

    @Override
    public T next() {
      if (hasNext()) {
        T nextEntry = array[nextIndex];
        nextIndex++; // advance iterator
        return nextEntry;
      } else {
        return null;
      }
    }
  }
  
}
