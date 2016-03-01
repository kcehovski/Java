package booking.models;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public class Booking {

	private Airport departure;
	private Airport firstDestination;
	private Airport secondDestination;
	private Date departureDate;
	private Date firstDestinationDate;
	private Date secondDestinationDate;
	private String name;
	private String reference;
	private SeatType seatType;
	private String luggage;
	
	public Booking(Airport departure, Airport firstDestination, String name, SeatType seatType, 
			Date departureDate, Date firstDestinationDate, String luggage) {
		this.departure = departure;
		this.firstDestination = firstDestination;
		this.name = name;
		this.reference = generateRandomReference();
		this.seatType = seatType;
		this.departureDate = departureDate;
		this.firstDestinationDate = firstDestinationDate;
		this.luggage = luggage;
	}
	
	public Booking(Airport departure, Airport firstDestination, Airport secondDestination, String name, SeatType seatType, 
			Date departureDate, Date firstDestinationDate, Date secondDestinationDate, String luggage) {
		this.departure = departure;
		this.firstDestination = firstDestination;
		this.secondDestination = secondDestination;
		this.name = name;
		this.reference = generateRandomReference();
		this.seatType = seatType;
		this.departureDate = departureDate;
		this.firstDestinationDate = firstDestinationDate;
		this.secondDestinationDate = secondDestinationDate;
		this.luggage = luggage;
	}

	public Airport getDeparture() {
		return departure;
	}

	public Airport getFirstDestination() {
		return firstDestination;
	}

	public Airport getSecondDestination() {
		return secondDestination;
	}

	public String getName() {
		return name;
	}

	public String getReference() {
		return reference;
	}

	public SeatType getSeatType() {
		return seatType;
	}
	
	public static String generateRandomReference(){
		
		SecureRandom random = new SecureRandom();
		return new BigInteger(30, random).toString(32);
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}

	public Date getFirstDestinationDate() {
		return firstDestinationDate;
	}

	public Date getSecondDestinationDate() {
		return secondDestinationDate;
	}

	public String getLuggage() {
		return luggage;
	}

}
