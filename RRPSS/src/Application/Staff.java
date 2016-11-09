package Application;
import java.io.Serializable;

/**
 * @author Nguyen Dang Duy Nghia
 * all attributes of an instance of this Staff class is immutable
 */
public class Staff implements Serializable{
	/**
	 * name of the staff
	 */
	private String name;
	/**
	 * gender of the staff
	 */
	private String gender;
	/**
	 * id of the staff - index in the staffList
	 */
	private int staffID;
	/**
	 * role of the staff
	 */
	private String role;
	/**
	 * constructor
	 * @param name
	 * @param gender
	 * @param ID
	 * @param role
	 */
	public Staff(String name, String gender, int ID, String role){
		this.name = name;
		this.gender = gender;
		this.staffID = ID;
		this.role = role;
	}
	/**
	 * get the name of this staff
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * get the gender of this staff
	 * @return
	 */
	public String getGender(){
		return this.gender;
	}
	/**
	 * get the id of this staff
	 * @return
	 */
	public int getStaffID(){
		return this.staffID;
	}
	/**
	 * get the role of this staff
	 * @return
	 */
	public String getRole(){
		return this.role;
	}
	@Override 
	public String toString(){
		if (gender.length() == 4)
		{
			return (	
				"         "+name+"       |     "+gender+ "     |        "+staffID+"       |   "+role+"\n"
						);
		}

		else
		{
			return (	
				"         "+name+"       |    "+gender+"    |        "+staffID+"       |   "+role+"\n"
						);
		}
	}
}
