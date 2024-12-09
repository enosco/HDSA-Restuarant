/**
 * class Table
 * <p> Contains the name of a table with the available seats of the table as its key. The seat count of this table can be retrieved with the inherited getKey() method from KeyedItem..
 * <p>
 *
 * @author Jamie Kahle, Scott Eno
 * */

public class Table extends KeyedItem<Integer> {

    private String name;

    /**
     * Constructs a new table.
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
