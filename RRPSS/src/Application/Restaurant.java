package Application;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.time.*;
import Helper.*;

/**
 * @author Nguyen Dang Duy Nghia
 * responsible for creating response for every user request
 */
public class Restaurant {
	/**
	 * instance of TablesManager
	 */
	private TablesManager tablesManager;
	/**
	 * instance of StaffManager
	 */
	private StaffManager staffManager;
	/**
	 * instance of OrderHistory
	 */
	private OrderHistory orderHistory;
	/**
	 * instance of Menu
	 */
	private Menu menu;
	/**
	 * instance Reserve
	 */
	private Reserve reserve;

	/**
	 * create new Restaurant, load all data from files
	 */
	Restaurant(){
		tablesManager = new TablesManager();
		staffManager = new StaffManager();
		orderHistory = new OrderHistory();
		menu = new Menu();
		reserve = new Reserve();
	}
	/**
	 * Update menu items
	 * @param sc - Read input from console
	 * @return
	 */
	public boolean updateMenuItem(Scanner sc) {
		String id;
		String newVal;
		System.out.println("\nChoose an action:" 
				+ "\n(1) Change item's name" 
				+ "\n(2) Change item's price"
				+ "\n(3) Change item's description" 
				+ "\n(4) Back");
		int opt =0;
		opt = SafeInput.safeRead(opt, sc);
		if (opt == 4)
			return false;
		sc.nextLine();
		System.out.println("Enter item ID:");	
		menu.viewMenuItem();
		id = sc.nextLine();
		while(!menu.isValid(id)){
			System.out.println("invalid menu item id. try again");
			id = sc.nextLine();
		}
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
		case 4:
			return false;
		default:
			System.out.println("Invalid choice!!!");
		}
		return true;
	}
	/**
	 * create new menu items
	 * @param sc - Read input from console
	 * @return
	 */
	public int createMenuItem(Scanner sc) {
		System.out.println("Course Type :" 
					+ "\n(1) Main course" 
					+ "\n(2) Drinks" 
					+ "\n(3) Desserts" 
					+ "\n(4) Back");
		int type = 0;
		type = SafeInput.safeRead(type, sc);
		if (type > 4 || type < 1) {
			System.out.println("Invalid choice!!!");
			return 5; // Ask again
		}
		if (type == 4)
			return 4;
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
	/**
	 * @param sc - Read input from console
	 * @return
	 */
	public String removeMenuItem(Scanner sc) {
		menu.viewMenuItem();
		System.out.println("What item you want to remove (Enter -1 to go back): ");
		String id = sc.nextLine();
		menu.removeMenuItem(id);
		return id;
	}
	/**
	 * update information of promotional package
	 * @param target - determine what to update, either name or price of the package
	 * @param id
	 * @param newVal
	 */
	public void updatePromoPack(int target, int id, String newVal) {
		if (menu.isValid(id)){
			switch (target) {
			case 1:
				menu.updatePackageName(id, newVal);
				break;
			case 2:
				menu.updatePackagePrice(id, Double.parseDouble(newVal));
				break;
			default:
			}
		} else {
			System.out.println("invalid id!!");
		}
	}
	/**
	 * add menu items into a package
	 * @param sc - Read input from console
	 */
	public void addItemToPackage(Scanner sc) {
		System.out.println("Choose package");
		menu.viewPackages();
		int packageID = -1;
		packageID = SafeInput.safeRead(packageID, sc);
		if (packageID == -1 || !menu.isValid(packageID)){
			System.out.println("package not exist!!");
			return;
		}
		System.out.println("Choose items you want to add to this package (Enter 0 when you are done):");
		menu.viewMenuItem();
		ArrayList<String> itemIDs = new ArrayList<String>();
		Integer id = 0;
		do {
			id = SafeInput.safeRead(id, sc);
			if (menu.isValid(id.toString()))
				itemIDs.add(id.toString());
		} while (id != 0);
		System.out.println("Enter new price for the package:");
		double newPrice =0;
		newPrice = SafeInput.safeRead(newPrice, sc);
		menu.addItemsToPackage(packageID, itemIDs, newPrice);
	}
	/**
	 * remove menu items from a package
	 * @param sc- Read input from console
	 */
	public void removeItemFromPackage(Scanner sc) {
		System.out.println("Choose package");
		menu.viewPackages();
		int packageNo=-1;
		packageNo = SafeInput.safeRead(packageNo, sc);
		if (packageNo == -1 || !menu.isValid(packageNo)){
			System.out.println("package not exist!!");
			return;
		}
		System.out.println("Choose items you want to remove from this package (Enter -1 when you are done):");
		menu.viewItemsFromPackage(packageNo);
		ArrayList<String> itemIDs = new ArrayList<String>();
		Integer id=-1;
		do {
			id = SafeInput.safeRead(id, sc);
			if (menu.isValid(id.toString()))
				itemIDs.add(id.toString());
		} while (id != -1);
		System.out.println("Enter new price for the package:");
		double newPrice =0;
		newPrice = SafeInput.safeRead(newPrice, sc);
		menu.removeItemsFromPackage(packageNo, itemIDs, newPrice);
	}
	/**
	 * create new promotional package
	 * @param sc - Read input from console
	 */
	public void createPromoPack(Scanner sc) {
		System.out.println("Enter package name:");
		String name = sc.nextLine();
		System.out.println("Enter the price for the package");
		Double price = sc.nextDouble();
		menu.createPromotionalPackage(name, price);
	}
	/**
	 * remove existing promotional package
	 * @param sc - Read input from console
	 */
	public void removePromoPack(Scanner sc) {
		System.out.println("Enter the package number you want to remove:");
		menu.viewPackages();
		int packageNo = sc.nextInt();
		menu.removePromotionalPackage(packageNo);
	}
	/**
	 * make order
	 * @param sc - Read input from console
	 */
	public void createNewOrder(Scanner sc) {
		System.out.println("Enter Staff ID (Enter -1 to cancel order):");
		int staffID = -1;
		staffID = SafeInput.safeRead(staffID, sc);
		if (staffID == -1)
			return;
		while (!staffManager.isValid(staffID)) {
			System.out.println("Invalid staff ID. Try again:");
			staffID = SafeInput.safeRead(staffID, sc);
			if (staffID == -1)
				return;
		}
		int tableID = -1;
		while (!tableAvail(tableID)) {
			System.out.println("Enter available Table ID (Enter -1 to cancel order)");
			showAvailableTables();
			tableID = SafeInput.safeRead(tableID, sc);
			if (tableID == -1)
				return;
		}
		
		System.out.println("Choose items from a la carte (Enter 1000 when you are done. Enter -1 to cancel order):");
		menu.viewMenuItem();
		ArrayList<String> itemIDs = new ArrayList<String>();
		Integer itemID = -1;
		do {
			itemID = SafeInput.safeRead(itemID, sc);
			if (itemID == -1)
				return;
			if (menu.isValid(itemID.toString())) {
				itemIDs.add(itemID.toString());
			}
		} while (itemID != 1000);

		System.out.println("Choose packages from package list (Enter 1000 when you are done. Enter -1 to cancel order):");
		menu.viewPackages();
		ArrayList<Integer> packageIDs = new ArrayList<Integer>();
		Integer packageID = -1;
		do {
			packageID = SafeInput.safeRead(packageID, sc);
			if (packageID == -1)
				return;
			if (menu.isValid(packageID)) {
				packageIDs.add(packageID);
			}
		} while (packageID != 1000);

		orderHistory.newOrder(staffID, tableID, itemIDs, packageIDs, menu);
		tablesManager.setStatus(tableID, TableStatus.occupied);
		System.out.println("Order has been made.");
	}
	/**
	 * view order
	 * @param sc - Read input from console
	 */
	public void viewOrder(Scanner sc) {
		System.out.println("What order (-1 to cancel)");
		orderHistory.show();
		int orderID = -1;
		orderID = SafeInput.safeRead(orderID, sc);
		if (orderID == -1)
			return;
		while(!orderHistory.isValid(orderID)){
			System.out.println("Invalid order ID. Try again:");
			orderID = SafeInput.safeRead(orderID, sc);
			if (orderID == -1)
				return;
		}
		orderHistory.viewOrder(orderID);
	}
	/**
	 * add menu items or packages to existing order
	 * @param sc - Read input from console
	 */
	public void addToOrder(Scanner sc) {
		int opt = 0;
		do {
			System.out.println("Choose an option:" 
						+ "\n(1) Add items" 
						+ "\n(2) Add packages"
						+ "\n(3) Back");
			opt = SafeInput.safeRead(opt, sc);
		} while (opt > 3 || opt < 1);
		int orderID = -1;
		System.out.println("What order?");
		orderHistory.show();
		while (!orderHistory.canAdd(orderID)){
			orderID = SafeInput.safeRead(orderID, sc);
			System.out.println("no order found! Try again");
		}
		switch (opt) {
		case 1:
			System.out.println("Choose items from a la carte (Enter 1000 when you are done. Enter -1 to cancel order):");
			menu.viewMenuItem();
			ArrayList<String> itemIDs = new ArrayList<String>();
			Integer itemID;
			do {
				itemID = sc.nextInt();
				if (itemID == -1)
					return;
				if (menu.isValid(itemID.toString())) {
					itemIDs.add(itemID.toString());
				}
			} while (itemID != 1000);
			orderHistory.addItemsToOrder(orderID, itemIDs, menu);
			return;
		case 2:
			System.out.println(
					"Choose packages from package list (Enter 1000 when you are done. Enter -1 to cancel order):");
			menu.viewMenuItem();
			ArrayList<Integer> packageIDs = new ArrayList<Integer>();
			Integer packageID;
			do {
				packageID = sc.nextInt();
				if (packageID == -1)
					return;
				if (menu.isValid(packageID)) {
					packageIDs.add(packageID);
				}
			} while (packageID != 1000);
			orderHistory.addPackagesToOrder(orderID, packageIDs, menu);
			return;
		case 3:
			break;
		default:
			System.out.println("Invalid choice");
		}
	}
	/**
	 * remove menu items or packages from existing order
	 * @param sc - Read input from console
	 */
	public void removeFromOrder(Scanner sc) {
		System.out.println("What order?");
		orderHistory.show();
		int orderID = sc.nextInt();
		System.out.println("Choose items to remove (-1 when finish):");
		orderHistory.viewOrder(orderID); // all items in orders are printed
											// according to their postion in
											// order
		// remove items by using its position in order
		ArrayList<Integer> pos = new ArrayList<Integer>();
		int chosen = 0;
		while (chosen != -1) {
			chosen = sc.nextInt();
			if (chosen == -1)
				break;
			pos.add(chosen - 1);
		}
		orderHistory.removeFromOrder(orderID, pos);

	}
	/**
	 * check if a table is available for booking or order
	 * @param tableID
	 * @return
	 */
	public boolean tableAvail(int tableID) {
		return tablesManager.isAvail(tableID, reserve);
	}
	/**
	 * show all available tables at the moment to the screen
	 */
	public void showAvailableTables() {
		tablesManager.showAvailableTables(reserve);
	}
	/**
	 * print order invoice when payment is made.
	 * the table associated with this order is released.
	 * @param sc - Read input from console
	 */
	public void printOrderInvoice(Scanner sc) {
		System.out.println("What order");
		orderHistory.show();
		int orderID = sc.nextInt();
		orderHistory.printOrderInvoice(orderID);
		tablesManager.setStatus(orderHistory.getOrder(orderID).getTableID(), TableStatus.vacated);
	}
	/**
	 * print total sale revenue within a period specified by opt
	 * @param sc - Read input from console
	 * @param opt - whether by date or by month. is checked in upper layer already, can only be either 1 or 2
	 */
	public void printSaleRevenue(Scanner sc, int opt) {
		switch (opt) {
		case 1:
			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			boolean flag = false;
			Date date = new Date();
			do {
				System.out.println("What date (dd/MM/yy). Enter -1 to go back");
				String in = sc.next();
				if (in.equals("-1"))
					return;
				try {
					date = formatter.parse(in);
					flag = false;
				} catch (ParseException e) {
					System.out.println("Wrong format. Try again");
					flag = true;
				}
			} while (flag);
			orderHistory.printRevenueReport(date);
			break;
		case 2:
			System.out.println("What month (enter number):");
			Month month = Month.of(sc.nextInt());
			orderHistory.printRevenueReport(month);
			break;
		}
	}
	/**
	 * create new reservation
	 * @param sc - Read input from console
	 */
	public void createReservation(Scanner sc) {
		System.out.println("Enter enter contact number:");
		String contact = sc.next();
		System.out.println("Enter arrival time (dd/MM/yy HH:mm):");
		DateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date arrTime = new Date();
		sc.nextLine();
		arrTime = SafeInput.safeRead(arrTime, sc, formatter);
		LocalDateTime arrivalTime = LocalDateTime.ofInstant(arrTime.toInstant(), ZoneId.systemDefault());
		System.out.println("Enter number of people:");
		int pax = sc.nextInt();
		int reservedTable = reserve.newReservation(contact, arrivalTime, pax,
				tablesManager.getAvailTables(reserve));
		tablesManager.reserve(reservedTable);
		System.out.println("Reserve successfully!");
	}
	/**
	 * check if a reservation exist
	 * @param sc - Read input from console
	 */
	public void checkReservation(Scanner sc) {
		System.out.println("Enter contact number used to book");
		String contact = sc.next();
		Reservation reservation = reserve.getReservation(contact);
		if (reservation == null)
			System.out.println("This number has not reserved.");
		else
			System.out.println(reservation);
	}
	/**
	 * remove existing reservation
	 * @param sc
	 */
	public void removeReservation(Scanner sc) {
		System.out.println("Enter contact number used to book");
		String contact = sc.next();
		Reservation reservation = reserve.getReservation(contact);
		if (reservation == null)
			System.out.println("This number has not reserved.");
		else {
			int releasedTable = reserve.removeReservation(reservation.getContact());
			tablesManager.release(releasedTable, reservation.getID());
		}
		System.out.println("Reservation removed successfully!");
	}
	/**
	 * save data back to files
	 */
	public void cleanUp() {
		orderHistory.cleanUp();
		menu.cleanUp();
		tablesManager.cleanUp();
		reserve.cleanUp();
	}
}
