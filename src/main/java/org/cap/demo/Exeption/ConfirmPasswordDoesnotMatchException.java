package org.cap.demo.Exeption;

public class ConfirmPasswordDoesnotMatchException extends Exception{

	public ConfirmPasswordDoesnotMatchException(String msg) {
		super(msg);
	}
}
