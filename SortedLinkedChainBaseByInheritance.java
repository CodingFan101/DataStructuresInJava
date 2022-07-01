
public class SortedLinkedChainBaseByInheritance<T extends Comparable<? super T>> extends LinkedChainBase<T>
        implements SortedListInterface<T> {

    private Node getNodeBefore(T anEntry) {
        Node currentNode = getFirstNode();
        Node nodeBefore = null;
        while (currentNode != null && anEntry.compareTo(currentNode.getData()) > 0) {
            nodeBefore = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return nodeBefore;
    }

    /**
     * Adds a new entry to this sorted list in its proper order. The list's size is
     * increased by 1.
     * 
     * @param newEntry The object to be added as a new entry.
     */
    @Override
    public void add(T newEntry) {
        // TODO Auto-generated method stub
    	int numOfEntries = getLength();
    	Node newNode = new Node(newEntry);
    	Node nodeBefore = getNodeBefore(newEntry);
    	if(nodeBefore == null) {
    		addFirstNode(newNode);
    	}
    	else {
    		addAfterNode(nodeBefore, newNode);
    	}
    	numOfEntries++;
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
    	int location = getPosition(anEntry);
    	if(location > 0) {
    		remove(location);
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
        int position = 1;
        Node node = getFirstNode();
        while (node != null) {
            int comparison = anEntry.compareTo(node.getData());
            if (comparison == 0)
                return position;
            else if (comparison < 0)
                return -position;
            position++;
            node = node.getNextNode();
        }
        return -position;
    }

    @Override
    public T getEntry(int givenPosition) {
        checkPosition(givenPosition);
        return getNodeAt(givenPosition).getData();
    }

    @Override
    public boolean contains(T anEntry) {
        Node nodeBefore = getNodeBefore(anEntry);
        return nodeBefore != null && nodeBefore.getNextNode() != null && nodeBefore.getData().equals(anEntry);
    }

    @Override
    public T remove(int givenPosition) {
        checkPosition(givenPosition);
        T data = null;
        if (givenPosition == 1) {
            data = getFirstNode().getData();
            removeFirstNode();
        } else {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            data = nodeBefore.getNextNode().getData();
            removeAfterNode(nodeBefore);
        }
        return data;
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

    private void checkPosition(int givenPosition) {
        if (givenPosition < 1 || givenPosition > getLength())
            throw new IndexOutOfBoundsException("Illegal position given");
    }

}
