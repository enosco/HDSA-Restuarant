/**
 * Seated Party
 *
 * <p>Contains information about a seated party, this is after the party has been seated at a table in a section.<p>
 *
 * @author Jamie Kahle, Scott Eno
 * */

public class ECSeatedParty extends Party {

    private ECTable table;

    public ECSeatedParty(String name, String section, int size, ECTable table) {
	super(name, section, size);
	table.seat(size);
	this.table = table;
    }

    public ECTable getTable() {
	return table;	
    }
    
    public String toString() {
	return super.toString() + " at " + table.toString();
    }
}
