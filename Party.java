/**
 * Party class
 *
 * <p>
 * Contains information about the size, section, and name of a party of customers. The party's name is used as its key, and thus the name is retrieved with getKey() inherited from KeyedItem.<p>
 *
 * @author Jamie Kahle, Scott Eno
 *
 **/

public class Party extends KeyedItem<String>
{
    private int size;
    private String section;

    /**
     * Constructs a new party
     */
    public Party(String name, String section, int size) {
	super(name);
	this.section = section;
	this.size = size;
    }

    /**
     * Returns the size of this Party
     *
     * @return the size of this Party
     */
    public int getSize() {
	return size;
    }

    /**
     * Returns the name of this Party's preferred section
     *
     * @return the name of this Party's preferred section
     */
    public String getSection()
    {
	return section;
    }

    /**
     * Returns a string representation of this table.
     *
     * @return a string representationn of this table
     */
    public String toString() {
	return "Customer " + getKey() + " party of " + size + "(" + section + ")";
    }
}
