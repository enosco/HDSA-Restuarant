public class Table extends KeyedItem<Integer> {

    private String name;
    
    public Table(String name, int seats) {
	super(seats);
	this.name = name;
    }

    public String getName() {
	return name;
    }
        
    public String toString() {
	return "table " + name + " with " + getKey() + " seats";
    }
}
