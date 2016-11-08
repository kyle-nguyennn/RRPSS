package Application;
import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.UUID;
public class Reservation implements Serializable{
	private String id;
	private String contact;
	private LocalDateTime arrivalTime;
	private int pax;
	private int tableID;
	
	Reservation(String contact, LocalDateTime arrival, int pax, int tableID){
		this.id = UUID.randomUUID().toString();
		this.contact = contact;
		this.arrivalTime = arrival;
		this.pax = pax;
		this.tableID = tableID;
	}
	
	public String getID(){
		return this.id;
	}
	public void setTime(LocalDateTime arrival){
		this.arrivalTime = arrival;
	}
	public LocalDateTime getTime(){
		return this.arrivalTime;
	}
	public String getContact(){
		return this.contact;
	}
	public void setContact(String contact){
		this.contact = contact;
	}
	public int getTableID(){
		return this.tableID;
	}
	public void assign(int tableID){// check validity of tableID in upper layer 
		this.tableID= tableID;
	}
	//return ids of tables chosen
	
	@Override
	public String toString(){
		String d = "|";
		return contact+d+arrivalTime+d+pax;
	}
}
