
public class SortedLListByInheritance<T extends Comparable<? super T>> extends LList<T>
        implements SortedListInterface<T> {

    /**
     * Adds a new entry to this sorted list in its proper order. The list's size is
     * increased by 1.
     * 
     * @param newEntry The object to be added as a new entry.
     */
    @Override
    public void add(T newEntry) {
        // TODO Auto-generated method stub
    	int location = Math.abs(getPosition(newEntry));
    	super.add(location, newEntry);
    }

    /**
     * Removes the first or only occurrence of a specified entry from this sorted
     * list.
     * 
     * @param anEntry The object to be removed.
     * @return True if anEntry was located and removed; otherwise returns false.
     */
    @Override
    public boolean remove(T anEntry) {
        // TODO Auto-generated method stub
    	int location = Math.abs(getPosition(anEntry));
    	if(location > 0) {
    		super.remove(location);
    		return true;
    	}
        return false;
    }

    /**
     * Gets the position of an entry in this sorted list.
     * 
     * @param anEntry The object to be found.
     * @return The position of the first or only occurrence of anEntry if it occurs
     *         in the list; otherwise returns the position where anEntry would occur
     *         in the list, but as a negative integer.
     */
    @Override
    public int getPosition(T anEntry) {
        // TODO Auto-generated method stub
    	int position = 1;
        for (int i = 1; i <= getLength(); i++){
            int comparison = anEntry.compareTo(getEntry(i));
            if (comparison == 0) {
                return position;
            }
            else if (comparison < 0) {
                return -position;
            }
            position++;
        }
        return -position;
    }

    @Override
    public void add(int newPosition, T newEntry) {
        // TODO Auto-generated method stub
    	throw new UnsupportedOperationException("Not this method");
    }

    @Override
    public T replace(int givenPosition, T newEntry) {
        // TODO Auto-generated method stub
    	throw new UnsupportedOperationException("Not this method");
    }

    @Override
    public T[] toArray(T[] result) {
        for (int i = 0; i < getLength(); i++)
            result[i] = getEntry(i + 1);
        return result;
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

}
