package adt;

import java.util.Iterator; //import for using loop through collections, which is ArrayQueue

/**
 * QueueInterface.java An interface for the ADT queue with an additional method that returns an iterator to the queue.
 * @author Ng Chun Shen
 * @version 3.0
 * @param <T>
 */
public interface QueueInterface<T> {

  /**
   * * Task: Adds a new entry to the back of the queue.
   * @param newEntry an object to be added
   */
  public void enqueue(T newEntry);

  /**
   * * Task: Removes and returns the entry at the front of the queue.
   * @return either the object at the front of the queue or, if the queue is empty before the operation, null
   */
  public T dequeue();
  
  /**
   * * Task: Removes and returns the specific entry of the queue based on the index.
   * @param index
   * @return either the object that based on index of the queue or, if the queue is empty before the operation, null
   */
  public T remove(int index);

  /**
   * * Task: Retrieves the entry at the front of the queue.
   * @return either the object at the front of the queue or, if the queue is empty, null
   */
  public T getFront();

  /**
   * * Task: Detects whether the queue is empty.
   * @return true if the queue is empty, or false otherwise
   */
  public boolean isEmpty();

  /**
   * * Task: Removes all entries from the queue.
   */
  public void clear();
  
  /**
   * Task: Gets the number of entries in the queue.
   *
   * @return the integer number of entries currently in the queue
   */
  public int getNumberOfEntries();
  /**
   * * Task: Gets an iterator for the array queue
     * @return 
     * An iterator to traverse the elements in the array queue
   */
  public Iterator<T> getIterator();
}
