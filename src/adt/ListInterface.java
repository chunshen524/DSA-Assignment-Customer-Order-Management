package adt;

//import for using loop through collections, which is ArrayList
import java.util.Iterator;

/**
 * ListInterface.java An interface for the ADT list.Entries in the list have positions 
 * that begin with 1.
 * @author Ng Chun Shen
 * @version 2.0
 * @param <T>
 */
public interface ListInterface<T> {

  /**
   * Task: Adds a new entry to the end of the list. Entries currently in the list 
   * are unaffected. The list's size is increased by 1.
   *
   * @param newEntry the object to be added as a new entry
   * @return true if the addition is successful, or false if the list is full
   */
  public boolean add(T newEntry);

  /**
   * Task: Retrieves the entry at a given position in the list.
   *
   * @param givenPosition an integer that indicates the position of the desired entry
   * @return a reference to the indicated entry or null, if either the list is
   * empty, givenPosition < 1, or givenPosition > getNumberOfEntries()
   */
  public T getEntry(int givenPosition);

  /**
   * Task: Gets the number of entries in the list.
   *
   * @return the integer number of entries currently in the list
   */
  public int getNumberOfEntries();
  
  /**
   * * Task: Gets an iterator for the array list
     * @return 
     * An iterator to traverse the elements in the array list
   */
  public Iterator<T> getIterator();

}
