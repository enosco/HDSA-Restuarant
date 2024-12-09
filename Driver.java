/**
 * Driver that handles all input and output relating to the restaurant.
 *
 * @author Scott Eno & Jamie Kahle
 */
import java.io.*;

public class Driver
{
    static BufferedReader stdin = new BufferedReader (new InputStreamReader(System.in));
  
    public static void main(String[] args) throws IOException
    {
	List<Section> sections = new List<Section>(); // list of sections
	DEQ<Party> waiting = new DEQ<Party>(); // waiting parties
	
	AscendinglyOrderedList<Name, String> parties = new AscendinglyOrderedList<Name, String>(); // list of all present party's names       

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
	    
	    System.out.print("You know the options.Make your menu selection now: ");
	    option = Integer.parseInt(stdin.readLine().trim());
	    System.out.println(option);
	}
	
	System.out.println("We are closing the restaurant...Good Bye!");
    }

    /**
     * Prints a list of menu options.
     */
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
			   + "\t8.\tDisplay info about customer parties being served.\n\n");  
    }


    /**
     * Prompts user to create and add tables to each section in the restaurant.
     * 
     */    
    public static void initialize(List<Section> sections) throws IOException
    {
	// Sections are hard coded for now.
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

		    System.out.println(" This table already exists! Please enter another table name.");
		    
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
		sect.addTable(new Table(tableName, numSeats));      		
	    }
	    
	}
	
	// Print menu and begin
	printMenu();
    }

    /**
     * Prompts user to create a new party which is added to the waiting queue
     *
     * @param waiting the waiting customers
     * @param parties the name of all present parties both waiting and being served
     * @param waitingListSize the number of customers waiting
     * @return the new size of the waiting queue
     */
    public static int welcomeParty(DEQ<Party> waiting, AscendinglyOrderedList<Name,String> parties, int waitingListSize) throws IOException
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


	    System.out.print(">>Does your party have pets? (Y/N): ");
	    
	    String section = stdin.readLine().trim();
	    System.out.println(section);

	    Party p = new Party(name, (section.equals("Y")?"pet-friendly":"non-pet-friendly"), size);

	    waiting.enqueue(p);

	    System.out.println();

	    return waitingListSize + 1;
    }

    /**
     * Attempts to seat parties in first come first serve order.
     * If a party cannot be seated, this method will attempt to seat the party next
     * in line until either a party is seated or no party could be seated.
     *
     * @param sections the sections within the restaurant
     * @param waiting the waiting customers      
     * @param waitingListSize the number of customers waiting
     * 
     * @return size of the waiting queue, which may be decremented if a party was seated
     */
    public static int seatParty(List<Section> sections, DEQ<Party> waiting, int waitingListSize) {
	if (waiting.isEmpty()) {
	    System.out.println("No customers to serve!\n");
	} else {
	    int numShifts = 0; //Keeps track of the number of parties that weren't able to be seated

	    
//	    boolean seating = true;
	    Party party = waiting.dequeue(); // get first in line
	    boolean seated = false;
	    
	    Section section = sections.get(0); //placeholder so it is able to compile

	    do {			
		switch(party.getSection()) {
		    case "pet-friendly":
			section = sections.get(0);
			break;
		    case "non-pet-friendly":
			section = sections.get(1);
			break;
		}

		Table table = section.seatParty(party); // get next in line

		if(table != null) { // seatParty was successful, halt
		    seated = true;
		    System.out.printf("Serving %s at %s.%n%n", party, table);
		} else { // could not find a suitable table, continue
		    
		    System.out.printf("Could not find a table with %d seats for customer %s!%n", party.getSize(), party.getKey()); 
		    numShifts++;
		    waiting.enqueue(party); // place party at back of the line for now
		    
		    if(numShifts != waitingListSize) { // continue until entire queue has been exhausted 
			party = waiting.dequeue();
		    }
		}
	    } while (!seated && numShifts < waitingListSize);

	    // return queue to original FIFO order 
	    if (!seated) { // no one was seated, queue has fully cycled 
		System.out.println("No party can be served!\n");
	    }
	    else { // seating successful, shift to return to original order
	    
		waitingListSize--;

		// changed '<' to '<=', check if this breaks anything

		// optimizes shifts so that the least required DEQ operations
		// are required to return to the original order
		if(numShifts <= (waitingListSize >> 1)) {
	      
		    for(int i = 0; i<numShifts; i++) {		   
			waiting.enqueueFront(waiting.dequeueBack());
		    }
		}
		else {
		    // shifted more than size / 2, it is quicker to rotate through the
		    // remaining items than to return the rejected parties to the front
		    for(int i = 0; i<(waitingListSize-numShifts); i++) {		    
			waiting.enqueue(waiting.dequeue());
		    }
		}
	    }
	}
	return waitingListSize;
    }

    /**
     * Prompts user to specify a party that wishes to leave the restaurant.
     * Displays leaving party and newly free table is successful, otherwise displays failure message.
     *
     * @param sections the sections within the restaurant
     * @param waiting the waiting customers
     * @param parties the name of all present parties both waiting and being served
     */
    public static void removeParty(List<Section> sections, DEQ<Party> waiting, AscendinglyOrderedList<Name, String> parties) throws IOException
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
	    System.out.println("No customer is being served!\n");
	} else { // restuarant is not empty, continue

	    // prompt user to specify party
	    System.out.print(">>Enter the name of the customer that wants to leave: ");
	    String name = stdin.readLine().trim();
	    System.out.println(name);

	    if(parties.search(name) < 0) { // verify party exists
		System.out.println("Party does not exist");		
	    } else {
		
		boolean removed = false;
		int sectionSize = sections.size();
		SeatedParty party = null;
		
		for(int i = 0; i < sectionSize && !removed; i++) { // find section where party is located		   		   
		    party = sections.get(i).removeParty(name);
		    
		    if(party != null) { // found in current section and successfully removed
			removed = true;
		    }		   
		}

		if(party == null) { // party exists but is not seated, so they must be waiting
		    System.out.printf(" Customer %s is not being served but waiting to be seated.%n%n", name);
		} else {		    
		    System.out.printf("%s has been freed.%n", party.getTable());
		    System.out.printf("Customer %s is leaving the restaurant.%n%n", party.getKey());		    
		    // successful remove, update list of names
		    parties.remove(parties.search(name));
		}
		
	    }
	}
    }
	
	
    /**
     * Prompts user for a new table to add to the restaurant.
     *
     * @param sections the sections within the restaurant
     */
    public static void addTable(List<Section> sections) throws IOException
    {
	System.out.println(">>You are now adding a table.");

	// Prompt user for section to add to
	System.out.print(" To which section would you like to add this table?(P/N):");
	String sectStr = stdin.readLine().trim();
	System.out.println(sectStr);

	Section sect = (sectStr.equals("P")) ? sections.get(0) : sections.get(1); 
	
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
	sect.addTable(new Table(tableName, numSeats));

	System.out.println();
	
    }

    /**
     * Prompts user for a table to remove from the restaurant and attempts to remove it.
     * Displays information of table if successfully removed, otherwise displays why it
     * could not be removed.
     * @param sections the sections within the restaurant
     */
    public static void removeTable(List<Section> sections) throws IOException
    {
	System.out.println(">>You are now removing a table.");

	// Prompt user for section to add to
	System.out.print(" From which section would you like to remove this table?(P/N):");
	String sectStr = stdin.readLine().trim();
	System.out.println(sectStr);

	Section sect = (sectStr.equals("P")) ? sections.get(0) : sections.get(1);
	
	// Prompt user for tableName until unique name is given	       
	System.out.print(">>Enter table name:");
	String tableName = stdin.readLine().trim();
	System.out.println(tableName);

	try {

	    Table table = sect.removeTable(tableName); // attempt ot remove table
	    
	    if (table != null) { // table removed successfully
		System.out.printf("Table %s has been removed.%n%n", table.getName());
	    } else {
		System.out.printf(" This table does not exist in the %s section! Please enter a different table name!%n%n", sect.getSectionName()); 
	    }
	    
	} catch (RemoveOnOccupiedTableException e) { // specified table was found, but occupied
	    System.out.println(" Can't remove a table that is currently in use!\n");
	}	
    }

    /**
     * Displays all available tables in the restaurant by section.
     * @param sections the sections within the restaurant
     */
    public static void displayAvailableTables(List<Section> sections)
    {
	int size = sections.size();
      
	for (int i = 0; i < size; i++) {
	    Section sect = sections.get(i);
	    System.out.printf("The following %d tables are available in the %s section:%n",
			      sect.getAvailableCount(), sect.getSectionName());
	    
	    System.out.print(sect.getAvailableTables());
	}
	System.out.println(); // newline for gap between menu prompt
    }

    /**
     * Displays all parties waiting to be seated in FIFO order.
     * @param waiting the waiting customers          
     */    
    public static void displayWaiting(DEQ<Party> waiting)
    {
	if (waiting.isEmpty()) {
	    System.out.println("\tNo customers are waiting for tables!\n");
	} else {
	    System.out.println("The following customer parties are waiting for tables:");
	    System.out.println(waiting);
	}
    }

    /**
     * Displays all parties being served in the restaurant by section.
     * @param sections the sections within the restaurant
     */
    public static void displayServing(List<Section> sections)
    {
	boolean hasCustomers = false;
	
	int size = sections.size(); 
	for (int i = 0; i < size; i++) { // displays info for each section
	    Section sect = sections.get(i);

	    if (sect.hasCustomers()) {		
		hasCustomers = true;		

		String tense = (sect.getServingCount() > 1) ? "customers are" : "customer is"; 
		
		System.out.printf("The following %s being served in the %s section:%n",
				      tense, sect.getSectionName());
		
		System.out.print(sect.getServing());

	    }	    
	}

	if (!hasCustomers) {
	    System.out.println("\tNo customers are being served!");
	}
	System.out.println(); // newline for gap between menu prompt
    }
}
