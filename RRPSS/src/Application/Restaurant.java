package Application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.time.*;
import Helper.*;

public class Restaurant {
	private ArrayList<Table> tableList;
	private ArrayList<Staff> staffList;
	private OrderHistory orderHistory;
	private Menu menu;

	Restaurant() throws IOException {
		ArrayList<Table> tbList = new ArrayList<Table>();
		tbList = (ArrayList) IOHandler.readSerializedObject("Tables.db");
		System.out.println(tbList);
		// TODO load staff from file db
		// orderHistory = new OrderHistory();
		menu = new Menu();
	}

	public boolean updateMenuItem(Scanner sc) {
		try{
			menu.viewMenuItem();
		} catch (NullPointerException e){
			System.out.println("EMPTY MENU!!!");
			return false;
		}
		String id;
		String newVal;
		System.out.println("\nChoose an action:"
				+ "\n(1) Change item's name"
				+ "\n(2) Change item's price"
				+ "\n(3) Change item's description"
				+ "\n(4) Back");
		int opt = sc.nextInt();
		
		System.out.println("Enter item ID");
		id = sc.nextLine();

		switch (opt) {
		case 1:
			System.out.println("Enter new name: ");
			newVal = sc.nextLine();
			menu.updateItemName(id, newVal);
			break;
		case 2:
			System.out.println("Enter new price: ");
			newVal = sc.nextLine();
			menu.updateItemPrice(id, Double.parseDouble(newVal));
			break;
		case 3:
			System.out.println("Enter new description: ");
			newVal = sc.nextLine();
			menu.updateItemDescription(id, newVal);
			break;
		case 4: return false;
		default:
			System.out.println("Invalid choice!!!");
		}
		return true;
	}
	
	public int createMenuItem(Scanner sc) {
		System.out.println("Course Type :"
				+"\n(1) Main course"
				+ "\n(2) Drinks"
				+ "\n(3) Desserts"
				+ "\n(4) Back");
		int type = sc.nextInt();
		if (type > 4 || type <1){
			System.out.println("Invalid choice!!!");
			return 5;
		}
		sc.nextLine();
		System.out.println("Enter name:");
		String name = sc.nextLine();
		System.out.println("Enter description:");
		String description = sc.nextLine();
		System.out.println("Enter price:");
		double price = sc.nextDouble();
		menu.createMenuItem(type, name, description, price);
		return type;
	}
	public String removeMenuItem(Scanner sc) {
		try {
			menu.viewMenuItem();
		} catch (NullPointerException e){
			System.out.println("EMPTY MENU");
			return "-1";
		}
		System.out.println("What item you want to remove (Enter -1 to go back): ");
		String id = sc.nextLine();
		menu.removeMenuItem(id);
		return id;
	}

	public void updatePromoPack(int target, int id, String newVal) {
		
		switch (target) {
		case 1:
			menu.updatePackageName(id, newVal);
			break;
		case 2:
			menu.updatePackagePrice(id, Double.parseDouble(newVal));
			break;
		default:
		}
	}

	public void addItemToPackage(Scanner sc) {
		System.out.println("Choose package");
		menu.viewPackages();
		int packageID = sc.nextInt();
		System.out.println("Choose items you want to add to this package (Enter -1 when you are done):");
		menu.viewMenuItem();
		ArrayList<String> itemIDs = new ArrayList<String>();
		Integer id;
		do{
			id = sc.nextInt();
			if (id != -1)
				itemIDs.add(id.toString());
		} while (id != -1);
		System.out.println("Enter new price for the package:");
		double newPrice = sc.nextDouble();
		menu.addItemsToPackage(packageID, itemIDs, newPrice);
	}
	public void removeItemFromPackage(Scanner sc) {
		System.out.println("Choose package");
		menu.viewPackages();
		int packageNo = sc.nextInt();
		System.out.println("Choose items you want to remove from this package (Enter -1 when you are done):");	
		menu.viewMenuItem();
		ArrayList<String> itemIDs = new ArrayList<String>();
		Integer id;
		do{
			id = sc.nextInt();
			if (id != -1)
				itemIDs.add(id.toString());
		} while (id != -1);
		System.out.println("Enter new price for the package:");
		double newPrice = sc.nextDouble();
		menu.removeItemsFromPackage(packageNo, itemIDs, newPrice);
	}

	public void createPromoPack(Scanner sc) {
		System.out.println("Enter package name:");
		String name = sc.nextLine();
		System.out.println("Enter the price for the package");
		Double price = sc.nextDouble();
		menu.createPromotionalPackage(name, price);
	}
	public void removePromoPack(Scanner sc) {
		System.out.println("Enter the package number you want to remove:");
		menu.viewPackages();
		int packageNo = sc.nextInt();
		menu.removePromotionalPackage(packageNo);
	}

	public void createNewOrder(Scanner sc) {
		System.out.println("Enter Staff ID:");
		int staffID = sc.nextInt();
		System.out.println("Enter available Table ID");
		showAvailableTables();
		int tableID = sc.nextInt();
		System.out.println("Choose items from a la carte (Enter -1 when you are done):");
		menu.viewMenuItem();
		ArrayList<String> itemIDs = new ArrayList<String>();
		Integer itemID;
		do {
			itemID = sc.nextInt();
			if (itemID != -1){
				itemIDs.add(itemID.toString());
			}
		} while (itemID != -1);
		
	}

	public void viewOrder() {

	}

	public void addToOrder() {

	}

	public void removeFromOrder() {

	}

	public boolean tableAvail(int tableID) {
		for (int i = 0; i < tableList.size(); i++) {
			if (tableList.get(i).getTableID() == tableID) {
				return (tableList.get(i).getStatus() == TableStatus.available);
			}
		}
		return true;
	}

	public void showAvailableTables() {
		ListIterator<Table> i = tableList.listIterator();
		while (i.hasNext()) {
			Table table = i.next();
			if (table.getStatus().equals(TableStatus.available))
				System.out.println(table);
		}

	}

	public void printOrderInvoice(int orderID) {

	}

	public void printSaleRevenue(Date date) {

	}

	public void printSaleRevenue(Month month) {

	}

	public void addTable(int numberOfSeat) {
		int newID = tableList.size();
		tableList.add(new Table(newID, numberOfSeat));
	}

	public void removeTable(int tableID) {
		for (int i = 0; i < tableList.size(); i++)
			if (tableList.get(i).getTableID() == tableID) {
				tableList.remove(i);
				break;
			}
		;
	}

	public void addStaff(Staff newStaff) {
		staffList.add(newStaff);
	}

	public void removeStaff(int staffID) {
		for (int i = 0; i < staffList.size(); i++)
			if (staffList.get(i).getStaffID() == staffID) {
				staffList.remove(i);
				break;
			}
		;
	}

}
