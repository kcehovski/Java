package booking.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;

import booking.data.DataUtils;
import booking.exceptions.AirlineBookingException;
import booking.models.Airport;
import booking.models.SeatType;

public class PopulateComboBox {

	public static void populateAirportComboBox(JComboBox comboBox, boolean addDefaultOption) throws AirlineBookingException {
		//clear combo box
		comboBox.removeAllItems();
		
		List<Airport> listOfAirports = new ArrayList<Airport>();
		listOfAirports = DataUtils.getAllAirports();
		Collections.sort(listOfAirports);
		// this is for combo boxes in flight booking screen, 
		// combo box in delete airport of maintenance screen cannot have empty airport
		if (addDefaultOption){
			Airport emptyAirport = new Airport("-", "-");
			comboBox.addItem(emptyAirport);
		}
		Airport airport;
		
		// add items to combo box
		for (int i = 0; i < listOfAirports.size(); i++){
			airport = listOfAirports.get(i);
			comboBox.addItem(airport);
		}
	}
	
	public static void populateTypeOfSeatComboBox(JComboBox typeOfSeatComboBox) throws AirlineBookingException {
		
		SeatType[] seatTypes = SeatType.values();
		
		//add items to combo box
		for (int i = 0; i < seatTypes.length; i++){
			typeOfSeatComboBox.addItem(seatTypes[i]);
		}
	}
}
