/**
 * An abstract KeyedItem with a comparable key.
 */
public abstract class KeyedItem<KT extends Comparable<? super KT>>
{
    private KT searchKey;

    /**
     * Constructs a new KeyedItem
     */
    public KeyedItem(KT key) {
        searchKey = key;
    }  // end constructor

    /**
     * Returns this KeyedItem's specific key
     *
     * @return this KeyedItem's specific key
     */
    public KT getKey() {
        return searchKey;
    } // end getKey
} // end KeyedItem
