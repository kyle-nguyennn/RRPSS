package Application;
import java.io.Serializable;
import java.time.*;
import java.util.Comparator;
import java.util.Objects;
public class Table implements Serializable {
	private int tableID;
	private TableStatus status;
	private int seatCapacity;	
	private LocalDateTime timestamp = null;
	
	public Table(int ID, int seatCapacity){
		this(ID, TableStatus.vacated, seatCapacity);
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
	public void setStatus(TableStatus status){
		this.status = status;
	}
	public LocalDateTime getTime(){
		return this.timestamp;
	}
	public void setTime(){
		this.timestamp = LocalDateTime.now();
	}
	public boolean isAvail(){ //check and update status
		if (this.status.equals(TableStatus.vacated)) return true;
		if (this.status.equals(TableStatus.reserved)){
			Duration duration = Duration.between(this.timestamp.toLocalDate(), LocalDateTime.now().toLocalDate());
			if (duration.toMinutes() > 30){
				
			}
		}
		return false;
	}
	
	@Override
	public String toString(){
		//TODO
		return "table "+this.tableID + "|" + this.seatCapacity + " seats|"
				+ status;
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
	
	public static Comparator<Table> CompareSeatNo = new Comparator<Table>() {
		public int compare(Table tb1, Table tb2){
			if (tb1.getSeatCapacity() == tb2.getSeatCapacity()) return 0;
			else if (tb1.getSeatCapacity() > tb2.getSeatCapacity()) return 1;
			else return -1;
		}
	};
	
}
