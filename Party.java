/**
 * Party class
 *
 * <p>
 * Contains information about the size and section of a party, as well as defining the name as the keyed item<p>
 *
 * @author Jamie Kahle, Scott Eno
 *
 **/

public class Party extends KeyedItem<String>
{
    private int size;
    private String section;
    
    public Party(String name, String section, int size) {
	super(name);
	this.section = section;
	this.size = size;
    }
  
    public int getSize() {
	return size;
    }

    public String getSection()
    {
	    return section;
    }

    public String toString() {
	return "Customer " + getKey() + " party of " + size + "(" + section + ")";
    }
}
