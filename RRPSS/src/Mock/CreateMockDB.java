package Mock;
import java.io.*;
import java.util.*;
import Helper.*;
import Application.*;
public class CreateMockDB {
	public static void main (String[] args){
		ArrayList<Table> tbList = new ArrayList<Table>();
		createMockTables(tbList); //System.out.println(tbList);
		List list1 = (ArrayList) tbList;
		IOHandler.writeSerializedObject("Tables.db", list1);
		
		ArrayList<Staff> stList = new ArrayList<Staff>();
		createMockStaff(stList); //System.out.println(stList);
		List list2 = (ArrayList) stList;
		IOHandler.writeSerializedObject("Staff.db", list2);
		
		ArrayList<Order> odList = new ArrayList<Order>();
		createMockOrders(odList);
		List list3 = (ArrayList) odList;
		IOHandler.writeSerializedObject("OrdersHistory.db", list3);
		
		ArrayList<PromotionalPackage> pkList = new ArrayList<PromotionalPackage>();
		createMockPackages(pkList);
		List list4 = (ArrayList) pkList;
		IOHandler.writeSerializedObject("PromoPackages.db", list4);
		
		ArrayList<MenuItem> itList = new ArrayList<MenuItem>();
		createMockItems(itList);
		List list5 = (ArrayList) itList;
		IOHandler.writeSerializedObject("Items.db", list5);
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
	
	public static void createMockStaff(ArrayList<Staff> stList){
		String[] gender = {"Male", "Female"};
		Random rand = new Random();
		String[] name = {"Kyle", "Jia Jing", "Zhi Yang", "Jacq", "Akman", "Matt", "Owen", "Jude", "Harry Potter", "Mark Zuckerberg"};
		Integer i = 0;
		for (;i<10;i++){
			stList.add(new Staff(name[i],gender[rand.nextInt(2)], i, "Server"));
		}
	}
	
	public static void createMockOrders(ArrayList<Order> odList){
		
	}
	
	public static void createMockPackages(ArrayList<PromotionalPackage> pkList){
		
	}
	
	public static void createMockItems(ArrayList<MenuItem> itList){
		
	}
}
