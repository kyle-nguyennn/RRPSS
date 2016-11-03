import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
	private int orderID;
	private int staffID;
	private int tableID;
	private LocalDateTime timeStamp;
	private ArrayList<MenuItem> menuItemsOrder;
	private ArrayList<PromotionalPackage> promotionalPackageOrder;
	
	Order(int orderID, int staffID, int tableID, LocalDateTime timeStamp, ArrayList<MenuItem> itemsOrder, ArrayList<PromotionalPackage> packageOrder){
		this.orderID = orderID;
		this.staffID = staffID;
		this.tableID = tableID;
		this.timeStamp = timeStamp;
		this.menuItemsOrder = itemsOrder;
		this.promotionalPackageOrder = packageOrder;
	}
	
	public int getOrderID(){
		return this.orderID;
	}
	public LocalDateTime getTimeStamp(){
		return this.timeStamp;
	}
	
	public void addMenuItemsOrder(MenuItem toAdd){
		this.menuItemsOrder.add(toAdd);
	}
	
	public void addPromotionalPackageOrder(PromotionalPackage toAdd){
		this.promotionalPackageOrder.add(toAdd);
	}
	
	@Override
	public String toString(){
		//TODO
		// what to print in order
		return this.toString();
	}
	
	public void showOrder(){
		System.out.println(this);
	}
}
