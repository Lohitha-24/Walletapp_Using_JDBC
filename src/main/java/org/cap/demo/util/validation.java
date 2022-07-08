package org.cap.demo.util;

public class validation {
	
	public static boolean isFirstNameAvailable(String firstName) {
		if(firstName==null || firstName=="") {
			return false;
		}
		return true;
	}
}
