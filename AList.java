

/**
 * A class that implements a list of objects by using an array. Entries in a
 * list have positions that begin with 1. Duplicate entries are allowed. The
 * size of the array doubles until it exceeds the maximum allowed capacity,
 * where upon an exception is thrown.
 *
 * This code is from Chapter 11 of Data Structures and Abstractions with Java
 * 5/e
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 *
 * The toString method is overridden to give a nice display of the items in the
 * list in this format { <1> <2> <3> <4> } Modification by Charles Hoot
 *  
 * An alternate display method has been created to print the list one item 
 * to a line along with the index
 * 
 * Stubs were added for the methods needed to complete Lab 10
 * 
  * @version 5.0
*/

import java.util.Arrays;

class AList<T> implements ListInterface<T> {

    private T[] list; // Array of list entries; ignore list[0]
    private int numberOfEntries; // current number of entries in list
    private boolean integrityOK;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public AList() {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    public AList(int initialCapacity) {
        integrityOK = false;
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY) {
            initialCapacity = DEFAULT_CAPACITY;
        } else // Is initialCapacity too big?
        {
            checkCapacity(initialCapacity);
        }

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[]) new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        integrityOK = true;
    } // end constructor

    /**
     * Throws an exception if this object is not initialized.
     *
     */
    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayBag object is not initialized " + "properly.");
        }
    } // end checkIntegrity

    /**
     * Throws an exception if the desired capacity exceeds the maximum.
     *
     */
    private void checkCapacity(int desiredCapacity) {
        if (desiredCapacity > MAX_CAPACITY) {
            throw new IllegalStateException(
                    "Attempt to create a bag " + "whose capacity exceeds " + "allowed maximum.");
        }
    } // end checkCapacity

    public void add(T newEntry) {
        checkIntegrity();
        list[numberOfEntries + 1] = newEntry;
        numberOfEntries++;
        ensureCapacity();
    } // end add

    // Precondition: The array list has room for another entry
    public void add(int givenPosition, T newEntry) {
        checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)) {
            if (givenPosition <= numberOfEntries) {
                makeRoom(givenPosition);
            }
            list[givenPosition] = newEntry;
            numberOfEntries++;
            ensureCapacity(); // Ensure enough room for next add
        } else {
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
        }
    } // end add

    public T remove(int givenPosition) {
        checkIntegrity();
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            // Assertion: The list is not empty
            T result = list[givenPosition]; // Get entry to be removed
            // Move subsequent entries towards entry to be removed,
            // unless it is last in list
            if (givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }
            list[numberOfEntries] = null;
            numberOfEntries--;
            return result; // Return reference to removed entry
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    } // end remove

    public void clear() {
        numberOfEntries = 0;
    } // end clear

    public T replace(int givenPosition, T newEntry) {
        checkIntegrity();

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
        }

    } // end replace

    public T getEntry(int givenPosition) {
        checkIntegrity();

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return list[givenPosition];
        } else {
            throw new IndexOutOfBoundsException("Illegal position give to getEntry operation.");
        }
    } // end getEntry

    public boolean contains(T anEntry) {
        checkIntegrity();
        boolean found = false;
        int index = 1;
        while (!found && (index <= numberOfEntries)) {
            if (anEntry.equals(list[index])) {
                found = true;
            }
            index++;
        } // end while
        return found;
    } // end contains

    public int getLength() {
        return numberOfEntries;
    } // end getLength

    public boolean isEmpty() {
        return numberOfEntries == 0;
    } // end isEmpty

    public T[] toArray() {
        checkIntegrity();

        // the cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = list[index + 1];
        } // end for

        return result;
    } // end toArray

    // Doubles the size of the array list if it is full.
    private void ensureCapacity() {
        int capacity = list.length - 1;

        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity); // Is capacity too big?
            list = Arrays.copyOf(list, newCapacity + 1);
        }
    } // end ensureCapacity

    /**
     * Makes room for a new entry at newPosition. Precondition: 1 <= newPosition <=
     * numberOfEntries+1; numberOfEntries is list's length before addition.
     * checkInitialization has been called.
     */
    private void makeRoom(int givenPosition) {
        int newIndex = givenPosition;
        int lastIndex = numberOfEntries;
        for (int index = lastIndex; index >= newIndex; index--) {
            list[index + 1] = list[index];
        }
    } // end makeRoom

    /**
     * Shifts entries that are beyond the entry to be removed to the next lower
     * position. Precondition: 1 <= givenPosition < numberOfEntries; numberOfEntries
     * is list's length before removal. checkInitialization has been called.
     */
    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition;
        for (int index = removedIndex; index < numberOfEntries; index++) {
            list[index] = list[index + 1];
        }
    } // end removeGap

    /**
     * Build a string representation of the list
     *
     * @return A string showing the state of the list.
     */
    public String toStringx() {
        String result = "{ ";
        for (int i = 0; i < numberOfEntries; i++) {
            result = result + "<" + list[i + 1] + "> ";
        }
        result = result + "}";

        return result;
    }

    /**
     * Display the list with indices one to a line
     * 
     */
    public void display() {
        for (int index = 1; index <= numberOfEntries; index++) {
            System.out.println(index + ":" + list[index]);
        }
    } // end display

    /**
     * Check to see if two lists are the same.
     * 
     * @param aList Another array list to check this list against.
     * @return True if all the items in this list and the other list are equals.
     */

    public boolean equals(AList<T> aList) {
        boolean result = false; // result of comparison of lists

        int position; // want position available throughout method

        // If the lists have unequal lengths just use the default value of result.
        if (numberOfEntries == (aList.getLength())) {
            boolean allMatch = true;
            position = 1;
            // Scan the equal length lists looking for a non equal pair of items.
            while ((position <= numberOfEntries) && allMatch) {
                // If we find a mismatch allMatch becomes false and we will escape the while
                allMatch = list[position].equals(aList.list[position]);
                position++;
            } // end while
            result = allMatch;
        }

        return result;
    } // end equals

    /*********************************************************************
     * 
     * METHODS TO BE COMPLETED
     * 
     ***********************************************************************/

    /**
     * Reverse the order of items in a list.
     */
    public void reverse() {
        // COMPLETE THIS METHOD
    	if(numberOfEntries > 1) {
    	for(int i = 1, j = this.numberOfEntries; i < j; i++, j--) {
    		T value = list[i];
    		list[i] = list[j];
    		list[j] = value;
    	}
    	}
    }

    /**
     * Cycle the first item to the end of the list.
     */
    public void cycle() {
        // COMPLETE THIS METHOD
    if(numberOfEntries > 1) {
	   T value = list[1];
    	for(int i = 1; i < numberOfEntries; i++){
    		list[i] = list[i+1];
    	}
    	list[numberOfEntries] = value;
   		}
    }

}
