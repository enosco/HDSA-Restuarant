public class OccupiedTable extends Table
{
    Party occupant;
    public OccupiedTable(String name, int size, Party party)
    {
	super(name, size);
	occupant = party;
    }

    public String getOccupantName()
    {
	return occupant.getName();
    }

    public int getOccupantCount()
    {
	return occupant.getSize();
    }
}
