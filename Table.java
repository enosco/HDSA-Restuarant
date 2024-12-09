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
 * A Keyed Item that represents a named table with a specific capacity. A table is keyed by its seat count, which may be retrieved with the inherited getKey().
 *
 * @author Jamie Kahle & Scott Eno
 * */

public class Table extends KeyedItem<Integer> {
    
    private String name;

    /**
     * Constructs a new table with the specified name and capacity.
     * @param name the name of this Table
     * @param seats the capacity of this table, which is also its key
     */
    public Table(String name, int seats) {
	super(seats);
	this.name = name;
    }

    /**
     * Returns the name of this Table.
     *
     * @return the name of this Table
     */
    public String getName() {
	return name;
    }

    /**
     * Returns a string representation of this table.
     *
     * @return a string representationn of this table
     */
    public String toString() {
	return "table " + name + " with " + getKey() + " seats";
    }
}
