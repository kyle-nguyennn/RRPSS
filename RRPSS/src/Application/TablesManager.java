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
	
	public void showAvailableTables(){
		for (Table table:tableList){
			if (table.getStatus().equals(TableStatus.available))
				System.out.println(table);
		}
	}
	public boolean isAvail(int tableID){
		return tableList.get(tableID).getStatus().equals(TableStatus.available);
	}
	public void setStatus(int tableID, TableStatus status){
		tableList.get(tableID).setStatus(status);
	}
	
	public void cleanUp(){
		IOHandler.writeSerializedObject("Tables.db", tableList);
	}
}