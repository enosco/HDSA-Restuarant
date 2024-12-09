/**
 * Represents a party that has been seated at a table, identified by name, its preferred section, its size, and the table it is seated at. This party is keyed by its name, thus it may be retrieved with getKey().
 *
 * @author Jamie Kahle & Scott Eno
 * */
public class ECSeatedParty extends Party {

    private ECTable table;

    /**
     * Constructs a new SeatedParty with the specified name, section, size, and table.
     *
     * @param name the name of this Party
     * @param section the preferred section of this Party
     * @param size the size of this Party
     * @param table the table this Party is occupying
     */
    public ECSeatedParty(String name, String section, int size, ECTable table) {
	super(name, section, size);
	table.seat(size);
	this.table = table;
    }

    /**
     * Returns the table this seated party is occupying.
     *
     * @return the table this seated party is occupying
     */  
    public ECTable getTable() {
	return table;	
    }

     /**
     * Returns a string representation of this seated party.
     *
     * @return a string representationn of this seated party
     */
    public String toString() {
	return super.toString() + " at " + table.toString();
    }
}
