

public class SortedAListByComposition<T extends Comparable<? super T>> implements SortedListInterface<T> {
    private ListInterface<T> list = new AList<>();

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
    	list.add(location, newEntry);
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
    		list.remove(location);
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
        for (int i = 1; i <= list.getLength(); i++){
            int comparison = anEntry.compareTo(list.getEntry(i));
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
    public T getEntry(int givenPosition) {
        checkPosition(givenPosition);
        return list.getEntry(givenPosition);
    }

    @Override
    public boolean contains(T anEntry) {
        return list.contains(anEntry);
    }

    @Override
    public T remove(int givenPosition) {
        checkPosition(givenPosition);
        return list.remove(givenPosition);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int getLength() {
        return list.getLength();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public T[] toArray() {
        return list.toArray();
    }

    @Override
    public T[] toArray(T[] result) {
        for (int i = 0; i < list.getLength(); i++)
            result[i] = list.getEntry(i + 1);
        return result;
    }

    private void checkPosition(int givenPosition) {
        if (givenPosition < 1 || givenPosition > getLength())
            throw new IndexOutOfBoundsException("Illegal position given");
    }

}
