public class Section {
    
    private String sectionName;
    private AscendinglyOrderedListD<Table, Integer> available; // sorted by table name
    private AscendinglyOrderedList<SeatedParty, String> serving;
    private AscendinglyOrderedList<Name, String> tableNames;
    
    public Section(String name) {
        this.sectionName = name;
	this.available = new AscendinglyOrderedListD<Table, Integer>();
	this.serving = new AscendinglyOrderedList<SeatedParty, String>();
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

    public Table removeTable(String tableName) {
    //throws RemoveOccupiedTableException       
	// search available for table name
	   // if it exists, remove and return
	// else, search occupied for table
	   // if it exists, throw exception
	// else return null

	Table target;
	
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
    
    public Table seatParty(Party party) {		

	int index = available.search(party.getSize());
	
	if (index < 0) {
	    index = -(index+1);
	}

	Table table;
	if (index == available.size()) {
//	    seated = false;
	    table = null;
	} else {
	    table = available.remove(index);
	    serving.add(new SeatedParty(party.getKey(), party.getSection(), party.getSize(), table));	    
	}
	return table;
    }

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
	// FIXME	       
	return available.toString();
    }

    public String getServing() {
	// FIXME
	return serving.toString();
    }
}
    
