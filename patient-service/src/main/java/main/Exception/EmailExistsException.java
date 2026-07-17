package main.Exception;

public class EmailExistsException extends RuntimeException{
	
	public EmailExistsException(String msg) {
		
		super(msg);
	}

}
