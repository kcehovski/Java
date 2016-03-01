package booking.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import booking.data.DataUtils;
import booking.exceptions.AirlineBookingException;
import booking.models.Airport;

public class MaintenanceFrame extends JFrame {

	private GridLayout gridLayout;
	private JRadioButton addNewAirportRadioButton;
	private JRadioButton deleteAirportRadioButton;
	private ButtonGroup radioGroup;
	private JPanel addNewAirportPanel;
	private JPanel addRadioButtonPanel;
	private JPanel deleteRadioButtonPanel;
	private JPanel deleteAirportPanel;
	private JPanel buttonsPanel;
	private JLabel addAirportNameLabel;
	private JLabel addAirportCityLabel;
	private JLabel deleteAirportLabel;
	private JTextField airportNameTextField;
	private JTextField airportCityTextField;
	private JButton addButton;
	private JButton deleteButton;
	private JButton viewBookingFilesButton;
	private JButton exitButton;
	private JButton helpButton;
	private JComboBox deleteAirportComboBox;
	private JOptionPane frame;

	public MaintenanceFrame() {
		super("Maintenance");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gridLayout = new GridLayout(5, 1);
		setLayout(gridLayout);

		// create and add panels to frame
		addRadioButtonPanel = new JPanel(new GridLayout(1, 1));
		addRadioButtonPanel.setBorder(new EmptyBorder(20, 20, 0, 100));
		add(addRadioButtonPanel);
		addNewAirportPanel = new JPanel(new GridLayout(3, 2));
		addNewAirportPanel.setBorder(new EmptyBorder(10, 50, 0, 60));
		add(addNewAirportPanel);
		deleteRadioButtonPanel = new JPanel(new GridLayout(1, 1));
		deleteRadioButtonPanel.setBorder(new EmptyBorder(20, 20, 0, 100));
		add(deleteRadioButtonPanel);
		deleteAirportPanel = new JPanel(new GridLayout(2, 2));
		deleteAirportPanel.setBorder(new EmptyBorder(0, 50, 30, 60));
		add(deleteAirportPanel);
		buttonsPanel = new JPanel(new GridLayout(1, 3));
		buttonsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(buttonsPanel);

		// create radio buttons
		addNewAirportRadioButton = new JRadioButton("Book Flights", true);
		deleteAirportRadioButton = new JRadioButton("Maintenance", false);

		// create labels
		addAirportNameLabel = new JLabel("Airport name");
		addAirportCityLabel = new JLabel("Airport city");
		deleteAirportLabel = new JLabel("Airport");

		// create text fields
		airportNameTextField = new JTextField();
		airportCityTextField = new JTextField();

		// create buttons
		addButton = new JButton("ADD");
		deleteButton = new JButton("DELETE");
		viewBookingFilesButton = new JButton("View Booking files");
		exitButton = new JButton("EXIT");
		Icon questionIcon = new ImageIcon(getClass().getResource("question.png"));
		helpButton = new JButton("Help", questionIcon);

		// create combo boxes
		deleteAirportComboBox = new JComboBox();

		// create logical relationship between radio buttons
		radioGroup = new ButtonGroup();
		radioGroup.add(addNewAirportRadioButton);
		radioGroup.add(deleteAirportRadioButton);

		// add elements to frame
		addRadioButtonPanel.add(addNewAirportRadioButton);
		addNewAirportPanel.add(addAirportNameLabel);
		addNewAirportPanel.add(airportNameTextField);
		addNewAirportPanel.add(addAirportCityLabel);
		addNewAirportPanel.add(airportCityTextField);
		addNewAirportPanel.add(addButton);
		deleteRadioButtonPanel.add(deleteAirportRadioButton);
		deleteAirportPanel.add(deleteAirportLabel);
		deleteAirportPanel.add(deleteAirportComboBox);
		deleteAirportPanel.add(deleteButton);
		buttonsPanel.add(viewBookingFilesButton);
		buttonsPanel.add(exitButton);
		buttonsPanel.add(helpButton);

		// properties of elements
		deleteAirportComboBox.setMaximumRowCount(3);
		// get all components of deleteAirportPanel and disable it
		Component[] componentDisable = deleteAirportPanel.getComponents();
		for (int i = 0; i < componentDisable.length; i++) {
			componentDisable[i].setEnabled(false);
		}

		addNewAirportRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// get all components of deleteAirportPanel and disable it
				Component[] componentDisable = deleteAirportPanel.getComponents();
				for (int i = 0; i < componentDisable.length; i++) {
					componentDisable[i].setEnabled(false);
				}

				// get all components of addNewAirportPanel and enable it
				Component[] componentEnable = addNewAirportPanel.getComponents();
				for (int i = 0; i < componentEnable.length; i++) {
					componentEnable[i].setEnabled(true);
				}
			}
		});

		deleteAirportRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// get all components of addNewAirportPanel and disable it
				Component[] componentDisable = addNewAirportPanel.getComponents();
				for (int i = 0; i < componentDisable.length; i++) {
					componentDisable[i].setEnabled(false);
				}
				// get all components of deleteAirportPanel and enable it
				Component[] componentEnable = deleteAirportPanel.getComponents();
				for (int i = 0; i < componentEnable.length; i++) {
					componentEnable[i].setEnabled(true);
				}
				
				//populate combo box
				try {
					PopulateComboBox.populateAirportComboBox(deleteAirportComboBox, false);
				} catch (AirlineBookingException e1) {
					JOptionPane.showMessageDialog(frame, e1.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();  // close maintenance screen 
				// open mein menu screen
				MainMenu mainMenu = new MainMenu();
				mainMenu.setSize(400, 200);
				mainMenu.setVisible(true);
			}
		});

		viewBookingFilesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// open booking text file 
				Runtime runtime = Runtime.getRuntime();
				try {
					Process process = runtime.exec("C:\\Windows\\notepad.exe booking.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Airport newAirport = new Airport(airportNameTextField.getText(), airportCityTextField.getText());
				
				// checking for text fields
				if (airportNameTextField.getText().length() == 0
						|| !FlightBooking.isAlphaOrSpace(airportNameTextField.getText())
						|| airportCityTextField.getText().length() == 0
						|| !FlightBooking.isAlphaOrSpace(airportCityTextField.getText())) {
					JOptionPane.showMessageDialog(frame, "Name and City of Airport can only contain letters!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					// add new airport
					DataUtils.addNewAirport(newAirport);
				} catch (AirlineBookingException e1) {
					//show error message, clear text fields and don't save new airport
					JOptionPane.showMessageDialog(frame, e1.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					airportNameTextField.setText("");
					airportCityTextField.setText("");
					return;
				}
				// show info message and clear text fields
				JOptionPane.showMessageDialog(frame, "Airport Saved!", "Information", JOptionPane.INFORMATION_MESSAGE);
				airportNameTextField.setText("");
				airportCityTextField.setText("");

			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Airport departure = (Airport) deleteAirportComboBox.getSelectedItem();
				try {
					// delete chosen airport, show info message and repopulate combo box
					DataUtils.deleteAirportByName(departure.getName());
					JOptionPane.showMessageDialog(frame, "Airport Deleted!", "Information", JOptionPane.INFORMATION_MESSAGE);
					PopulateComboBox.populateAirportComboBox(deleteAirportComboBox, false);
				} catch (AirlineBookingException e1) {
					//show error message
					JOptionPane.showMessageDialog(frame, e1.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// get and show help message
					JOptionPane.showMessageDialog(frame,
						    DataUtils.getHelpMessage("maintenanceHelp"),
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
	}

}
