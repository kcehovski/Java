package booking.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import booking.data.DataUtils;
import booking.exceptions.AirlineBookingException;

public class MainMenu extends JFrame {
	
	private JRadioButton flightBookingRadioButton;
	private JRadioButton maintenanceRadioButton;
	private GridLayout gridLayout;
	private ButtonGroup radioGroup;
	private JButton okButton;
	private JButton exitButton;
	private JButton helpButton;
	private JPanel radioButtonsPanel;
	private JPanel buttonsPanel;
	private JOptionPane frame;
	
	public MainMenu(){
		super ("Main Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gridLayout = new GridLayout(3, 1);
		setLayout(gridLayout);
		
		//create and add panels to frame
		radioButtonsPanel = new JPanel(new GridLayout(1, 2));
		radioButtonsPanel.setBorder(new EmptyBorder(30, 20, 10, 80));
		add(radioButtonsPanel);
		buttonsPanel = new JPanel(new GridLayout(1, 3));
		buttonsPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
		add(buttonsPanel);
		
		//create radio buttons
		flightBookingRadioButton = new JRadioButton("Book Flights", true);
		maintenanceRadioButton = new JRadioButton("Maintenance", false);
		
		//create buttons
		okButton = new JButton("OK");
		exitButton = new JButton("EXIT");
		Icon questionIcon = new ImageIcon(getClass().getResource("question.png"));
		helpButton = new JButton("Help", questionIcon);
		
		//add elements to frame
		radioButtonsPanel.add(flightBookingRadioButton);
		radioButtonsPanel.add(maintenanceRadioButton);
		buttonsPanel.add(okButton);
		buttonsPanel.add(exitButton);
		buttonsPanel.add(helpButton);
		
		//create logical relationship between radio buttons
		radioGroup = new ButtonGroup();
		radioGroup.add(flightBookingRadioButton);
		radioGroup.add(maintenanceRadioButton);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (flightBookingRadioButton.isSelected()){
					dispose();  //close main menu screen
					
					//open flight booking screen
					FlightBooking flightBooking;
					try {
						flightBooking = new FlightBooking();
						flightBooking.setSize(500, 500);
					    flightBooking.setVisible(true);
					} catch (AirlineBookingException e1) {
						JOptionPane.showMessageDialog(frame,
							    e1.getMessage(),
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					dispose();   //close main menu screen
					//open maintenance log in screen
					MaintenanceLogIn maintenanceLogIn = new MaintenanceLogIn();
					maintenanceLogIn.setSize(400, 200);
					maintenanceLogIn.setVisible(true);
				}
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();	 //close main menu screen
			}
		});
		
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//get message and show help screen
					JOptionPane.showMessageDialog(frame,
						    DataUtils.getHelpMessage("mainMenuHelp"),
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
