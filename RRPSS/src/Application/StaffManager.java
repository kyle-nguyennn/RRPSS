package Application;
import java.util.*;

import Helper.IOHandler;

import java.io.*;
public class StaffManager {
	ArrayList<Staff> staffList;
	
	public StaffManager(){
		staffList = new ArrayList<Staff>();
		staffList = (ArrayList) IOHandler.readSerializedObject("Staff.db");
	}
	public boolean isValid(int staffID){
		return (staffID >= 0 && staffID < staffList.size());
	}
}
