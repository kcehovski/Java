package booking.exceptions;

public class AirlineBookingException extends Exception{
	
	String errorMessage;

	public AirlineBookingException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
	

}
