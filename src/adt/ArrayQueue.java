package adt;

//import for using loop through collections, which is ArrayQueue
import java.util.Iterator; 

public class ArrayQueue<T> implements QueueInterface<T> {

  private T[] array;
  private final static int frontIndex = 0;
  private int backIndex;
  private static final int DEFAULT_CAPACITY = 100;

  public ArrayQueue() {
    this(DEFAULT_CAPACITY);
  }

  public ArrayQueue(int initialCapacity) {
    array = (T[]) new Object[initialCapacity];
    backIndex = -1;
  }

  //Add the given newEntry to the back of the queue
  @Override
  public void enqueue(T newEntry) {
    if (!isArrayFull()) {
      backIndex++;
      array[backIndex] = newEntry;
    }
  }

  //Removes and returns the entry at the front of the queue.
  @Override
  public T dequeue() {
    T front = null;
    if (!isEmpty()) {
      front = array[frontIndex];      // shift remaining array items forward one position      
      for (int i = frontIndex; i < backIndex; ++i) {
        array[i] = array[i + 1];
      }
      backIndex--;
    }
    return front;
  }
  
  //Removes and returns the specific entry of the queue based on the given index.
  @Override
  public T remove(int index){
    T obj = array[index];
    for (int i = index; i <= backIndex; i++) {
      array[i] = array[i+1];
    }
    backIndex--;
    return obj;
  }
   
  //Retrieves the entry at the front of the queue.
  @Override
  public T getFront() {
    T front = null;
    if (!isEmpty()) {
      front = array[frontIndex];
    }
    return front;
  }
  
  //Check if the queue is empty and has no entry.
  @Override
  public boolean isEmpty() {
    return frontIndex > backIndex;
  }

  //Removes all entries from the queue.
  @Override
  public void clear() {
    if (!isEmpty()) { // deallocates only the used portion      
      for (int index = frontIndex; index <= backIndex; index++) {
        array[index] = null;
      }
      backIndex = -1;
    }
  }

  //Check if the queue is full.
  private boolean isArrayFull() {
    return backIndex == array.length - 1;
  }
  
  //Retrieve the number of entries in the queue.
  @Override
  public int getNumberOfEntries() {
    return backIndex;
  }
  
  //Gets an iterator for the array queue
  @Override
  public Iterator<T> getIterator() {
    return new ArrayQueueIterator();
  }
  
  private class ArrayQueueIterator implements Iterator<T> {
    private int nextIndex;

    private ArrayQueueIterator() {
      nextIndex = 0;
    }

    //Checks if the array queue has a next element
    @Override
    public boolean hasNext() {
      return nextIndex <= backIndex;
    }

    //Gets the next element in the array queue
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
