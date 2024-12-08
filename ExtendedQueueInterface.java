public interface ExtendedQueueInterface<T> extends QueueInterface<T>{
  public void enqueueFront(T newItem) throws ExtendedQueueException;
  public T dequeueBack() throws ExtendedQueueException;
  public T peekBack() throws ExtendedQueueException;
}  // end ExtendedQueueInterface

