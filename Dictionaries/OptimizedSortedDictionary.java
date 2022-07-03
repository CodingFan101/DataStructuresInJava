import java.util.Iterator;

/**
   A class that implements the ADT dictionary by using two parallel lists,
   one of which is sorted.
   The dictionary has sorted and distinct search keys.
   Search keys and associated values are not null.
 
   @author Frank M. Carrano
   @version 5.0
 
   NOTE: We are using the sorted list with an iterator,
         which is the solution to Project 6 in Chapter 17.
*/
public class OptimizedSortedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
	class Entry implements Comparable<Entry> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        Entry(K key) {
            this.key = key;
            value = null;
        }

        @Override
        public int compareTo(Entry o) {
            return key.compareTo(o.key);
        }
    };
    private SortedListInterface<Entry> list;
    //private SortedListInterface<K> list; // List of sorted search keys; indexing begins at 1
    //private ListInterface<V> list; // Parallel list of values corresponding the the search keys; indexing begins at
                                        // 1
    private final static int DEFAULT_CAPACITY = 6; // 6 is for testing; must be >= 1
    private static final int MAX_CAPACITY = 10000;

    public OptimizedSortedDictionary() {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end default constructor

    public OptimizedSortedDictionary(int initialCapacity) {
        checkCapacity(initialCapacity); // Ensures that the constructor for ArrayList has a legal argument
        list = new OptimizedSortedList<>();
    } // end constructor

    public V add(K key, V value) {
        if ((key == null) || (value == null))
            throw new IllegalArgumentException("Cannot add null to a dictionary.");
        else {
        	Entry testEntry = new Entry(key, value);
            V result = null;
            int keyPosition = list.getPosition(testEntry); // key cannot be null

            if ((keyPosition > 0) && (keyPosition <= list.getLength())) {
                // Key found, return and replace entry's value
            	Entry exists = list.getEntry(keyPosition);
                result = exists.value; // Get and replace old value
                exists.value = value;
            } else // Key not found (keyPosition is negative); add new entry to dictionary
            {
                // Since list is sorted, we must ask where key was placed,
                // so that we can maintain the parallel nature of list
                // Change sign of keyPosition
                list.add(testEntry);
            } // end if

            return result;
        } // end if
    } // end add

    public V remove(K key) {
        V result = null;
        Entry testEntry = new Entry(key);
        int keyIndex = list.getPosition(testEntry);

        if ((keyIndex > 0) && (keyIndex <= list.getLength())) {
            // Key found; remove entry and return its value
        	Entry exists = list.getEntry(keyIndex);
            result = exists.value;
            list.remove(exists);
        } // end if
          // Else result is null

        return result;
    } // end remove

    public V getValue(K key) {
        V result = null;
        Entry testEntry = new Entry(key);
        int keyIndex = list.getPosition(testEntry);

        if ((keyIndex > 0) && (keyIndex <= list.getLength())) {
        	Entry exists = list.getEntry(keyIndex);
            result = exists.value; // Key found; return value
        } // end if
          // Else result is null

        return result;
    } // end getValue

    public boolean contains(K key) {
    	Entry testEntry = new Entry(key);
        return list.contains(testEntry);
    } // end contains

    public boolean isEmpty() {
        return list.isEmpty();
    } // end isEmpty

    public int getSize() {
        return list.getLength();
    } // end getSize

    public final void clear() {
        list.clear();
    } // end clear

    public Iterator<K> getKeyIterator() {
        throw new UnsupportedOperationException();
    } // end getKeyIterator

    public Iterator<V> getValueIterator() {
        throw new UnsupportedOperationException();
    } // end getValueIterator

    // Ensures that the client requests a capacity
    // that is not too small or too large.
    private void checkCapacity(int capacity) {
        if (capacity < DEFAULT_CAPACITY)
            capacity = DEFAULT_CAPACITY;
        else if (capacity > MAX_CAPACITY)
            throw new IllegalStateException(
                    "Attempt to create a dictionary " + "whose capacity is larger than " + MAX_CAPACITY);
    } // end checkCapacity
} // end OurSortedDictionary
