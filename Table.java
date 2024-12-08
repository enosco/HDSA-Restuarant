public class Table extends KeyedItem<Integer> {

    private String name;
    
    public Table(String name, int seats) {
	this.name = name;
	super(seats);
    }

    public String getName() {
	return getKey();
    }
    
    public int getCapacity() {
	return seats;
    }
    
    public String toString() {
	return "table " + this.getKey() + " with " + seats + " seats.";
    }
}
