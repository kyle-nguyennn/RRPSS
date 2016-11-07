package Application;
import java.util.*;

import Helper.IOHandler;
import java.io.*;
import java.time.LocalDateTime;

public class RestaurantApp {
	public static void main (String[] args) throws IOException{
		System.out.println(LocalDateTime.now());
		final Restaurant myRestaurant = new Restaurant();
		Scanner sc = new Scanner(System.in);
		while (prompt()){
			int opt = sc.nextInt();
			switch (opt){
			case 1:
				modifyMenu(sc, myRestaurant);
				break;
			case 2:
				myRestaurant.createNewOrder(sc);
				break;
			case 3:
				myRestaurant.viewOrder(sc);
				break;
			case 4:
				myRestaurant.addToOrder(sc);
				break;
			case 5:
				myRestaurant.removeFromOrder(sc);
				break;
			case 6:
				myRestaurant.printOrderInvoice(sc);
			case 7:
				printSaleRevenue(sc, myRestaurant);
			case 10:
				myRestaurant.cleanUp();
				return;
			}
		}
		
	}
	
	public static boolean prompt(){
		System.out.println("What would you like to perform:"
				+ "\n(1) Modify Menu"
				+ "\n(2) Create new order"
				+ "\n(3) View order"
				+ "\n(4) Add to order"
				+ "\n(5) Remove from order"
				+ "\n(6) Print order invoice"
				+ "\n(7) Print total revenue"
				+ "\n(10) Exit");
		return true;
	}
	
	public static void modifyMenu(Scanner sc, Restaurant myRestaurant){
		int opt = 0;
		while (opt != 5){
			System.out.println("\nModify Menu:"
					+ "\n(1) Modify a la carte item"
					+ "\n(2) Add/Remove a la carte item"
					+ "\n(3) Modify package"
					+ "\n(4) Add/Remove package"
					+ "\n(5) Back");
			opt = sc.nextInt();
			switch (opt){
			case 1: //Modify a la carte item
				while (myRestaurant.updateMenuItem(sc));
				break;
			case 2: //Add/remove ala carte items
				int action = 0;
				while (action != 3){
					System.out.println("Choose an action: "
							+ "\n(1) Add"
							+ "\n(2) Remove"
							+ "\n(3) Back");
					action = sc.nextInt();
					switch (action){
					case 1:
						while(myRestaurant.createMenuItem(sc)!=4);
						break;
					case 2:
						while(!myRestaurant.removeMenuItem(sc).equals("-1"));
						break;
					case 3:
						break;
					default:
						System.out.println("Invalid choice!!!");
					}
				}	
				break;
			case 3://modify package
				action =0;
				while(action != 5){
					System.out.println("Choose an action:"
							+ "\n(1) Change package name"
							+ "\n(2) Change package price"
							+ "\n(3) Add items to package"
							+ "\n(4) Remove items from package"
							+ "\n(5) Back");
					action = sc.nextInt();
					switch(action){
					case 1:
					case 2:
						System.out.println("Enter package id:");
						int id = sc.nextInt();
						if (action == 1)
							System.out.println("Enter new name");
						else
							System.out.println("Enter new price");
						String newVal = sc.nextLine();
						myRestaurant.updatePromoPack(action, id, newVal);
						break;
					case 3:
						myRestaurant.addItemToPackage(sc);
						break;
					case 4:
						myRestaurant.removeItemFromPackage(sc);
						break;
					case 5:
						break;
					default:
						System.out.println("Invalid choice!!!");
					}
				}
				break;
			case 4: // add/remove package
				action =0;
				while (action != 3){
					System.out.println("Choose an action:"
							+ "\n(1) Create new promotional package"
							+ "\n(2) Remove promotional package"
							+ "\n(3) Back");
					action = sc.nextInt();
					switch(action){
					case 1:
						myRestaurant.createPromoPack(sc);
						break;
					case 2:
						myRestaurant.removePromoPack(sc);
						break;
					case 3:
						break;
					default:
						System.out.println("Invalid choice!!!");
					}
				}
				break;
			case 5: return;
			default:
				System.out.println("Invalid choice!!!");
			}
		}
	}
	
	public static void printSaleRevenue(Scanner sc, Restaurant myRestaurant){
		System.out.println("(1) By Date"
				+ "\n(2) By Month"
				+ "\n(3) Back");
		int opt = sc.nextInt();
		switch(opt){
		case 1:
		case 2:
			myRestaurant.printSaleRevenue(sc, opt);
			break;
		case 3:
			break;
		default:
			System.out.println("Invalid choice");
		}
		
	}
}
