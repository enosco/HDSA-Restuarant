import java.io.*;
public class ECDriver
{
    static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException
    {
	List<ECSection> sections = new List<ECSection>();
	DEQ<Party> waiting = new DEQ<Party>();

	AscendinglyOrderedList<Name, String> parties = new AscendinglyOrderedList<Name, String>();
	int waitingListSize = 0;

	initialize(sections);

	System.out.print("Make your menu selection now: ");
	int option = Integer.parseInt(stdin.readLine().trim());
	System.out.println(option);
	
	while (option != 0) {
	    switch (option) {
		case 0: break;// exit		    
		case 1: // party enters
		    waitingListSize = welcomeParty(waiting, parties, waitingListSize, sections);
		    break;
		case 2: // serve waiting party
		    waitingListSize = seatParty(sections, waiting, waitingListSize);
		    break;
		case 3: // party leaves
		    removeParty(sections, waiting, parties);
		    break;
		case 4: // add table
		    addTable(sections);
		    break;
		case 5: // remove table
		    removeTable(sections);
		    break;
		case 6: // display available tables
		    displayAvailableTables(sections);
		    break;
		case 7: // display waiting parties
		    displayWaiting(waiting);
		    break;
		case 8: // display occupied tables
		    displayServing(sections);
		    break;
		case 9:
		    addSection(sections);
		    break;
	    }
	    
	    System.out.print("You know the options.Make your menu selection now: ");
	    option = Integer.parseInt(stdin.readLine().trim());
	    System.out.println(option);
	}
	
	System.out.println("We are closing the restaurant...Good Bye!");
    }

    public static void printMenu()
    {
	System.out.println("\nSelect from the following menu:\n"
			   + "\t0.\tClose the restaurant.\n"
			   + "\t1.\tCustomer party enters the restaurant.\n"
			   + "\t2.\tCustomer party is seated and served.\n"
			   + "\t3.\tCustomer party leaves the restaurant.\n"
			   + "\t4.\tAdd a table.\n"
			   + "\t5.\tRemove a table.\n"
			   + "\t6.\tDisplay available tables.\n"
			   + "\t7.\tDisplay info about waiting customer parties.\n"
			   + "\t8.\tDisplay info about customer parties being served.\n"
			   +"\t9.\tAdd a new section\n\n");  
    }

    public static void initialize(List<ECSection> sections) throws IOException
    {
	// Sections are hard coded for now. Later, prompt the user
	// for the number of sections they want.
	sections.add(0, new ECSection("pet-friendly"));
	sections.add(1, new ECSection("non-pet-friendly"));
	
	System.out.println("Enter your restaurant configuration:");
	// For each section
	int size = sections.size();
	for (int i = 0; i < size; i++) {
	    // Prompt user for number of tables
	    ECSection sect = sections.get(i);
	    
	    System.out.print(">>How many tables does your " + sect.getSectionName() + " section have? ");
	    int numTables = Integer.parseInt(stdin.readLine().trim());
	    System.out.println(numTables);

	    
	    // For [number of tables] loops
	    for (int j = 0; j < numTables; j++) {
		
                // Prompt user for tableName until unique name is given	       
		System.out.print(">>Enter table name:");
		String tableName = stdin.readLine().trim();
		System.out.println(tableName);

		
		// If section.hasTable(tableName) == true → tableName is not unique, prompt again	      
                // Repeat until unique name is given
		while (sect.hasTable(tableName)) {

		    System.out.println(" This table already exists! Please enter another table name.\n");
		    
		    System.out.print(">>Enter table name:");
		    tableName = stdin.readLine().trim();
		    System.out.println(tableName);
		}

		// Prompt for number of seats
		System.out.print(">>Enter number of seats:");
		int numSeats = Integer.parseInt(stdin.readLine().trim());
		System.out.println(numSeats);

		// Create newTable
		// section.addTable(newTable)
		// newTable is added to availableTables
		sect.addTable(new ECTable(tableName, numSeats));      		
	    }
	    
	}
	
	// Print menu and begin
	printMenu();
    }
    
