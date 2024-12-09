/**
 * A section within a restaurant composed of available tables and the customers being served within it.
 *
 * @author Jamie Kahle & Scott Eno
 *
 * */


public class Section {
    
    private String sectionName;
    private AscendinglyOrderedListD<Table, Integer> available; // sorted by table name
    private AscendinglyOrderedList<SeatedParty, String> serving;
    private AscendinglyOrderedList<Name, String> tableNames;

    /**
     * Constructs a new section with the specified name.
     *
     * @param name the name of this section
     */
    public Section(String name) {
        this.sectionName = name;
	this.available = new AscendinglyOrderedListD<Table, Integer>();
	this.serving = new AscendinglyOrderedList<SeatedParty, String>();
	this.tableNames = new AscendinglyOrderedList<Name, String>();
    }

    /**
     * Returns true if this section contains the specified tableName.
     *
     * @param tableName the name of the table to search for
     * @return true if this section contains the specified tableName
     */    
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
     * Attemtps to add a new table to this section.
     *
     * @param 	table 	the table that is to be added
     * @return		true if it was successful, false otherwise
     * */
    public boolean addTable(Table table) {
	
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
     * Attempts to remove an available, unoccupied table from this section.
     *
     * @param	tableName	The name of the table to be removed
     * @return			The table that has been removed if it has been successfully removed, otherwise null if it could not be found
     * @throws RemovedOnOccupiedTableException if the table to be removed is currently occupied
     * */
    public Table removeTable(String tableName) throws RemoveOnOccupiedTableException {

	Table target;
	
	if (hasTable(tableName)) { // verify table exists

	    target = null; 
	    
	    boolean removed = false;
	    int size = available.size();
	    for (int i = 0; !removed && i < size; i++) { // search available tables for target
		if (available.get(i).getName().equals(tableName)) {
		    target = available.remove(i);		    
		    removed = true;
		}
	    }

	    if (target == null) { // table exists but is not available, throw exception
		throw new RemoveOnOccupiedTableException("Can't remove a table that is currently in use!");
	    }
		
	} else { // table does not exist
	    target = null;
	}
	
	return target;
    }    

    /**
     * Attempts to seat a party in this section. 
     * 
     * @param	party	the party to be seated
     * @return 		The table the party has been seated at, otherwise null if the party could not be seated
     * */
    public Table seatParty(Party party) {		

	int index = available.search(party.getSize());
	
	if (index < 0) { // table with exact seats could not be found, but there may be a larger table
	    index = -(index+1);
	}

	Table table;
	if (index == available.size()) { // party is too large to be seated
	    table = null;
	} else { // suitable table found, proceed with seating
	    table = available.remove(index);
	    serving.add(new SeatedParty(party.getKey(), party.getSection(), party.getSize(), table));	    
	}
	return table;
    }
    /**
     * Attempts to remove the specified party from this section.
     *
     * @param	partyName	the name of the party to be removed
     * @return			the SeatedParty that was removed, null if failed
     * */
    public SeatedParty removeParty(String partyName) {
	SeatedParty party;

	int index = serving.search(partyName);

	if (index < 0) {
	    party = null;
	} else {
	    party = serving.remove(index);
	    available.add(party.getTable());
	}
	
	return party;
    }

    /**
     * Returns the name of this section.
     *
     * @return the name of this section.
     */ 
    public String getSectionName() {
	return sectionName;
    }

    /**
     * Returns true if there are available tables in this section.
     *
     * @return true if there are available tables in this section
     */ 
    public boolean hasAvailableTables() {
	return available.size() > 0;
    }

    /**
     * Returns the number of available tables in this section.
     *
     * @return the number of available tables in this section
     */
    public int getAvailableCount() {
	return available.size();
    }

    /**
     * Returns true if this section is currently serving customers.
     *
     * @return true if this section is currently serving customers     
     */
    public boolean hasCustomers() {
	return serving.size() > 0;
    }

    /**
     * Returns the number of customers being served in this section
     *
     * @returns the number of customers being served in this section
     */     
    public int getServingCount() {
	return serving.size();
    }

    /**
     * Returns a formatted string of all available tables in this section.
     *
     * @return a formatted string of all available in this section.
     */
    public String getAvailableTables() {
	return available.toString();
    }

    /**
     * Returns a formatted string of all customers in this section.
     *
     * @return a formatted string of all customers in this section.
     */
    public String getServing() {
	return serving.toString();
    }
}
    
