import java.util.Objects;
public class Table {
	private int tableID;
	private TableStatus status;
	private int seatCapacity;
	
	Table(int ID, int seatCapacity){
		this(ID, TableStatus.available, seatCapacity);
	}
	Table(int ID, TableStatus status, int seatCapacity){
		this.tableID = ID;
		this.status = status;
		this.seatCapacity = seatCapacity;
	}
	
	public int getTableID(){
		return this.tableID;
	}
	
	public TableStatus getStatus(){
		return this.status;
	}
	
	public int getSeatCapacity(){
		return this.seatCapacity;
	}
	@Override
	public String toString(){
		//TODO
		return toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Table))
			return false;
		Table table = (Table) o;
		if (this.tableID == table.tableID)
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.tableID, this.status, this.seatCapacity);
	}
	
}
