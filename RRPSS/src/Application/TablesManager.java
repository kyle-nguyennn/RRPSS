package Application;
import java.util.*;

import Helper.IOHandler;

import java.io.*;

public class TablesManager {
	private ArrayList<Table> tableList;
	
	public TablesManager(){
		tableList = new ArrayList<Table>();
		tableList = (ArrayList) IOHandler.readSerializedObject("Tables.db");
	}
	
	public ArrayList<Table> getAvailTables(){
		ArrayList<Table> availTables = new ArrayList<Table>();
		for (Table table:tableList)
			if (table.isAvail())
				availTables.add(table);
		return availTables;
	}
	public void showAvailableTables(){
		System.out.println(getAvailTables());
	}
	public boolean isAvail(int tableID){
		if (tableID<0 || tableID>29) return false;
		return tableList.get(tableID).isAvail();
	}
	public void setStatus(int tableID, TableStatus status){
		tableList.get(tableID).setStatus(status);
	}
	
	public void reserve(ArrayList<Integer> tableIDs){
		for (int id:tableIDs){
			setStatus(id, TableStatus.reserved);
		}
	}
	public void release(ArrayList<Integer> tableIDs){
		for (int id:tableIDs){
			setStatus(id, TableStatus.vacated);
		}
	}
	
	public void cleanUp(){
		IOHandler.writeSerializedObject("Tables.db", tableList);
	}
}
