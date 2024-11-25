public class Section
{
    // FIXME: Add datafields + collections
    public Section(String name) {
	// FIXME: Finish constructor
    }

    public boolean hasParty(String partyName) {
	// FIXME
	return false;
    }
    
    public boolean hasTable(String tableName) {
	// FIXME
	return false;

    }

    // Note: add logic that prevents
    // duplicates from being added even though the
    // driver is currently responsible for that
    //
    // The Driver manually checks hasTableName() because
    // the sample runs show that the name is checked for uniqueness
    // before the table is added.
    public boolean addTable(Table table) {
	// FIXME
	return false;

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
	// FIXME
	return null;
    }
    
    public String getAvailableTableInfo() {
	// FIXME
	return null;
    }

    public String getServingInfo() {
	// FIXME
	return null;
    }
}
    
