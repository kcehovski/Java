package booking.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import booking.exceptions.AirlineBookingException;
import booking.models.Airport;
import booking.models.Booking;
import booking.models.SeatType;

public class DataUtils {

	//method for getting all airports from file
	public static List<Airport> getAllAirports() throws AirlineBookingException {
		List<Airport> airports = new ArrayList<Airport>();

		try {
			File file = new File("airports.txt");

			Scanner input = new Scanner(file);

			while (input.hasNextLine()) {
				String line = input.nextLine();
				Integer i = line.indexOf(',');
				String airportName = line.substring(0, i);
				String airportCity = line.substring(i + 2);

				Airport airport = new Airport(airportName, airportCity);

				airports.add(airport);
			}
			input.close();

		} catch (FileNotFoundException e) {
 			throw new AirlineBookingException("File not found!");
    	}
		
		//returns list of airport objects
		return airports;
	}

	//method for adding new airport to file
	public static void addNewAirport(Airport newAirport) throws AirlineBookingException {
		List<Airport> allAirports = getAllAirports();
		Airport airport;
		
		for (int i = 0; i < allAirports.size(); i++) {
			airport = allAirports.get(i);
			
			//if new airport already exists throw an exception and don't save it
			if (airport.equals(newAirport)) {
				throw new AirlineBookingException("That Airport already exists!");
			}
		}
		
		allAirports.add(newAirport);
		saveAirports(allAirports);
	}


	//method for deleting existing airport from file
	public static void deleteAirportByName(String name) throws AirlineBookingException {
		List<Airport> allAirports = getAllAirports();
		Airport airport;

		for (int i = 0; i < allAirports.size(); i++) {
			airport = allAirports.get(i);
			
			if (airport.getName().equals(name)) {
				allAirports.remove(i);
			}
		}
		saveAirports(allAirports);
	}
	
	//method that saves a list of airports to file
	private static void saveAirports(List<Airport> listAirports) throws AirlineBookingException{
		Airport airport;
		Formatter output = null;
		try {
			output = new Formatter("airports.txt");

			for (int i = 0; i < listAirports.size(); i++) {
				airport = listAirports.get(i);
				output.format("%s, %s", airport.getName(), airport.getCity());
				if (i != listAirports.size() - 1){
					output.format("\n");
				}
			}
		} catch (FileNotFoundException e) {
			throw new AirlineBookingException("File not found!");
		}
		output.close();
	}

	//method that saves new booking to file
	public static void addNewBooking(Booking booking) throws AirlineBookingException {
		String secondDestination = "";
		String departureDateStr, firstDestDateStr, secondDestDateStr;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		departureDateStr = formatter.format(booking.getDepartureDate());
		firstDestDateStr = formatter.format(booking.getFirstDestinationDate());
		
		//if second destination is not chosen
		if (booking.getSecondDestination() == null || booking.getSecondDestination().getName().equals("-")){
			secondDestination = " - ";
			secondDestDateStr = "-";
		}
		else {
			String name = booking.getSecondDestination().getName();
			String city = booking.getSecondDestination().getCity();
			secondDestination = name + " - " + city; 
			secondDestDateStr = formatter.format(booking.getSecondDestinationDate());
		}
		
		//write in file
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("booking.txt", true)))){
		    out.printf("%s, %s, %s - %s, %s - %s, %s, %s, %s, %s, %s, %s\n",
		    		booking.getReference(), booking.getName(), 
		    		booking.getDeparture().getName(), booking.getDeparture().getCity(),
		    		booking.getFirstDestination().getName(), booking.getFirstDestination().getCity(), secondDestination, 
		    		booking.getSeatType(),
		    		departureDateStr, firstDestDateStr, secondDestDateStr, booking.getLuggage()
		    		);
		}
		catch (IOException e) {
			throw new AirlineBookingException("Unable to save booking details!");
		}

	}
	
	//method for reading help message from file 
	public static String getHelpMessage(String nameOfFile) throws AirlineBookingException{
		String topic;
		String message = "";
		topic = nameOfFile + ".txt"; //add .txt to string so it can be passed to class File
		
		try {
			File file = new File(topic);

			Scanner input = new Scanner(file);

			while (input.hasNextLine()) {
				message += input.nextLine() + "\n";
			}
			input.close();

		} catch (FileNotFoundException e) {
 			throw new AirlineBookingException("File not found!");
    	}
		
		return message;
	}

}
