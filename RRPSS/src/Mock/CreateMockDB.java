package Mock;
import java.io.*;
import java.util.*;
import Helper.*;
import Application.*;

public class CreateMockDB {
	public static void main (String[] args){
		ArrayList<Table> tbList = new ArrayList<Table>();
		ArrayList<Staff> stList = new ArrayList<Staff>();
		createMockTables(tbList);
		//System.out.println(tbList);
		List list = (ArrayList) tbList;
		IOHandler.writeSerializedObject("Tables.db", list);
		
	}
	
	public static void createMockTables(ArrayList<Table> tbList){
		int i =0;
		for (;i<5; i++){
			tbList.add(new Table(i, 10));
		}
		for (;i<10; i++){
			tbList.add(new Table(i, 8));
		}
		for (;i<20; i++){
			tbList.add(new Table(i, 4));
		}	
		for (;i<30; i++){
			tbList.add(new Table(i, 2));
		}
	}
	
	public static void createMockStaff(){
		
		
	}
	
	
}
