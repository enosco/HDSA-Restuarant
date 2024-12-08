import java.io.*;
public class Driver
{
    static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException
    {
	List<Section> sections = new List<Section>();
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
		    waitingListSize = welcomeParty(waiting, parties, waitingListSize);
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
	    }
	    
	    System.out.print("You know the options. Make your menu selection now: ");
	    option = Integer.parseInt(stdin.readLine().trim());
	    System.out.println(option);
	}
	
	System.out.println("We are closing the restaurant...Good Bye!");
    }

    public static void printMenu()
    {
	System.out.println("\nSelect from the following menu:\n"
			   + "\t0.  Close the restaurant.\n"
			   + "\t1.   Customer party enters the restaurant.\n"
			   + "\t2.   Customer party is seated and served.\n"
			   + "\t3.   Customer party leaves the restaurant.\n"
			   + "\t4.   Add a table.\n"
			   + "\t5.   Remove a table.\n"
			   + "\t6.   Display available tables.\n"
			   + "\t7.   Display info about waiting customer parties.\n"
			   + "\t8.   Display info about customer parties being served.\n\n");  
    }

    public static void initialize(List<Section> sections) throws IOException
    {
	// Sections are hard coded for now. Later, prompt the user
	// for the number of sections they want.
	sections.add(0, new Section("pet-friendly"));
	sections.add(1, new Section("non-pet-friendly"));
	
	System.out.println("Enter your restaurant configuration:");
	// For each section
	int size = sections.size();
	for (int i = 0; i < size; i++) {
	    // Prompt user for number of tables
	    Section sect = sections.get(i);
	    
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

		    System.out.println("This table already exists! Please enter another table name.");
		    
		    System.out.print(">>Enter table name:");
		    tableName = stdin.readLine().trim();
		    System.out.println(tableName);
		}

		// Prompt for number of seats
		System.out.print(">>Enter number of seats:");
		int numSeats = Integer.parseInt(stdin.readLine().trim());
		System.out.println(numTables);

		// Create newTable
		// section.addTable(newTable)
		// newTable is added to availableTables
		sect.addTable(new Table(tableName, numSeats));      		
	    }
	    
	}
	
	// Print menu and begin
	printMenu();
    }
    
    public static int welcomeParty(DEQ<Party> waiting, AscendinglyOrderedList<Name,String> parties, int waitingListSize) throws IOException
    {
	    String name="";
	    boolean naming = true;

	    while(naming)
	    {
		    try
		    {
			    System.out.print("Enter party name: ");
			    name = stdin.readLine().trim();
			    System.out.println(name);

			    parties.add(new Name(name));
			    naming = false;
		    }
		    catch(ListIndexOutOfBoundsException ex)
		    {
			    System.out.println("Name already exists, please choose a new one.");
		    }
	    }
	    

	    System.out.print("Enter party size: ");
	    int size = Integer.parseInt(stdin.readLine().trim());
	    System.out.println(size);


	    System.out.print("Does your party have pets?(Y/N): ");
	    String section = stdin.readLine().trim();
	    System.out.println(section);

	    Party p = new Party(name, (section.equals("Y")?"pet-friendly":"non-pet-friendly"), size);

	    waiting.enqueue(p);

	    return waitingListSize + 1;

	    
	// Prompt for partyName until unique name is given
        	// if waitingQueue contains party with partyName OR section.hasParty(partyName) == true → partyName is not unique, prompt again
	// Prompt for group size
	// Prompt for hasPets
	// Create newParty
	// Enqueue newParty onto waitingQueue
    }

    public static int seatParty(List<Section> sections, DEQ<Party> waiting, int waitingListSize)
    {
	if (waiting.isEmpty()) {
	    System.out.println("\tNo customers to serve!\n");
	} else {
		int numShifts = 0; //Keeps track of the number of parties that weren't able to be seated


		boolean seating = true;
		Party party = waiting.dequeue();
		Section section = sections.get(0); //placeholder so it is able to compile

		do{

			
			switch(party.getSection())
			{
				case "pet-friendly":
					section = sections.get(0);
				case "non-pet-friendly":
					section = sections.get(1);
			}

			boolean seated = !section.seatParty(party);

			if(seated)
			{
				seating = false;
			}
			else
			{
				numShifts++;
				waiting.enqueue(party);
				party = waiting.dequeue();
			}


		} while(seating &&  numShifts != waitingListSize);

		if(numShifts == waitingListSize && !seating)
		{
			System.out.println("Unable to accomadate any party");
		}
		else
		{
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
			waitingListSize--;
		}







	// Dequeue nextParty from the front of waitingQueue
	// Store nextParty’s name to keep track of original order of the queue
	// Loop until nextParty is seated OR no party could be seated (nextParty == original front of queue)
        	// Get the section that nextParty prefers
	        // preferredSection.add(nextParty)
                 	// Search through availableTables to find a table with the least number of excess seats.
                        	// If a Table is found, create a new OccupiedTable from nextParty and Table and add it to occupiedTables
                                	// .add(nextParty) returns true
                         	// if .add(nextParty) returns false → enqueue nextParty, dequeue new nextParty, repeat
                                 	// Display failure 
	// If a party was successfully seated → rotate through queue items until original FIFO order is restored
        	// dequeue and enqueue from waitingQueue until peek returns original front of queue -> Display success
	// If no parties could be seated -->  Display failure
	}
	return waitingListSize;
    }

    public static void removeParty(List<Section> sections, DEQ<Party> waiting, AscendinglyOrderedList<Name,String> parties) throws IOException
    {
	// check if the restuarant is empty
	boolean hasCustomers = false;
      
	int size = sections.size();
	for (int i = 0; i < size; i++) {
	    Section sect = sections.get(i);
	    if (sect.hasCustomers()) {
		hasCustomers = true;
	    }
	}

	if (!hasCustomers) {
	    System.out.println("\tNo customers are being served!\n");
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
			SeatedParty party = null;
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
                                	// Table is now freed
                         	// Return matching OccupiedTable
                                   	// Display successful remove
	// If a match is not found within any of the sections
         	// search through waitingList to verify that specified party is not still waiting
         	// Display location of match in waitingList
	// Else → match not found, display failure
	}
    }

    public static void addTable(List<Section> sections)
    {
	// Prompt user for section to add to
	// Prompt user for tableName until unique name is given
         	// If section.hasTable(tableName) == true → tableName is not unique, prompt again
                	// Repeat until unique name is given
	// Prompt for number of seats
	// Create newTable
	// section.addTable(newTable)
        	// newTable is added to availableTables
    }

    public static void removeTable(List<Section> sections)
    {
	// Prompt user for section to remove from
	// Prompt user for tableName
	// section.removeTable()
        	// If matching tableName is found in availableTables
                	// Remove, secure, and return found Table
                  	// Display successful remove
            	// If matching tableName is found in occupiedTables
                	// Throw RemoveOccupiedTableException
                 	// Driver catches exception, informs user that table is occupied
          	// If matching tableName could not be found
                 	// Throw NoSuchElementException
                   	// Driver catches exception, informs user that table could not be found

    }

    public static void displayAvailableTables(List<Section> sections)
    {
	int size = sections.size();
      
	for (int i = 0; i < size; i++) {
	    Section sect = sections.get(i);
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
	    System.out.println(waiting);
	}

    }

    public static void displayServing(List<Section> sections)
    {
	// For each section
        	// section.getServingInfo()
                	// Display name of section along with contents of occupiedTables (.size() & .toString() )

	boolean hasCustomers = false;
	
	int size = sections.size();
	for (int i = 0; i < size; i++) {
	    Section sect = sections.get(i);

	    if (sect.hasCustomers()) {		
		hasCustomers = true;		
		// FIXME: PRINT SERVING INFO FOR SECTION
	    }
	    
	}

	if (!hasCustomers) {
	    System.out.println("\tNo customers are being served!\n");
	}	
    }
}
