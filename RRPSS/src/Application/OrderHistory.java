package Application;
//For the purpose of managing orders history and revenue report
import java.io.*;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;

import Helper.IOHandler;

public class OrderHistory {
	private ArrayList<Order> ordersList;
	OrderHistory() throws IOException{
		ordersList = new ArrayList<Order>();
		ordersList = (ArrayList)IOHandler.readSerializedObject("OrdersHistory.db");
	}
	public void newOrder(int staffID, int tableID, ArrayList<String> itemIDs, ArrayList<Integer> packageIDs, Menu menu){
		if (itemIDs.isEmpty() && packageIDs.isEmpty()){
			System.out.println("Please choose at least 1 item from the menu!!!");
			return;			
		}
		int orderID = ordersList.size();
		LocalDateTime timeStamp = LocalDateTime.now();
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		ArrayList<PromotionalPackage> packages = new ArrayList<PromotionalPackage>();
		for (int i=0; i<itemIDs.size(); i++){
			menuItems.add(menu.getMenuItem(itemIDs.get(i)));
		}
		for (int i=0; i<packageIDs.size(); i++){
			packages.add(menu.getPackage(packageIDs.get(i)));
		}
		ordersList.add(new Order(orderID, staffID, tableID, timeStamp, menuItems, packages));
	}
	
	public void viewOrder(int orderID){
		System.out.println(ordersList.get(orderID));
	}

	public void addItemsToOrder(int orderID, ArrayList<String> itemIDs, Menu menu){
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		for (String id:itemIDs)
			items.add(menu.getMenuItem(id));
		ordersList.get(orderID).addMenuItems(items);
	}
	public void addPackagesToOrder(int orderID, ArrayList<Integer> packageIDs, Menu menu){
		ArrayList<PromotionalPackage> packages = new ArrayList<PromotionalPackage>();
		for(Integer id:packageIDs){
			packages.add(menu.getPackage(id));
		}
		ordersList.get(orderID).addPromotionalPackges(packages);
	}
	public void removeFromOrder(int orderID, ArrayList<Integer> pos){
		ArrayList<String> itemIDs = new ArrayList<String>();
		ArrayList<Integer> packageIDs = new ArrayList<Integer>();
		
		removeItemFromOrder(orderID, itemIDs);
		removePackagesFromOrder(orderID, packageIDs);
	}
	public void removeItemFromOrder(int orderID, ArrayList<String> itemIDs){
		ordersList.get(orderID).removeItems(itemIDs);
	}
	public void removePackagesFromOrder(int orderID, ArrayList<Integer> packageIDs){
		ordersList.get(orderID).removePackages(packageIDs);
	}
	
	public void printOrderInvoice(int orderID){
		if (orderID > ordersList.size() || orderID < 0){
			System.out.println("Invalid ID");
			return;
		}
		ordersList.get(orderID).printOrderInvoice();
	}
	public void printRevenueReport(Month month){
		//TODO filter orders in OrderList by its month and print to the screen
		// use Order.forReport()
	}
	
	public void printRevenueReport(Date date){
		//TODO filter orders in OrderList by its date and print to the screen
		// use Order.forReport()
	}
	
	@Override
	public String toString(){
		String result = new String();
		for (Order order : ordersList)
			result.concat(order.info());
		return toString();
	}
	
	public void show(){
		//print out 10 recent orders' header
		System.out.println(this);
	}
	
	public void cleanUp(){
		IOHandler.writeSerializedObject("OrdersHistory.db", ordersList);
	}
}
