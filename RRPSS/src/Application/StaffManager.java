package Application;
import java.util.*;

import Helper.IOHandler;

import java.io.*;
/**
 * @author Nguyen Dang Duy Nghia
 *
 */
public class StaffManager {
	/**
	 * store all staff
	 */
	ArrayList<Staff> staffList;
	/**
	 * constructor
	 * load data from files
	 */
	public StaffManager(){
		staffList = new ArrayList<Staff>();
		staffList = (ArrayList) IOHandler.readSerializedObject("Staff.db");
		System.out.println("        Staff Name    |    Gender    |    Staff ID    |    Role\n "+staffList);
	}
	/**
	 * check if the staff exist
	 * @param staffID
	 * @return
	 */
	public boolean isValid(int staffID){
		return (staffID >= 0 && staffID < staffList.size());
	}
}
