package booking.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import booking.data.DataUtils;
import booking.exceptions.AirlineBookingException;
import booking.models.Airport;
import booking.models.SeatType;

public class FlightBooking extends JFrame {
	
	private JRadioButton oneDestinationRadioButton;
	private JRadioButton twoDestinationRadioButton;
	private JComboBox departureComboBox;
	private JComboBox firstDestinationComboBox;
	private JComboBox secondDestinationComboBox;
	private JComboBox typeOfSeatComboBox;
	private JButton okButton;
	private JButton exitButton;
	private JButton helpButton;
	private JLabel departureLabel;
	private JLabel firstDestinationLabel;
	private JLabel secondDestinationLabel;
	private JLabel typeOfSeatLabel;
	private JLabel nameLabel;
	private JTextField nameTextField;
	private GridLayout firstGridLayout;
	private JPanel radioButtonRowPanel;
	private JPanel choosDestinationsRowPanel;
	private JPanel nameAndSeatRowPanel;
	private JPanel buttonsRowPanel;
	private JPanel luggageRowPanel;
	private ButtonGroup radioGroup;
	private JCheckBox luggageCheckBox;
	private JOptionPane frame;
	private JSpinner departureDateSpinner;
	private JSpinner firstDateSpinner;
	private JSpinner secondDateSpinner;
	
	
	public FlightBooking() throws AirlineBookingException{
		super ("Flight Booking");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firstGridLayout = new GridLayout(5, 1);
		setLayout(firstGridLayout);
		
		//create and add panels to frame
		radioButtonRowPanel = new JPanel(new GridLayout(1, 2));
		radioButtonRowPanel.setBorder(new EmptyBorder(0, 50, 0, 20));
		add(radioButtonRowPanel);
		choosDestinationsRowPanel = new JPanel(new GridLayout(3, 3));
		choosDestinationsRowPanel.setBorder(new EmptyBorder(0, 20, 0, 100));
		add(choosDestinationsRowPanel);
		nameAndSeatRowPanel = new JPanel(new GridLayout(3, 2));
		nameAndSeatRowPanel.setBorder(new EmptyBorder(0, 20, 0, 100));
		add(nameAndSeatRowPanel);
		luggageRowPanel = new JPanel(new GridLayout(1, 1));
		luggageRowPanel.setBorder(new EmptyBorder(0, 20, 0, 100));
		add(luggageRowPanel);
		buttonsRowPanel = new JPanel(new GridLayout(1, 3));
		buttonsRowPanel.setBorder(new EmptyBorder(20, 70, 20, 70));
		add(buttonsRowPanel);
		
		//create radio buttons
		oneDestinationRadioButton = new JRadioButton("One destination", true);
		twoDestinationRadioButton = new JRadioButton("Two destinations", false);
		
		//create combo boxes 
		departureComboBox = new JComboBox();
		firstDestinationComboBox = new JComboBox();
		secondDestinationComboBox = new JComboBox();
		typeOfSeatComboBox = new JComboBox();
		
		//create labels
		departureLabel = new JLabel("Departure");
		firstDestinationLabel = new JLabel("First Destination");
		secondDestinationLabel = new JLabel("Second Destination");
		typeOfSeatLabel = new JLabel("Type Of Seat");
		nameLabel = new JLabel("Name");
		
		//create text field
		nameTextField = new JTextField();
		
		//create buttons
		okButton = new JButton("OK");
		Icon questionIcon = new ImageIcon(getClass().getResource("question.png"));
		helpButton = new JButton("Help", questionIcon);
		exitButton = new JButton("EXIT");
		
		//create check box
		luggageCheckBox = new JCheckBox("I will have extra luggage");
		
		//create date spinners
		departureDateSpinner = createDateSpinner();
		firstDateSpinner = createDateSpinner();
		secondDateSpinner = createDateSpinner();
		
		//add elements to frame
		radioButtonRowPanel.add(oneDestinationRadioButton);
		radioButtonRowPanel.add(twoDestinationRadioButton);
		choosDestinationsRowPanel.add(departureLabel);
		choosDestinationsRowPanel.add(departureComboBox);
		choosDestinationsRowPanel.add(departureDateSpinner);
		choosDestinationsRowPanel.add(firstDestinationLabel);
		choosDestinationsRowPanel.add(firstDestinationComboBox);
		choosDestinationsRowPanel.add(firstDateSpinner);
		choosDestinationsRowPanel.add(secondDestinationLabel);
		choosDestinationsRowPanel.add(secondDestinationComboBox);
		choosDestinationsRowPanel.add(secondDateSpinner);
		nameAndSeatRowPanel.add(typeOfSeatLabel);
		nameAndSeatRowPanel.add(typeOfSeatComboBox);
		nameAndSeatRowPanel.add(nameLabel);
		nameAndSeatRowPanel.add(nameTextField);
		luggageRowPanel.add(luggageCheckBox);
		buttonsRowPanel.add(okButton);
		buttonsRowPanel.add(exitButton);
		buttonsRowPanel.add(helpButton);
		
		//properties of elements
		departureComboBox.setMaximumRowCount(3);
		firstDestinationComboBox.setMaximumRowCount(3);
		secondDestinationLabel.setVisible(false);
		secondDestinationComboBox.setMaximumRowCount(3);
		secondDestinationComboBox.setVisible(false);
		secondDateSpinner.setVisible(false);
		
		//create logical relationship between radio buttons
		radioGroup = new ButtonGroup();
		radioGroup.add(oneDestinationRadioButton);
		radioGroup.add(twoDestinationRadioButton);
		
		//populate combo boxes
		PopulateComboBox.populateAirportComboBox(departureComboBox, true);
		PopulateComboBox.populateAirportComboBox(firstDestinationComboBox, true);
		PopulateComboBox.populateAirportComboBox(secondDestinationComboBox, true);
		PopulateComboBox.populateTypeOfSeatComboBox(typeOfSeatComboBox);
		
		//listener for the radio button
		twoDestinationRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//setting visibility of elements 
				secondDestinationLabel.setVisible(true);
				secondDestinationComboBox.setVisible(true);
				secondDateSpinner.setVisible(true);
			}
		});;
		
		oneDestinationRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//setting visibility of elements 
				secondDestinationLabel.setVisible(false);
				secondDestinationComboBox.setVisible(false);
				secondDateSpinner.setVisible(false);
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//fetch data from combo boxes, date spinners, check box and text field
				Airport departure = (Airport) departureComboBox.getSelectedItem();
				Airport firstDestination = (Airport) firstDestinationComboBox.getSelectedItem();
				Airport secondDestination = (Airport) secondDestinationComboBox.getSelectedItem();
				SeatType seatType = (SeatType) typeOfSeatComboBox.getSelectedItem();
				Date departureDestinationDate = (Date) departureDateSpinner.getValue();
				Date firstDestinationDate = (Date) firstDateSpinner.getValue();
				Date secondDestinationDate = null;
				
				//check that departure and first destination are not same and that they are selected
				if (oneDestinationRadioButton.isSelected()){
					if (departure.getName().equals("-") || firstDestination.getName().equals("-")){
						JOptionPane.showMessageDialog(frame,
							    "You didn't select Departure or Destination Airport!",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (departure.equals(firstDestination)){
						JOptionPane.showMessageDialog(frame,
							    "Departure and Destination Airport cannot be the same!",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				//check that departure and first destination are not same; first destination and second destination are not same
				//and that all airports are selected
				else if (twoDestinationRadioButton.isSelected()) {
					if (departure.getName().equals("-") || firstDestination.getName().equals("-") || secondDestination.getName().equals("-")){
						JOptionPane.showMessageDialog(frame,
							    "You didn't select Departure or Destination Airports!",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (departure.equals(firstDestination) || firstDestination.equals(secondDestination)){
						JOptionPane.showMessageDialog(frame,
							    "Departure and Destinations Airports cannot be the same!",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					secondDestinationDate = (Date) secondDateSpinner.getValue();
				}
				
				//check that all dates are in future, and one after another; they can be the same 
				Date now = new Date();
				if (departureDestinationDate.before(now) || firstDestinationDate.before(now) || (secondDestinationDate != null && secondDestinationDate.before(now))){
					JOptionPane.showMessageDialog(frame,
						    "Travel dates have to be in the future!",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(firstDestinationDate.before(departureDestinationDate) || (secondDestinationDate != null && secondDestinationDate.before(firstDestinationDate))){
					JOptionPane.showMessageDialog(frame,
						    "Travel dates not in order!",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//check that name text box is not empty and that characters are letters or spaces
				if (nameTextField.getText().length() == 0 || !isAlphaOrSpace(nameTextField.getText())){
					JOptionPane.showMessageDialog(frame,
						    "Name can only contain letters!",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//open confirmation details screen with fetched data
				ConfirmationDetails confirmationDetails = new ConfirmationDetails(departure, firstDestination, secondDestination, 
						nameTextField.getText(), seatType, departureDestinationDate, firstDestinationDate, secondDestinationDate,
						luggageCheckBox.isSelected());
				confirmationDetails.setSize(650, 350);
				confirmationDetails.setVisible(true);
				nameTextField.setText("");
			}
		});
		
		//event for help button
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//get and show message from file bookingHelp
					JOptionPane.showMessageDialog(frame,
						    DataUtils.getHelpMessage("bookingHelp"),
						    "Help Guide",
						    JOptionPane.INFORMATION_MESSAGE);
				} catch (AirlineBookingException e1) {
					JOptionPane.showMessageDialog(frame,
						    e1.getMessage(),
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); //close Flight booking screen and open main menu screen
				MainMenu mainMenu = new MainMenu();
				mainMenu.setSize(400, 200);
				mainMenu.setVisible(true);
			}
		});
	}

	private static JSpinner createDateSpinner() {
		SpinnerDateModel spinerDateModel = new SpinnerDateModel();
		JSpinner spinner = new JSpinner( spinerDateModel );
		((JSpinner.DateEditor) spinner.getEditor()).getFormat().applyPattern("dd/MM/yyyy");
		spinner.setValue(new Date());
		return spinner;
	}
	
	//check that string contains only letters and spaces
	public static boolean isAlphaOrSpace(String name) {
	    char[] chars = name.toCharArray();
	    //if first character is space then it is not correct
	    if (chars[0] == ' '){
	    	return false;
	    }
	    for (char c : chars) {
	        if(!(Character.isLetter(c) || Character.isSpaceChar(c))) {
	            return false;
	        }
	    }
	    return true;
	}
	
}
