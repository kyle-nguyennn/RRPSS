package Application;
import java.io.Serializable;

public class Staff implements Serializable{
	private String name;
	private String gender;
	private int staffID;
	private String role;
	
	public Staff(String name, String gender, int ID, String role){
		this.name = name;
		this.gender = gender;
		this.staffID = ID;
		this.role = role;
	}
	
	public String getName(){
		return this.name;
	}
	public String getGender(){
		return this.gender;
	}
	
	public int getStaffID(){
		return this.staffID;
	}
	public String getRole(){
		return this.role;
	}
	@Override 
	public String toString(){
		String d = "|";
		return (name+d+gender+d+staffID+d+role);
	}
}
