
public class OptimizedSortedList<T extends Comparable<? super T>> implements SortedListInterface<T> {
    private ListInterface<T> list = new AList<>();

    @Override
    public void add(T newEntry) {
        list.add(Math.abs(getPosition(newEntry)), newEntry);
    }

    @Override
    public boolean remove(T anEntry) {
        int position = getPosition(anEntry);
        if (position > 0)
            list.remove(position);
        return position > 0;
    }
    
    private int binarySearch(int first, int last, T value) {
        int mid = first + (last - first) / 2;
        if (first > last)
            return -mid;
        else {
            int comp = value.compareTo(list.getEntry(mid));
            if (comp == 0)
                return mid;
            else if (comp < 0)
                return binarySearch(first, mid - 1, value);
            else
                return binarySearch(mid + 1, last, value);
        }
    }
    
    @Override
    public int getPosition(T anEntry) {
    	int initial = 1;
    	int last = list.getLength();
        return binarySearch(initial, last, anEntry);
    }

    @Override
    public T getEntry(int givenPosition) {
        return list.getEntry(givenPosition);
    }

    @Override
    public boolean contains(T anEntry) {
        return getPosition(anEntry) > 0;
    }

    @Override
    public T remove(int givenPosition) {
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
}
