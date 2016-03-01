package booking.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import booking.data.DataUtils;
import booking.exceptions.AirlineBookingException;

public class MaintenanceLogIn extends JFrame{
	private JLabel passwordLabel;
	private JButton okButton;
	private JButton helpButton;
	private JButton exitButton;
	private JPasswordField passwordField;
	private JOptionPane frame;
	private GridLayout gridLayout;
	private JPanel passwordPanel;
	private JPanel buttonsPanel;
	
	public MaintenanceLogIn(){
		super("Maintenance Log In");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gridLayout = new GridLayout(2, 1);
		setLayout(gridLayout);
		
		//create panels
		passwordPanel = new JPanel(new GridLayout(1, 2));
		passwordPanel.setBorder(new EmptyBorder(30,30, 30, 150));
		add(passwordPanel);
		buttonsPanel = new JPanel(new GridLayout(1, 3));
		buttonsPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
		add(buttonsPanel);
		
		//create label
		passwordLabel = new JLabel("Password");
		
		//create buttons
		okButton = new JButton("OK");
		exitButton = new JButton("EXIT");
		Icon questionIcon = new ImageIcon(getClass().getResource("question.png"));
		helpButton = new JButton("Help", questionIcon);
		
		//create password field
		passwordField = new JPasswordField(8);
		
		//add elements to frame
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		buttonsPanel.add(okButton);
		buttonsPanel.add(exitButton);
		buttonsPanel.add(helpButton);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// check that password is correct
				if (Arrays.equals(passwordField.getPassword(), new char[]{'K','a','t','a', 'r', 'i', 'n', 'a'})) {
					dispose(); // close maintenance log in screen
					// open maintenance screen 
					MaintenanceFrame maintenanceFrame = new MaintenanceFrame();
					maintenanceFrame.setSize(500, 500);
					maintenanceFrame.setVisible(true);
				}
				else{
					// shoe error message
					JOptionPane.showMessageDialog(frame,
						    "You entered wrong password.",
						    "Wrong password error",
						    JOptionPane.ERROR_MESSAGE);
					// clear password field
					passwordField.setText("");
				}
			}
		});
		
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// get and show help message
					JOptionPane.showMessageDialog(frame,
						    DataUtils.getHelpMessage("maintenanceLogInHelp"),
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
				dispose();   // close maintenance screen
				// open main menu screen
				MainMenu mainMenu = new MainMenu();
				mainMenu.setSize(400, 200);
				mainMenu.setVisible(true);	
			}
		});
	}
}
