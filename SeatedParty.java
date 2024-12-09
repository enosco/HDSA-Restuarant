/**
 * Seated Party
 *
 * <p>Contains information about a seated party, which includes its name, its preferred section, its size, and the table it is seated at. This party is keyed by its name, thus it may be retrieved with getKey().<p>
 *
 * @author Jamie Kahle, Scott Eno
 * */

public class SeatedParty extends Party {
   
    private Table table;

    /**
     * Constructs a new SeatedParty
     */
    public SeatedParty(String name, String section, int size, Table table) {
	super(name, section, size);
	this.table = table;
    }

    /**
     * Returns the table this SeatedParty is occupying..
     *
     * @return the table this SeatedParty is occupying
     */     
    public Table getTable() {
	return table;	
    }

    /**
     * Returns a string representation of this SeatedParty
     *
     * @return a string representationn of this SeatedParty
     */
    public String toString() {
	return super.toString() + " at " + table.toString();
    }
}