    public static int welcomeParty(DEQ<Party> waiting, AscendinglyOrderedList<Name,String> parties, int waitingListSize, List<ECSection> sections) throws IOException
    {
	    String name="";
	    boolean naming = true;

	    while(naming)
	    {
		try
		{
		    System.out.print(">>Enter customer name : ");
		    name = stdin.readLine().trim();
		    System.out.println(name);
		    
		    parties.add(new Name(name));
		    naming = false;
		}
		catch(ListIndexOutOfBoundsException ex)
		{
		    System.out.println("There already exists a customer with this name in the restaurant.\n\tPlease select another name.");
		}
	    }
	    

	    System.out.printf(">>Enter number of seats for customer %s: ", name);
	    int size = Integer.parseInt(stdin.readLine().trim());
	    System.out.println(size);


	    String section="";
	    boolean selectingSect = true;
	    while(selectingSect)
	    {
	    System.out.print(">>Please choose from the following sectons:\n"+sections+"Enter your selection: ");
	    section = stdin.readLine().trim();
	    System.out.println(section);

	    if(selectSection(sections,section) != null)
	    {
		    selectingSect = false;
	    }

	    }
	    Party p = new Party(name, section, size);

	    waiting.enqueue(p);

	    System.out.println();

	    return waitingListSize + 1;

	    
	// Prompt for partyName until unique name is givenc
        	// if waitingQueue contains party with partyName OR section.hasParty(partyName) == true → partyName is not unique, prompt again
	// Prompt for group size
	// Prompt for hasPets
	// Create newParty
	// Enqueue newParty onto waitingQueue
    }

    public static int seatParty(List<ECSection> sections, DEQ<Party> waiting, int waitingListSize)
    {
	if (waiting.isEmpty()) {
	    System.out.println("No customers to serve!\n");
	} else {
	    int numShifts = 0; //Keeps track of the number of parties that weren't able to be seated

	    
	    boolean seating = true;
	    Party party = waiting.dequeue();
	    ECSection section = sections.get(0); //placeholder so it is able to compile

	    do {
		    section = selectSection(sections, party.getSection());

		    ECTable table = section.seatParty(party);

		if(table != null)
		{
		    seating = false;
		    System.out.printf("Serving %s at %s.%n%n", party, table);
		}
		else
		{
		    System.out.printf("Could not find a table with %d seats for customer %s!%n", party.getSize(), party.getKey()); 
		    numShifts++;
		    waiting.enqueue(party);
		    if(numShifts != waitingListSize)
		    {
			party = waiting.dequeue();
		    }
		}


	    } while(seating && numShifts != waitingListSize);


	    // return queue to original order
	    
	    if(numShifts == waitingListSize && seating)
	    {
		System.out.println("No party can be served!\n");
	    }
	    else
	    {
		System.out.println(); // newline for formatting

		    
		waitingListSize--;

		// changed '<' to '<=', check if this breaks anything
		// 
		if(numShifts < (waitingListSize >> 1))
		{
		    for(int i = 0; i<numShifts; i++)
		    {
			waiting.enqueueFront(waiting.dequeueBack());
		    }
		}
		else
		{
		    for(int i = 0; i<(waitingListSize-numShifts); i++)
		    {
			waiting.enqueue(waiting.dequeue());
		    }
		}
	    }
	}
	return waitingListSize;
    }

    public static void removeParty(List<ECSection> sections, DEQ<Party> waiting, AscendinglyOrderedList<Name,String> parties) throws IOException
    {
	// check if the restuarant is empty
	boolean hasCustomers = false;
      
	int size = sections.size();
	for (int i = 0; i < size; i++) {
	    ECSection sect = sections.get(i);
	    if (sect.hasCustomers()) {
		hasCustomers = true;
	    }
	}

	if (!hasCustomers) {
	    System.out.println("No customer is being served!\n");
	} else {
		System.out.print("Enter name of leaving party: ");
		String name = stdin.readLine().trim();
		System.out.println(name);

		if(parties.search(name) < 0)
		{
			System.out.println("Party does not exist");
		}
		else
		{
			boolean removed = false;
			int sectionSize = sections.size();
			ECSeatedParty party = null;
			for( int i = 0; i < sectionSize && !removed; i++)
			{
				party = sections.get(i).removeParty(name);
				if(party != null)
				{
					removed = true;
				}
			}

			if(party == null)
			{
				System.out.println("Party is in the waiting list");
			}
			else
			{
				System.out.println("Removed :" + party);
				parties.remove(parties.search(name));
			}
		}
	   
	
	// Prompt for partyName of leaving party
	// For each section until a match is found
        	// section.remove(partyName)
        	// Search occupiedTables for matching 
                	// if a match is found:
                        	// remove and secure matching OccupiedTable from occupiedTables
                         	// Create newTable from name and seats of OccupiedTable
                        	// add newTable to availableTables 
                                	// ECTable is now freed
                         	// Return matching OccupiedTable
                                   	// Display successful remove
	// If a match is not found within any of the sections
         	// search through waitingList to verify that specified party is not still waiting
         	// Display location of match in waitingList
	// Else → match not found, display failure
	}
    }

