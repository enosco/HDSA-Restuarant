/**
 * class Table
 * <p> Contains the name of a table with the available seats of the table as its key
 * <p>
 *
 * @author Jamie Kahle, Scott Eno
 * */

public class Table extends KeyedItem<Integer> {

    private String name;
    
    public Table(String name, int seats) {
	super(seats);
	this.name = name;
    }

    public String getName() {
	return name;
    }
        
    public String toString() {
	return "table " + name + " with " + getKey() + " seats";
    }
}
