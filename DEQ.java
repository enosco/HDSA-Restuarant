public class DEQ<T> extends Queue<T> implements ExtendedQueueInterface<T>
{
	public T peekBack() throws ExtendedQueueException
	{
		if(numItems != 0)
		{
			return items[(back + items.length-1) % items.length];
		}
		else
		{
			throw new ExtendedQueueException("EQE in peekBack");
		}
	}

	public void enqueueFront(T item) throws ExtendedQueueException
	{
		if(numItems == items.length)
		{
			resize();
		}
		front = (front + items.length-1) % items.length;
		items[front]=item;
		numItems++;
	}

	public T dequeueBack() throws ExtendedQueueException
	{
		if(numItems != 0)
		{
			back = (back + items.length-1) % items.length;
			T ret = items[back];
			items[back] = null;
			numItems--;
			return ret;
		}
		else
		{
			throw new ExtendedQueueException("EQE in dequeueBack");
		}
	}
}
