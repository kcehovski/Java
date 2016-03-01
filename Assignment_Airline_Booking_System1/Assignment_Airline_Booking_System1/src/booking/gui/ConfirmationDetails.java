package booking.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import booking.data.DataUtils;
import booking.exceptions.AirlineBookingException;
import booking.models.Airport;
import booking.models.Booking;
import booking.models.SeatType;

public class ConfirmationDetails extends JFrame{
	private GridLayout firstGridLayout;
	private JLabel destinationLabel;
	private JLabel destinationValueLabel;
	private JLabel firstDestinationLabel;
	private JLabel firstDestinationValueLabel;
	private JLabel secondDestinationLabel;
	private JLabel secondDestinationValueLabel;
	private JLabel nameLabel;
	private JLabel nameValueLabel;
	private JLabel referenceLabel;
	private JLabel referenceValueLabel;
	private JLabel departureDateLabel;
	private JLabel departureDateValueLabel;
	private JLabel firstDateLabel;
	private JLabel firstDateValueLabel;
	private JLabel secondDateLabel;
	private JLabel secondDateValueLabel;
	private JLabel titleDetailsLabel;
	private JLabel luggageLabel;
	private JLabel luggageValueLabel;
	private JButton confirmButton;
	private JButton exitButton;
	private JOptionPane frame;
	private JPanel flightsRowPanel;
	private JPanel nameReferenceNotificationRowPanel;
	private JPanel buttonRowPanel;
	private JPanel titleRowPanel;
	
	public ConfirmationDetails(final Airport departure, final Airport firstDestination, final Airport secondDestination, final String name, 
			final SeatType seatType, final Date departureDate, final Date firstDate, final Date secondDate, final boolean luggage){
		super ("Confirmation Details");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstGridLayout = new GridLayout(4, 1);
		setLayout(firstGridLayout);
		
		//create and add panels to frame
		titleRowPanel = new JPanel(new GridLayout(1, 1));
		titleRowPanel.setBorder(new EmptyBorder(20, 20, 20, 200));
		add(titleRowPanel);
		flightsRowPanel = new JPanel(new GridLayout(3, 4));
		flightsRowPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		add(flightsRowPanel);
		nameReferenceNotificationRowPanel = new JPanel(new GridLayout(3, 2));		
		nameReferenceNotificationRowPanel.setBorder(new EmptyBorder(0, 20, 20, 300));
		add(nameReferenceNotificationRowPanel);
		buttonRowPanel = new JPanel(new GridLayout(1, 2));
		buttonRowPanel.setBorder(new EmptyBorder(0, 200, 50, 200));
		add(buttonRowPanel);
		
		//create labels 
		titleDetailsLabel = new JLabel("Flight Details");
		destinationLabel = new JLabel("Departure");
		destinationValueLabel = new JLabel();
		firstDestinationLabel = new JLabel("First Destination");
		firstDestinationValueLabel = new JLabel();
		secondDestinationLabel = new JLabel("Second Destination");
		secondDestinationValueLabel = new JLabel();
		nameLabel = new JLabel("Name");
		nameValueLabel = new JLabel();
		referenceLabel = new JLabel("Reference");
		referenceValueLabel = new JLabel();
		departureDateLabel = new JLabel("Departure Date");
		departureDateValueLabel = new JLabel();
		firstDateLabel = new JLabel("First Destination Date");
		firstDateValueLabel = new JLabel();
		secondDateLabel = new JLabel("Second Destination Date");
		secondDateValueLabel = new JLabel();
		luggageLabel = new JLabel("Luggage");
		luggageValueLabel = new JLabel();
		
		//create buttons
		confirmButton = new JButton("Confirm");
		exitButton = new JButton("EXIT");
				
		//add labels to form
		titleRowPanel.add(titleDetailsLabel);
		flightsRowPanel.add(destinationLabel);
		flightsRowPanel.add(destinationValueLabel);
		flightsRowPanel.add(departureDateLabel);
		flightsRowPanel.add(departureDateValueLabel);		
		flightsRowPanel.add(firstDestinationLabel);
		flightsRowPanel.add(firstDestinationValueLabel);
		flightsRowPanel.add(firstDateLabel);
		flightsRowPanel.add(firstDateValueLabel);	
		flightsRowPanel.add(secondDestinationLabel);
		flightsRowPanel.add(secondDestinationValueLabel);
		flightsRowPanel.add(secondDateLabel);
		flightsRowPanel.add(secondDateValueLabel);	
		nameReferenceNotificationRowPanel.add(nameLabel);
		nameReferenceNotificationRowPanel.add(nameValueLabel);
		nameReferenceNotificationRowPanel.add(referenceLabel);
		nameReferenceNotificationRowPanel.add(referenceValueLabel);
		nameReferenceNotificationRowPanel.add(luggageLabel);
		nameReferenceNotificationRowPanel.add(luggageValueLabel);
		buttonRowPanel.add(confirmButton);
		buttonRowPanel.add(exitButton);
		
		//properties of elements
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		destinationValueLabel.setText(departure.toString());
		departureDateValueLabel.setText(formatter.format(departureDate));
		firstDestinationValueLabel.setText(firstDestination.toString());
		firstDateValueLabel.setText(formatter.format(firstDate));
		secondDestinationValueLabel.setText(secondDestination.toString());
		
		if(secondDate != null){
			secondDateValueLabel.setText(formatter.format(secondDate));
		}else{
			secondDateValueLabel.setText("-");
		}
		nameValueLabel.setText(name);
		referenceValueLabel.setText(Booking.generateRandomReference());
		
		if (luggage){
			luggageValueLabel.setText("Yes");
		}else{
			luggageValueLabel.setText("No");
		}
		
		
		confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Booking booking = new Booking(departure, firstDestination, secondDestination, name, seatType, departureDate, 
						firstDate, secondDate, luggageValueLabel.getText());
				try {
					//add booking to file and show information screen
					DataUtils.addNewBooking(booking);
					JOptionPane.showMessageDialog(frame,
						    "Booking saved!",
						    "Information",
						    JOptionPane.INFORMATION_MESSAGE);
					
					dispose(); //close confirmation details screen
				} catch (AirlineBookingException e1) {
					//show error message
					JOptionPane.showMessageDialog(frame,
						    e1.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		} );
		
		exitButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); //close confirmation details screen
				nameLabel.setText(""); //clear name text box 
			}
		});
	}
	
}
