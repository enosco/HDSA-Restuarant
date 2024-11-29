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
	return false;
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
	return null;      
    }    
    
    public boolean seatParty(Party party) {
	// FIXME
	return false;

    }

    public OccupiedTable removeParty(String partyName) {
	// FIXME
	return null;
	
    }

    public String getSectionName() {
	return sectionName;
    }

    public int getAvailableCount() {
	return available.size();
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
    
