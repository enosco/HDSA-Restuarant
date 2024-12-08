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

    public String toString() {
	return "Customer " + getKey() + " party of " + size + "(" + section + ")";
    }
}