    public static void addTable(List<ECSection> sections) throws IOException
    {
	System.out.println(">>You are now adding a table.");

	// Prompt user for section to add to
	System.out.print(" To which section would you like to add this table?\n\t"+sections+"Make your selectionNow: ");
	String sectStr = stdin.readLine().trim();
	System.out.println(sectStr);

	ECSection sect = selectSection(sections,sectStr);

	
	// Prompt user for tableName until unique name is given	       
	System.out.print(">>Enter table name:");
	String tableName = stdin.readLine().trim();
	System.out.println(tableName);
	            
	// Repeat until unique name is given
	while (sect.hasTable(tableName)) {
	    
	    System.out.printf(" This table already exists in the %s section! Please enter another table name%n", sect.getSectionName());
	    
	    System.out.print(">>Enter table name:");
	    tableName = stdin.readLine().trim();
	    System.out.println(tableName);
	}
	
	// Prompt for number of seats
	System.out.print(">>Enter number of seats:");
	int numSeats = Integer.parseInt(stdin.readLine().trim());
	System.out.println(numSeats);
	
	// Create newTable
	// section.addTable(newTable)
	// newTable is added to availableTables
	sect.addTable(new ECTable(tableName, numSeats));

	System.out.println();
	
    }

    public static void removeTable(List<ECSection> sections) throws IOException
    {
	// Prompt user for section to remove from
	// Prompt user for tableName
	// section.removeTable()
        	// If matching tableName is found in availableTables
                	// Remove, secure, and return found ECTable
                  	// Display successful remove
            	// If matching tableName is found in occupiedTables
                	// Throw RemoveOccupiedTableException
                 	// Driver catches exception, informs user that table is occupied
          	// If matching tableName could not be found
                 	// Throw NoSuchElementException
                   	// Driver catches exception, informs user that table could not be found

	System.out.println(">>You are now remove a table.");

	// Prompt user for section to add to
	System.out.print(" To which section would you like to add this table?(P/N):");
	String sectStr = stdin.readLine().trim();
	System.out.println(sectStr);

	ECSection sect = (sectStr.equals("P")) ? sections.get(0) : sections.get(1);
	
	// Prompt user for tableName until unique name is given	       
	System.out.print(">>Enter table name:");
	String tableName = stdin.readLine().trim();
	System.out.println(tableName);
	
	ECTable table = sect.removeTable(tableName);

	if (table != null) {
	    System.out.printf("Table %s has been removed%n%n", table.getName());
	} else {
	    System.out.printf(" This table doesn't exists in the %s section or is currently occupied! Please enter another table name.%n%n", sect.getSectionName()); 

	}
	
    }

    public static void displayAvailableTables(List<ECSection> sections)
    {
	int size = sections.size();
      
	for (int i = 0; i < size; i++) {
	    ECSection sect = sections.get(i);
	    System.out.printf("The following %d tables are available in the %s section:%n",
			      sect.getAvailableCount(), sect.getSectionName());
	    
	    System.out.println(sect.getAvailableTables());
	}
    }

    public static void displayWaiting(DEQ<Party> waiting)
    {

	if (waiting.isEmpty()) {
	    System.out.println("\tNo customers are waiting for tables!\n");
	} else {
	    System.out.println("The following customer parties are waiting for tables:");
	    System.out.println(waiting);
	}

    }

    public static void displayServing(List<ECSection> sections)
    {
	// For each section
        	// section.getServingInfo()
                	// Display name of section along with contents of occupiedTables (.size() & .toString() )

	boolean hasCustomers = false;
	
	int size = sections.size();
	for (int i = 0; i < size; i++) {
	    ECSection sect = sections.get(i);

	    if (sect.hasCustomers()) {		
		hasCustomers = true;		

		System.out.printf("The following customers are being served in the %s section:%n", sect.getSectionName());
		System.out.println(sect.getServing());

	    }
	    
	}

	if (!hasCustomers) {
	    System.out.println("\tNo customers are being served!\n");
	}
    }

    public static void addSection(List<ECSection> sections) throws IOException
    {
	    System.out.print("Enter your section name: ");
	    String section = stdin.readLine().trim();
	    System.out.println(section);

	    if(selectSection(sections,section) == null)
	    {
		    sections.add(sections.size(),new ECSection(section));
	    }
	    else
	    {
		    System.out.println("Section already exists");
	    }
    }

    private static ECSection selectSection(List<ECSection> sections, String sect)
    {
	    ECSection section = null;
		    int sectionSize = sections.size();
		    boolean selectingSection = true;
			for(int i=0;i<sectionSize && selectingSection;i++)
			{
				if(sect.equals(sections.get(i).getSectionName()))
				{
					section = sections.get(i);
					selectingSection = false;
				}
			}
	return section;
    }
		    
}
