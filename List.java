/*
 * Purpose: Data Structure and Algorithms Lab 6 Problem 2,3,4
 * Status: Complete and thoroughly tested
 * Last update: 10/10/24
 * Submitted:  10/10/24
 * Comment: test suite and sample run attached
 * Comment: I declare that this is entirely my own work
 * @author: Scott Eno
 * @version: 2024.10.10
 */

import java.util.ListIterator;
import java.util.NoSuchElementException;

//public class ListCDLS implements ListInterfaceR
public class List<T> implements ListInterface<T>
{
    // reference to linked list of items
    private CNode<T> head;
    private int numItems; // number of items in list

    public List()
    {
        numItems = 0;
        head = null;
    }

    public boolean isEmpty()
    {
        return numItems == 0;
    }

    public int size()
    {
        return numItems;
    }

    // traverse list and find item at index
    private CNode<T> find(int index)
    {
        CNode<T> curr = head;

        int count = 0;

        // +1 should be added to avoid an extra traversal when
        // accessing the exact middle item.
        if(index < (numItems >> 1) + 1)
        {
            for(int i = 0; i < index; i++)
            {
                count++;
                curr = curr.getNext();
            }
        }
        else
        {
            for(int i = 0; i < numItems - index; i++)
            {
                count++;
                curr = curr.getBack();
            }
        }
        return curr;
    }

    public T get(int index)
    throws ListIndexOutOfBoundsException
    {
        //validate index
        if(index >= 0 && index < numItems)
        {
            return find(index).getItem();
        }
        else
        {
            throw new ListIndexOutOfBoundsException("List index out of bounds.");
        }
    }

    public void add(int index, T item)
    throws ListIndexOutOfBoundsException
    {
        if(index >= 0 && index <= numItems) // validate index
        {
            if(numItems == 0) // special case: empty list -> size 1 list
            {
                head = new CNode<T>(item);	// first node is self referencing
            }
            else
            {
                CNode<T> curr = find(index);
                CNode<T> prev = curr.getBack();

                CNode<T> newNode = new CNode<T>(item, curr, prev);

                curr.setBack(newNode);
                prev.setNext(newNode);

                // special case: must update head adding at index 0
                head = (index == 0) ? newNode : head;

            }
            numItems++;
        }
        else
        {
            throw new ListIndexOutOfBoundsException("List index out of bounds.");
        }
    }

    public T remove(int index)
    throws ListIndexOutOfBoundsException
    {
        //validate index
        if(index >= 0 && index < numItems)
        {
            T item;

            if(numItems == 1) // special case: size 1 list -> empty list
            {
                item = head.getItem();
                head = null; // last item is garbage collected, list now empty
            }
            else
            {

                // While this implementation requires 2 additional objects be created,
                // it requires the least traversals be used.
                CNode<T> curr = find(index);
                CNode<T> prev = curr.getBack();
                CNode<T> next = curr.getNext();

                item = curr.getItem();

                prev.setNext(next);
                next.setBack(prev);

                head = (index == 0) ? next : head;
            }
            numItems--;
            return item;
        }
        else
        {
            throw new ListIndexOutOfBoundsException("List index out of bounds.");
        }
    }

    public void removeAll()
    {
        head = null;
        numItems = 0;
    }

    public String toString()
    {
        StringBuilder output = new StringBuilder();

        CNode<T> curr = head;
        for(int i = 0; i < numItems; i++)
        {
            output.append(curr);
            curr = curr.getNext();
        }
        return output.toString();
    }

    public String toStringR()
    {
        StringBuilder output = new StringBuilder();

        CNode<T> curr = head;
        for(int i = 0; i < numItems; i++)
        {
            curr = curr.getBack();
            output.append(curr);
        }
        return output.toString();
    }

    public ListIterator listIterator()
    {
        return new ListSLSListIterator();
    }

    private class ListSLSListIterator implements ListIterator
    {
        private CNode<T> cursor;
        private int index;

        public ListSLSListIterator()
        {
            cursor = head;
            index = 0;
        }

        public boolean hasNext()
        {
            return index < numItems;
        }

        public Object next()
        {
            if(hasNext())
            {
                Object item = cursor.getItem();
                cursor = cursor.getNext();
                index++;

                return item;
            }
            {
                throw new NoSuchElementException();
            }
        }

        public boolean hasPrevious()
        {
            return index > 0;
        }

        public Object previous()
        {
            if(hasPrevious())
            {
                cursor = cursor.getBack();
                index--;

                return cursor.getItem();
            }
            else
            {
                throw new NoSuchElementException();
            }

        }

        // returns next index or list size if at end of list
        public int nextIndex()
        {
            return (hasNext()) ? index : numItems;
        }

        // returns previous index or -1 if at beginning of list
        public int previousIndex()
        {
            return (hasPrevious()) ? index-1 : -1;
        }

        /***** unsupported operations *****/

        public void add(Object item)
        {
            throw new UnsupportedOperationException();
        }

        public void set(Object item)
        {
            throw new UnsupportedOperationException();
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}
