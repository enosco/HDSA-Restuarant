public class Driver
{
    public static void main(String[] args)
    {
	int option = 0;
	switch (option)
	{
	    case 0: // exit
	    case 1: // party enters
	    case 2: // serve waiting party
	    case 3: // party leaves
	    case 4: // add table
	    case 5: // remove table
	    case 6: // display available tables
	    case 7: // display waiting parties
	    case 8: // display occupied tables
	}
    }

    public static void welcomeParty()
    {
	// prompt for party name
	// if already exists -> prompt until unique name
	// prompt for party size, pets/no pets

	// create new party and add to waiting collection

	
    }

    public static void seatParty()
    {
	// attempt to seat party at front of queue
	// if no tables available: attempt to seat next party,
	//    continue until party is seated or noone could be seated
    }

    public static void removeParty()
    {
	// prompt for party name
	// if it matches a party currently being served, free the table occupied by the party
    }

    public static void addTable()
    {
	// prompt for table name
	// if already exists -> halt
	
	// prompt for section
    }

    public static void removeTable()
    {
	// prompt for section
	// prompt for table name
	// if it does not exist -> halt
	// if occupied -> halt
	// else, remove table 
    }

    public static void displayAvailableTables()
    {
	// if available tables is not empty ->

    }

    public static void displayWaiting()
    {
	// if waitlist is not empty -> display
    }

    public static void displayServing()
    {
	// if seated list is not empty -> display
    }
}
