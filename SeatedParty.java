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
 * Represents a party that has been seated at a table, identified by name, its preferred section, its size, and the table it is seated at. This party is keyed by its name, thus it may be retrieved with getKey().
 *
 * @author Jamie Kahle & Scott Eno
 * */

public class SeatedParty extends Party {
   
    private Table table;

    /**
     * Constructs a new SeatedParty with the specified name, section, size, and table.
     *
     * @param name the name of this Party
     * @param section the preferred section of this Party
     * @param size the size of this Party
     * @param table the table this Party is occupying
     */
    public SeatedParty(String name, String section, int size, Table table) {
	super(name, section, size);
	this.table = table;
    }

    /**
     * Returns the table this SeatedParty is occupying.
     *
     * @return the table this SeatedParty is occupying
     */     
    public Table getTable() {
	return table;	
    }

    /**
     * Returns a string representation of this SeatedParty.
     *
     * @return a string representationn of this SeatedParty
     */
    public String toString() {
	return super.toString() + " at " + table.toString();
    }
}
