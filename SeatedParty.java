/**
 * Seated Party
 *
 * <p>Contains information about a seated party, this is after the party has been seated at a table in a section.<p>
 *
 * @author Jamie Kahle, Scott Eno
 * */

public class SeatedParty extends Party {

    private Table table;

    public SeatedParty(String name, String section, int size, Table table) {
	super(name, section, size);
	this.table = table;
    }

    public Table getTable() {
	return table;	
    }
    
    public String toString() {
	return super.toString() + " at " + table.toString();
    }
}
