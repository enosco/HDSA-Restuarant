/**
 * Section
 *
 * <p> Contains information about a certain section including the names of the tables, the parties that are seated, and the available tables <p>
 *
 * @author Jamie Kahle, Scott Eno
 *
 * */


public class ECSection {
    
    private String sectionName;
    private AscendinglyOrderedListD<ECTable, Seats> available; // sorted by table name
    private AscendinglyOrderedList<ECSeatedParty, String> serving;
    private AscendinglyOrderedList<Name, String> tableNames;
    
    public ECSection(String name) {
        this.sectionName = name;
	this.available = new AscendinglyOrderedListD<ECTable, Seats>();
	this.serving = new AscendinglyOrderedList<ECSeatedParty, String>();
	this.tableNames = new AscendinglyOrderedList<Name, String>();
    }
    
    public boolean hasTable(String tableName) {		      	
	boolean result;

	if (tableNames.search(tableName) < 0) {
	    result = false;
	} else {
	    result = true;
	}

	return result;	
    }
    /**
     * Adds a table to the section
     *
     * @param 	table 	the table that is to be added
     * @return		true if it was successful, false otherwise
     * */
    public boolean addTable(ECTable table) {
	
	boolean added;
	if (!hasTable(table.getName())) {
	    available.add(table);
	    tableNames.add(new Name(table.getName()));
	    added = true;
	} else {
	    added = false;
	}

	return added;
    }
    /**
     * Removes an available table from this section
     *
     * @param	tableName	The name of the table to be removed
     * @return			The table that is reomved, null if the removal failed
     * */
    public ECTable removeTable(String tableName) {
    //throws RemoveOccupiedTableException       
	// search available for table name
	   // if it exists, remove and return
	// else, search occupied for table
	   // if it exists, throw exception
	// else return null

	ECTable target;
	
	if (hasTable(tableName)) {

	    target = null;
	    
	    boolean removed = false;
	    int size = available.size();	   
	    for (int i = 0; !removed && i < size; i++) {		
		if (available.get(i).getName().equals(tableName)) {
		    target = available.remove(i);		    
		    removed = true;
		}
	    }

	    if (target == null) {
		// throw exception here
	    }
		
	} else {
	    target = null;
	}
	
	return target;
    }    

    /**
     * Seats a party in this section
     * 
     * @param	party	the party to be seated
     * @return 		The table the party is seated at
     * */
    public ECTable seatParty(Party party) {		

	int index = available.search(new Seats(party.getSize()));
	
	if (index < 0) {
	    index = -(index+1);
	}

	ECTable table;
	if (index == available.size()) {
//	    seated = false;
	    table = null;
	} else {
	    table = available.remove(index);
	    serving.add(new ECSeatedParty(party.getKey(), party.getSection(), party.getSize(), table));

	    if(table.getSeats().getAvailableSeats() >0)
	    {
		    available.add(table);
	    }
	}
	return table;
    }
    /**
     * removes a party from this section
     *
     * @param	partyName	the name of the party to be removed
     * @return			the SeatedParty that was removed, null if failed
     * */
    public ECSeatedParty removeParty(String partyName) {
	ECSeatedParty party;

	int index = serving.search(partyName);

	if (index < 0) {
	    party = null;
	} else {
	    party = serving.remove(index);

	    ECTable table = party.getTable();

	    if(table.getSeats().getAvailableSeats()==0)
	    {
	    	available.add(table);
	    }

	    table.returnSeats(party.getSize());
	}
	
	return party;
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
	return serving.size() > 0;
    }
   
    public int getServingCount() {
	return serving.size();
    }
    
    public String getAvailableTables() {
	return available.toString();
    }

    public String getServing() {
	return serving.toString();
    }

    public String toString()
    {
	    return sectionName+"\n";
    }
}
    
