/**
 * A section within a restaurant composed of available tables and the customers being served within it, modified to allow table sharing if an available table has spare seats.
 *
 * @author Jamie Kahle & Scott Eno
 *
 * */
public class ECSection {
    
    private String sectionName;
    private AscendinglyOrderedListD<ECTable, Seats> available; // sorted by table name
    private AscendinglyOrderedList<ECSeatedParty, String> serving;
    private AscendinglyOrderedList<Name, String> tableNames;

   /**
     * Constructs a new section with the specified name.
     *
     * @param name the name of this section
     */    
    public ECSection(String name) {
        this.sectionName = name;
	this.available = new AscendinglyOrderedListD<ECTable, Seats>();
	this.serving = new AscendinglyOrderedList<ECSeatedParty, String>();
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
     *
     */
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
     * Attempts to remove an available, unoccupied table from this section.
     *
     * @param	tableName	The name of the table to be removed
     * @return			The table that has been removed if it has been successfully removed, otherwise null if it could not be removed
     */
    public ECTable removeTable(String tableName) {

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
     * Attempts to seat a party at a table with sufficient seats.
     * 
     * @param	party	the party to be seated
     * @return 		The table the party has been seated at, otherwise null if the party could not be seated
     */
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

	    if(table.getSeats().getAvailableSeats() > 0)
	    {
		    available.add(table);
	    }
	}
	return table;
    }

    /**
     * Attempts to remove the specified party from this section.
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

	    int originalSeats = table.getSeats().getAvailableSeats();
	    if(originalSeats != 0)
	    {
		    int tableIndex= -1;
		    int siz = available.size();
		    for( int i=0; i<siz && tableIndex == -1; i++)
		    {
			    if(available.get(i).getName().equals(table.getName()))
			    {
				    tableIndex = i;
			    }
		    }
		if(tableIndex != -1)
		{
	    		available.remove(tableIndex);
		}
	    }

	    table.returnSeats(party.getSize());
	    available.add(table);
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
    
    /**
     * Returns this section's name
     *
     * @return this section's name
     */

    public String toString()
    {
	    return sectionName+"\n";
    }
}
    
