/**
 * class ECTable
 * <p> Contains the name of a table with the available seats of the table as its key
 * <p>
 *
 * @author Jamie Kahle, Scott Eno
 * */

public class ECTable extends KeyedItem<Seats> {

    private String name;
    
    public ECTable(String name, int seats) {
	super(new Seats(seats));
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void seat(int seats)
    {
	    this.getKey().occupySeats(seats);
    }

    public void returnSeats(int seats)
    {
	    this.getKey().returnSeats(seats);
    }

    public Seats getSeats()
    {
	    return this.getKey();
    }

    public String toString() {
	return "table " + name + " with " + getKey() + " spare seats";
    }
}
