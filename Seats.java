public class Seats implements Comparable<Seats>
{
	int availableSeats;
	int totalSeats;

	public Seats(int seats)
    {
	availableSeats = seats;
	totalSeats = seats;
	}
    
    public int compareTo(Seats seats)
    {
		return -seats.getAvailableSeats().compareTo(availableSeats);
    }

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
    
    public int getTotalSeats()
    {
	return totalSeats;
    }
    
    public Integer getAvailableSeats()
    {
	return availableSeats;
    }
    
    public String toString()
    {
	return ""+availableSeats;
    }
}
