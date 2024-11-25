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

public class Queue<T> implements QueueInterface<T>
{
    protected T[] items;
    protected int front, back, numItems;

    public Queue()
    {
        items = (T[]) new Object[3];
        numItems = front = back = 0;
    }

    public boolean isEmpty()
    {
        return numItems == 0;
    }

    public boolean isFull()
    {
        return numItems == items.length;
    }

    protected void resize()
    {
        T[] temp = (T[]) new Object[items.length*2];

        // circular incrementation handles
        // case where back has wrapped around
        for(int i = 0; i < numItems; i++)
        {
            temp[i] = items[front];
            front = (front+1) % items.length;
        }

        front = 0;
        back = numItems;

        items = temp;
    }

    public void enqueue(T newItem)
    {
        if(numItems == items.length) // if full, resize
        {
            resize();
        }

        items[back] = newItem;
        back = (back+1) % items.length; // circular decrement

        numItems++;
    }

    public T dequeue() throws QueueException
    {
        if(numItems != 0)
        {
            T item = items[front];
            items[front] = null; // avoid memory leak
            front = (front+1) % items.length; // circular increment

            numItems--;
            return item;
        }
        else
        {
            throw new QueueException("Queue is Empty.");
        }

    }

    public void dequeueAll()
    {
        items = (T[]) new Object[3];
        numItems = front = back = 0;
    }

    public T peek() throws QueueException
    {
        if(numItems != 0)
        {
            return items[front];
        }
        else
        {
            throw new QueueException("Queue is Empty.");
        }
    }

    // collects from front to back no matter the order of the items
    public String toString()
    {
        StringBuilder output = new StringBuilder();

        int cursor = front;
        for(int i = 0; i < numItems; i++)
        {
            output.append(items[cursor]+"\n");
            cursor = (cursor+1) % items.length; // circular increment
        }

        return output.toString();
    }
}
