/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 11/7/24
 * Submitted:  11/7/24
 * Comment: test suite and sample run attached
 * Comment: I declare that this is entirely my own work
 * @author: Scott Eno
 * @version: 2024.11.7
 */

/**
 * An ordered collection of comparable items that contains no duplicates.
 * Implements the AscendinglyOrderedListInterface
 *
 * @author Scott Eno
 */
public class AscendinglyOrderedList<T extends KeyedItem<KT>, KT extends Comparable <? super KT>>
    implements AscendinglyOrderedListInterface<T, KT>
{
    /**
     * The initial size of the list.
     */
    private static final int INIT_SIZE = 3;

    /**
     * The backing array storing the items in the list.
     */
    protected T[] items;

    /**
     * The number of items currently in the list.
     */
    protected int numItems;

    /**
     * Constructs an empty ordered list.
     */
    public AscendinglyOrderedList()
    {
        items = (T[]) new KeyedItem[INIT_SIZE];
        numItems = 0;
    }

    /**
     * Returns true if this list is empty.
     *
     * @return true if this list is empty
     */
    public boolean isEmpty()
    {
        return numItems == 0;
    }

    /**
     * Returns the number of items in this list.
     *
     * @return the number of items in this list
     */
    public int size()
    {
        return numItems;
    }

    /**
     * Increases the capacity of this list before inserting the item at the specified index.
     *
     * @param index the index where the item should be inserted
     * @param item the item to be inserted into this list
     */
    private void resize(int index, T item)
    {
        T[] temp = (T[]) new KeyedItem[items.length << 1];

        temp[index] = item; // assign item to be added at appropriate index
        for(int i = 0, offset = 0; i < items.length; i++)
        {
            if(i == index)
            {
                temp[i+1] = items[i];

                // offset++ ensures that the added item is not overwritten and
                // future copies are added to the correct index of temp
                offset++;
            } else
            {
                temp[i+offset] = items[i];
            }
        }

        items = temp;
        numItems++;
    }

    /**
     * Inserts the item at the specified index in this list. Shifts all proceeding items to the right upon insertion.
     *
     * @param index the index where the item should be inserted
     * @param item the item to be inserted into this list
     */
    private void add(int index, T item)
    throws  ListIndexOutOfBoundsException
    {
        if (numItems == items.length)
        {
            resize(index, item);
        }
        else
        {
            for (int pos = numItems-1; pos >= index; pos--)
            {
                items[pos+1] = items[pos];
            }
            items[index] = item;
            numItems++;
        }
    }

    /**
     * Inserts the item to this list in lexiographic order if it is not already in this list.
     *
     * @param item the item to be inserted into this list
     * @throws ListIndexOutOfBoundsException if the item is already present in this list
     */
    public void add(T item) throws ListIndexOutOfBoundsException
    {
        int index = search(item.getKey()); // find ordered position

        if (index < 0)
        {
            index = -(index+1); // get real position

            if (numItems == items.length)
            {
                resize(index, item);
            }
            else
            {
                for (int pos = numItems-1; pos >= index; pos--)
                {
                    items[pos+1] = items[pos];
                }
                items[index] = item;
                numItems++;
            }
        }
        else
        {
            // duplicate, do not add
            // maybe use a ListException?
            throw new ListIndexOutOfBoundsException("List already contains item.");
        }

    }

    /**
     * Returns the item at the specified index in this list.
     *
     * @param index the index to retrieve from
     * @return the item at the specified index in this list
     *
     * @throws ListIndexOutOfBoundsException if the specified index is out of range
     */
    public T get(int index) throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else // invalid index
        {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }
    }

    /**
     * Removes the item at the specified index in this list. Upon removal, any items to the right will be shifted
     * left to fill the gap left by the removal.
     *
     * @param index the index to remove from
     * @return the item that was removed from this list
     *
     * @throws ListIndexOutOfBoundsException if the specified index is out of range
     */
    public T remove(int index) throws ListIndexOutOfBoundsException
    {
        T item;
        if (index >= 0 && index < numItems)
        {
            item = items[index];
            for (int pos = index+1; pos < numItems; pos++)
            {
                items[pos-1] = items[pos];
            }
            items[--numItems] = null;
        }
        else // invalid index
        {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }
        return item;
    }

    /**
     * Empties this list of all items.
     */

    public void removeAll()
    {
        items = (T[]) new KeyedItem[INIT_SIZE];
        numItems = 0;
    }

    /**
     * Returns the index of the specified search key, or the shifted index ( -(index-1) ) of the first
     * item greater than the key if it is not present in this list.<p>
     *
     * Specifically, if the item is found, a valid index in range [0, size() - 1] is returned. If the item is not
     * found, an index within the shifted range [-size(), -1] is returned, corresponding to the index at which
     * the item should be inserted to maintain correct ordering. Upon a failed search, a valid index can be
     * obtained by incrementing, then negating the returned value.<p>
     *
     * Additionally, this method implements Binary Search II (eagar advancing until singleton).
     *
     * @param key the item to search the list for
     * @return the index of the specified item, or the shifted index ( -(index-1) ) of the first
     * item greater than the key if it is not present in this list.
     */
    // implements binary search II
    public int search(KT key)
    {
        int keyPos;

        // define search bounds
        int lower = 0;
        int upper = numItems - 1;

        int mid;

        while (lower < upper) // split until singleton
        {
            mid = (lower + upper) >> 1;

            if (key.compareTo(items[mid].getKey()) > 0)
            {
                lower = mid+1;
            }
            else
            {
                upper = mid;
            }
        }

        // get compare value of item the search ended on -- also handles empty list
        int compareVal;
        if (numItems != 0)
        {
            KT currKey = items[lower].getKey();
            compareVal = key.compareTo(currKey);
        }
        else
        {
            compareVal = -1;
        }

        // determine if key was found or not
        if(compareVal == 0) // item found
        {
            keyPos = lower;
        }
        else if (compareVal > 0) // handle case where key > last item
        {
            keyPos = -numItems;
            keyPos--;
        }
        else // all other cases where key is not found
        {
            keyPos = -lower;
            keyPos--;
        }

        return keyPos;
    }

    /**
     * Returns a string representation of the contents of this list.
     *
     * @return a string representation of the contents of this list
     */
    public String toString()
    {
        StringBuilder contents = new StringBuilder();

        for(int i = 0; i < numItems; i++)
        {
            contents.append(items[i] + "\n");
        }
        return contents.toString();
    }

}
