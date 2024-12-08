public class SeatedParty extends Party {

    private Table table;

    public SeatedParty(String name, String section, int size, Table table) {
	super(name, section, size);
	this.table = table;
    }

    public String toString() {
	return super.toString() + " at " + table.toString();
    }
}
