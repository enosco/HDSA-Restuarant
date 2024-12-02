
public class Section
{
    /*
     * These two collections might be better as a
     * ascendingly sorted list, sorted by seat count
     * to make seating by the lowest bound easier
     *
     * 
     */
    String sectionName;
    AscendinglyOrderedListD<Table, Integer> available;
    AscendinglyOrderedListD<OccupiedTable, Integer> occupied;

    public Section(String name)
    {
        this.sectionName = name;
	available = new AscendinglyOrderedListD<Table, Integer>();
	occupied = new AscendinglyOrderedListD<OccupiedTable, Integer>();
    }

    public boolean hasParty(String partyName)
    {
	// FIXME
	boolean found = false;
	int size = occupied.size();
	for (int i = 0; !found && i < size; i++) {
	    String currName = occupied.get(i).getOccupantName();
	    
	    if (currName.equals(partyName)) {
		found = true;
	    }
	}
	

	return !found;
    }
    
    public boolean hasTable(String tableName)
    {
	boolean found = false;
	
	int size = available.size();
	for (int i = 0; !found && i < size; i++) {
	    String currName = available.get(i).getName();
	    
	    if (currName.equals(tableName)) {
		found = true;
	    }
	}

	size = occupied.size();
	for (int i = 0; !found && i < size; i++) {
	    String currName = occupied.get(i).getName();
	    
	    if (currName.equals(tableName)) {
		found = true;
	    }
	}
	
	return found;
    }

    // Note: add logic that prevents
    // duplicates from being added even though the
    // driver is currently responsible for that
    //
    // The Driver manually checks hasTableName() because
    // the sample runs show that the name is checked for uniqueness
    // before the table is added.
//    public boolean addTable(Table table) {
    public void addTable(Table table) {
	// TEMP SOLUTION
       	available.add(table);	    
    }

    public Table removeTable(String tableName) {
	// FIXME
	
	int size = available.size();
	Table ret = null;
	boolean removed = false;

	for ( int i=0; i<size && !removed; i++)
	{
		Table t = available.get(i);
		if(t.getName().equals(tableName))
		{
			ret = available.remove(t.getCapacity());
			removed = true;
		}
	}
	return ret;      
    }    
    
    public boolean seatParty(Party party) {
	// FIXME
	
	int size = available.size();
	
	boolean seated = false;

	for( int i = 0; i< size && !seated; i++)
	{
		Table t =  available.get(i);
		if(!(t.getCapacity() < party.getSize()))
		{
			occupied.add(new OccupiedTable(t.getName(), t.getCapacity(), party));
			available.remove(t.getKey());
			seated = true;
		}
	}

	return seated;

    }

    public OccupiedTable removeParty(String partyName) {
	// FIXME
	

	int size = occupied.size();
	OccupiedTable ret = null;
	boolean removed = false;

	for ( int i=0; i<size && !removed; i++)
	{
		OccupiedTable t = occupied.get(i);
		if(t.getOccupantName().equals(partyName))
		{
			ret = occupied.remove(t.getCapacity());
			available.add(new Table(t.getName(), t.getCapacity()));
			removed = true;
		}
	}
	return ret;

	
    }

    public String getSectionName() {
	return sectionName;
    }

    public boolean hasAvailableTables() {
	return available.size() > 0;
    }
   
    public int getAvailableCount() {
	return available.size();
    }

    public boolean hasCustomers() {
	return occupied.size() > 0;
    }
   
    public int getServingCount() {
	return occupied.size();
    }
    
    public String getAvailableTables() {
	// FIXME	       
	return available.toString();
    }

    public String getServing() {
	// FIXME
	return null;
    }
}
    
