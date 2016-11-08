package Application;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Helper.IOHandler;

public class Reserve implements Serializable{
	ArrayList<Reservation> reservations;
	
	public Reserve(){
		reservations = new ArrayList<Reservation>();
		reservations = (ArrayList)IOHandler.readSerializedObject("Reservations.db");
		System.out.println(reservations);
	}
	
	public int newReservation(String contact, LocalDateTime arrival, int pax, ArrayList<Table> availTables){
		availTables.sort(Table.CompareSeatNo);
		for (Table table:availTables){
			if (pax < table.getSeatCapacity()){
				reservations.add(new Reservation(contact, arrival, pax, table.getTableID()));
				return table.getTableID();
			}
		}
		return -1;
	}
	
	public Reservation getReservation(String contact){
		for (Reservation res:reservations)
			if (res.getContact().equals(contact)){
				return res;
			}	
		return null;
	}
	public void showReservation(String contact){
		System.out.println(getReservation(contact));
	}
	public ArrayList<Reservation> getReservations(ArrayList<String> ids){ 
		return (ArrayList<Reservation>)reservations.stream()
				.filter(res -> ids.contains(res.getID()))
				.collect(Collectors.toList());
	}
	public int removeReservation(String contact){
		Reservation res = null;
		for (int i =0; i<reservations.size(); i++){
			if (reservations.get(i).getContact().equals(contact)){
				res = reservations.get(i);
				reservations.remove(i);
				break;
			}
		}
		if (res==null) return -1;
		return res.getTableID();
	}
	
	@Override
	public String toString(){
		//TODO
		return new String();
	}
	public void cleanUp(){
		IOHandler.writeSerializedObject("Reservations.db", reservations);
	}
}
