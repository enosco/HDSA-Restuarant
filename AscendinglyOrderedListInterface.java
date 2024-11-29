public interface AscendinglyOrderedListInterface<T,KT>
{   public boolean isEmpty();
    public int size();
    public void add(T item) throws ListIndexOutOfBoundsException;
    public T get(int index) throws ListIndexOutOfBoundsException;
    public T remove(int index) throws ListIndexOutOfBoundsException;
    public void removeAll();
    public int search(KT key);
}
