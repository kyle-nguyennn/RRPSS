package Application;
import java.io.Serializable;
import java.time.*;
import java.util.*;
/**
 * @author Nguyen Dang Duy Nghia
 *
 */
public class Table implements Serializable {
	/**
	 * the id of this table
	 */
	private int tableID;
	/**
	 * the status of this table
	 */
	private TableStatus status;
	/**
	 * the maximum number of people this table can accommodate
	 */
	private int seatCapacity;
	/**
	 * the id of all future reservations that reserve this table
	 */
	private ArrayList<String> reservationIDs;
	/**
	 * constructor
	 * @param ID
	 * @param seatCapacity
	 */
	public Table(int ID, int seatCapacity){
		this.tableID = ID;
		this.status = TableStatus.vacated;
		this.seatCapacity = seatCapacity;
		reservationIDs = new ArrayList<String>();
	}
	/**
	 * get the id of this table
	 * @return
	 */
	public int getTableID(){
		return this.tableID;
	}
	/**
	 * get the status of this table
	 * @return
	 */
	public TableStatus getStatus(){
		return this.status;
	}
	/**
	 * get the maximum number of seats of this table
	 * @return
	 */
	public int getSeatCapacity(){
		return this.seatCapacity;
	}
	/**
	 * set the status for this table
	 * @param status
	 */
	public void setStatus(TableStatus status){
		this.status = status;
	}
	/**
	 * get all ids of the future reservations that reserve this table
	 * @return
	 */
	public ArrayList<String> getReservationIDs(){
		return this.reservationIDs;
	}
	/**
	 * add a reservation's id to the list
	 * @param id
	 */
	public void addReservation(String id){
		reservationIDs.add(id);
	}
	/**
	 * remove a reservation id from the list when reservation is out-dated or canceled
	 * @param id
	 */
	public void removeReservation(String id){
		for (int i=0;i<reservationIDs.size();i++){
			if (reservationIDs.get(i).equals(id)){
				reservationIDs.remove(i);
				return;
			}
		}
	}
	@Override
	public String toString(){
		//TODO
		if (this.tableID < 10 && this.seatCapacity == 10)
		{
			return (	
				 "       "+this.tableID+"      "+" | "+"     "+this.seatCapacity+"      "+" | "+	status+"\n"
					);
		}
		else if (this.tableID > 0 && this.tableID < 10 && this.seatCapacity == 8)
		{
			return (	
				 "       "+this.tableID+"      "+" | "+"     "+this.seatCapacity+"       "+" | "+	status+"\n"
					);
		}
		else
		{
			 return (
					 "       "+this.tableID+"     "+" | "+"     "+this.seatCapacity+"       "+" | "+	status+"\n"
					 );
		}
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
	/**
	 * used to compare tables according to their seat capacity
	 */
	public static Comparator<Table> CompareSeatNo = new Comparator<Table>() {
		public int compare(Table tb1, Table tb2){
			if (tb1.getSeatCapacity() == tb2.getSeatCapacity()) return 0;
			else if (tb1.getSeatCapacity() > tb2.getSeatCapacity()) return 1;
			else return -1;
		}
	};
	
}
