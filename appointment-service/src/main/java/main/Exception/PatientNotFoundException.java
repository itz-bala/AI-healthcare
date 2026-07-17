package main.Exception;

public class PatientNotFoundException extends RuntimeException{
	
	public PatientNotFoundException(String msg) {
		
		super(msg);
	}

}
