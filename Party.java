/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 12/9/24
 * Submitted:  12/9/24
 * Comment: test suite and sample run attached
 * Comment: I declare that this is entirely my own work
 * @author: Scott Eno, Jamie Kahle
 * @version: 2024.12.9
 */

/**
 * A Keyed Item that represents a party of customers with a name, size, and preferred section. A party is keyed by its name, which may be retrieved with the inherited getKey() method.
 *
 * @author Jamie Kahle & Scott Eno
 *
 **/

public class Party extends KeyedItem<String>
{
    private int size;
    private String section;

    /**
     * Constructs a new party with the specified name, preferred section, and size.
     *
     * @param name the name of this Party
     * @param section the preferred section of this Party
     * @param size the size of this Party
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
