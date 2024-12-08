public class Section {
    
    private String sectionName;
    private AscendinglyOrderedListD<Table, Integer> availableTables; // sorted by table name
    private AscendinglyOrderedList<SeatedParty, String> servingParties;
    private AscendinglyOrderedList<Name, String> tableNames;
    
    public Section(String name) {
        this.sectionName = name;
	available = new AscendinglyOrderedList<Table, String>();
	occupied = new AscendinglyOrderedList<SeatedParty, String>();
	tableNames = new AscendinglyOrderedList<NamedItem, String>();
    }
    
    public boolean hasTable(String tableName) {		      	
	boolean result;

	if (tableNames.search(tableNames) != null) {
	    result = true;
	} else {
	    result = false;
	}

	return result;
	
    }

    public boolean addTable(Table table) {

	boolean added;
	if (hasTable(table.getName())) {
		availableTables.add(table);
	    }

	return added;
    }

    public Table removeTable(String tableName) {
    //throws RemoveOccupiedTableException       
	// search available for table name
	   // if it exists, remove and return
	// else, search occupied for table
	   // if it exists, throw exception
	// else return null

	Table target;
	
	if (hasTable(tableName)) {

	    boolean removed = false;
	    int size = available.size();	   
	    for (int i = 0; !removed && i < size; i++) {		
		if (available[i].getName().equals(tableName)) {
		    available.remove(i);
		    removed = true;
		}
	    }

	    if (!removed) {
		// throws custom exception here, making it null for now
		target = null;
	    }
		
	} else {
	    target = null;
	}
	
	return result;
    }    
    
    public boolean seatParty(Party party) {
	// FIXME
	
	int size = available.size();
	
	boolean seated = false;
	/*
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
	*/
	return seated;

    }

    public SeatedParty removeParty(String partyName) 
    {
	// FIXME: Return      

	OccupiedTable ret = null;
	boolean removed = false;

	int size = occupied.size();
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
    
