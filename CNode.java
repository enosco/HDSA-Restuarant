public class CNode<T>
{
    private T item;
    private CNode<T> next;
    private CNode<T> back;

    public CNode(T newItem)
    {
        item = newItem;
        next = this;
        back = this;
    } // end constructor

    public CNode(T newItem, CNode<T> nextNode, CNode<T> prevNode)
    {
        item = newItem;
        next = nextNode;
        back = prevNode;
    } // end constructor

    public void setItem(T newItem)
    {
        item = newItem;
    } // end setItem

    public T getItem()
    {
        return item;
    } // end getItem

    public void setNext(CNode<T> nextNode)
    {
        next = nextNode;
    } // end setNext

    public CNode<T> getNext()
    {
        return next;
    } // end getNext

    public void setBack(CNode<T> prevNode)
    {
        back = prevNode;
    } // end setBack

    public CNode<T> getBack()
    {
        return back;
    } // end getBack


    public String toString()
    {
        return item+" ";
    } //end toString
} // end class Node
