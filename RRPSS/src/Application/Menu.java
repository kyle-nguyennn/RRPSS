package Application;
import java.util.ArrayList;

public class Menu {
	private ArrayList<PromotionalPackage> promotionalPackageList;
	private ArrayList<String> typeList;
	private ArrayList<MenuItem> mainCourse;
	private ArrayList<MenuItem> drinks;
	private ArrayList<MenuItem> desserts;
	
	Menu(){}
	public void updateName(String type, int itemNo, String newName){
		//TODO
	}
	public void updatePrice(String type, int itemNo, String newPrice){
		//TODO
	}
	
	public void updateDescription(String type, int itemNo, String newDesription){
		//TODO
	}
	public void removeMenuItem(String type, int iemNo){
		//TODO
	}
	
	public void createPromotionalPackage(){
		//TODO
	}
	
	public void removePromotionalPackage(int packageNo){
		//TODO
	}
	
	public void updatePackageName(int packageNo, String newName){
		//TODO
	}
	
	public void updatePackagePrice(int packageNo, String newPrice){
		//TODO
	}
	
	public void addItemToPackage(int packageNo, MenuItem newItem, double newPrice){
		//TODO
	}
	
	public void removeItemFromPackage(int packageNo, int itemNo, double newPrice){
		//TODO
	}
	
	public MenuItem getMenuItem(String type, int itemNo){
		//TODO
		return new MenuItem();
	}
}