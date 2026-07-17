package main.Exception;

public class InvalidPaymentException extends RuntimeException{
	
	
	public InvalidPaymentException(String msg) {
		
		super(msg);
	}

}
