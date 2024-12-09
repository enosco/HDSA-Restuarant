/**
 * Modified Table that keeps track of its available and occupied seats.
 *
 * @author Jamie Kahle & Scott Eno
 * */

public class ECTable extends KeyedItem<Seats> {

    private String name;

    /**
     * Constructs a new table with the specified name and capacity.
     * @param name the name of this Table
     * @param seats the maximum capacity of this table
     */
    public ECTable(String name, int seats) {
	super(new Seats(seats));
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
     * Occupies the specified number of seats.
     *
     * @param seats the number of seats to occupy
     */
    public void seat(int seats)
    {
	    this.getKey().occupySeats(seats);
    }

    /**
     * Frees the specified number of seats.
     *
     * @param seats the number of seats to free
     */
    public void returnSeats(int seats)
    {
	    this.getKey().returnSeats(seats);
    }

    /**
     * Returns the number of available seats.
     *
     * @return the number of available seats.
     */
    public Seats getSeats()
    {
	    return this.getKey();
    }

    /**
     * Returns a string representation of this table.
     *
     * @return a string representationn of this table
     */
    public String toString() {
	return "table " + name + " with " + getKey() + " spare seats";
    }
}
