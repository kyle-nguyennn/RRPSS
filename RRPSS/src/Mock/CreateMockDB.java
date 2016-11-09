package Mock;
import java.io.*;
import java.time.LocalDateTime;
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
		
		ArrayList<Reservation> reList = new ArrayList<Reservation>();
		createMockReservations(reList);
		List list6 = (ArrayList) reList;
		IOHandler.writeSerializedObject("Reservations.db", list6);
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
		String[] name = {"Kyle", "Jing", "Yang", "Jacq", "Aman", "Matt", "Owen", "Jude", "Hapy", "Mark"};
		Integer i = 0;
		for (;i<10;i++){
			stList.add(new Staff(name[i],gender[rand.nextInt(2)], i, "Server"));
		}
	}
	
	public static void createMockOrders(ArrayList<Order> odList){
		
	}
	
	public static void createMockPackages(ArrayList<PromotionalPackage> pkList){
		
	}
	/**
	 * 
	 * @param itList
	 */
	public static void createMockItems(ArrayList<MenuItem> itList){
		Integer i = 0;
		String mainList[] = {"   Steak    " , "  Spaghetti " , "  Tortilla  " , "   Risotto  " , "Baked Salmon"};
		String mainDescription[] = {"Tender and juicy beef " , "   Hand made by chef  " , "      Crispy made     " , "   Creamy and yummy   " , "Fresh salmons everyday"};
		String drinksList[] = {"    Sprite    " , "Iced Lemon Tea" , " Orange Juice " , "   Red Wine   " , "     Beer     "};
		String drinksDescription[] = {"      Refreshing      " , "     Freshly made     " , "Made from real oranges" , "     Made in 1977     " , "        Cheers        "};
		String dessertsList[] = {"  Banana Ice Cream  " , "Strawberry Shortcake" , "     Cheesecake     " , "  Chocolate Sundae  " , "  Chocolate Mousse  "};
		String dessertsDescription[] = {"Made from banana bits " , "   Fresh and sweet    " , " Thick and cheeeeesy  " , "Perfect for a warm day" , "   Sweet and creamy   "};
		double mainpriceList[] = {23.90 , 12.50 , 8.80 , 15.70 , 19.80};
		double drinkspriceList[] = {3.00 , 3.50 , 4.00 , 16.50 , 11.50};
		double dessertspriceList[] = {5.90 , 6.50 , 7.70 , 3.50 , 8.20};
		for(;i<5;i++){
			itList.add(new MenuItem(
					String.valueOf(1)+i.toString(),
					CourseType.valueOf("main"),
					mainList[i],
					mainDescription[i],
					mainpriceList[i]));
			
			itList.add(new MenuItem(
					String.valueOf(2)+i.toString(),
					CourseType.valueOf("drinks"),
					drinksList[i],
					drinksDescription[i],
					drinkspriceList[i]));
			
			itList.add(new MenuItem(
					String.valueOf(3)+i.toString(),
					CourseType.valueOf("desserts"),
					dessertsList[i],
					dessertsDescription[i],
					dessertspriceList[i]));
		}
	}
	
	public static void createMockReservations(ArrayList<Reservation> reList){
		String contact = "111";
		int pax =0;
		for (Integer i = 0; i<27; i++){
			LocalDateTime arrival = LocalDateTime.of(2016, 11, 9, 18, 00, 00);
			if (i<5) pax =10;
			else if (i<10) pax = 8;
			else if (i<20) pax = 4;
			else pax = 2;
			reList.add(new Reservation(contact+i.toString(), arrival, pax, i));
		}
	}
}
