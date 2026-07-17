package main.Exception;

public class DoctorNotFoundException extends RuntimeException{
	
	public DoctorNotFoundException(String msg) {
		
		super(msg);
	}

}
