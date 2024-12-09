/**
 * A tracker of available and maximum seats used alongside ECTable.
 * @author Jamie Kahle
 */
public class Seats implements Comparable<Seats>
{
    private int availableSeats;
    private int totalSeats;

    /**
     * Constructs a new seat tracker with the specified maximum capacity
     *
     * @param seats the maximum capacity of this seat tracker
     */
    public Seats(int seats)
    {
	availableSeats = seats;
	totalSeats = seats;
    }

    /**
     * Compares this seat tracker's available seats with the specified seat count
     *
     * @param seats the seat count to compare to
     * @return < 0 if this seat tracker contains more available seats, > 0 if it contains less, and 0 if the seats are equal
     */
    public int compareTo(Seats seats)
    {
	return -seats.getAvailableSeats().compareTo(availableSeats);
    }


    /**
     * Occupies the specified number of seats.
     *
     * @param seats the number of seats to occupy
     */
    public boolean occupySeats(int seats)
    {
	boolean ret = false;
	if(availableSeats - seats >= 0)
	{
	    availableSeats-=seats;
	    ret = true;
	}
	return ret;
    }

    /**
     * Frees the specified number of seats.
     *
     * @param seats the number of seats to free
     */
    public boolean returnSeats(int seats)
    {
	boolean ret = false;
	if(availableSeats + seats <= totalSeats)
	{
	    availableSeats+=seats;
	    ret = true;
	}
	
	return ret;
    }

    /**
     * Returns the maximum capacity.
     *
     * @return the maximum capacity.
     */
    public int getTotalSeats()
    {
	return totalSeats;
    }

    /**
     * Returns the number of available seats.
     *
     * @return the number of available seats.
     */
    public Integer getAvailableSeats()
    {
	return availableSeats;
    }

    /**
     * Returns a string representation of the number of available seats.
     *
     * @return a string representationn of the number of available seats
     */
    public String toString()
    {
	return ""+availableSeats;
    }
}
