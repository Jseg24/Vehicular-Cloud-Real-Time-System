package package1;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Customer extends JFrame implements ActionListener {

	private JButton submit;
	private JButton back;
	private JTextField client_ID;
	private JTextField jobDuration;
	private JTextField jobDeadline;
	private JLabel Client;
	private JLabel duration;
	private JLabel deadline;
	private JPanel panel;
	private JPanel panelButton;
	
	
	public Customer() {

		createButton();
		createTextField();
		createPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("User Page");
		this.setLayout(new BorderLayout());		
		this.setSize(500, 500);
		this.setResizable(false);
		panel.setBorder(new EmptyBorder(150,75,150,75));
		this.add(panel, BorderLayout.CENTER);
		this.add(panelButton, BorderLayout.SOUTH);

		this.setVisible(true);

	}

	public static void main(String[] args) {
		new Customer();
	}

	public void createButton() {
		submit = new JButton();
		submit.addActionListener(this);
		submit.setText("Submit");
		submit.setFocusable(false);

		back = new JButton();
		back.addActionListener(this);
		back.setText("back");
		back.setFocusable(false);

		panelButton = new JPanel();
		panelButton.setLayout(new BorderLayout());
		panelButton.add(submit, BorderLayout.EAST);
		panelButton.add(back, BorderLayout.WEST);

	}

	public void createTextField() {

		Client = new JLabel();
		Client.setText("Client ID (6 digits): ");
		client_ID = new JTextField();
		client_ID.setPreferredSize(new Dimension(10,10));

		duration = new JLabel();
		duration.setText("Approximate Job Duration (hours): ");
		jobDuration = new JTextField();
		jobDuration.setPreferredSize(new Dimension(10,10));


		deadline = new JLabel();
		deadline.setText("Job Deadline (Enter MM/DD/YYYY): ");
		jobDeadline = new JTextField();
		jobDeadline.setPreferredSize(new Dimension(10,10));
		
		

	}

	public void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.add(Client);
		panel1.add(client_ID);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(duration);
		panel2.add(jobDuration);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.add(deadline);
		panel3.add(jobDeadline);
		
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		

		add(panel);
	}
	 // Method to Validate Date Format (MM-DD-YYYY)
    private boolean isValidDate(String jobDeadline) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false); // Ensure strict format checking
        try {
            dateFormat.parse(jobDeadline);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
            try {
                // Convert inputs from text fields to ints
                int clientID = Integer.parseInt(client_ID.getText());
                int jobHours = Integer.parseInt(jobDuration.getText().trim());
                
                String deadlineText = jobDeadline.getText().trim();
                if (!isValidDate(deadlineText)) {
                    System.out.println("Error: Invalid date format. Please use MM-DD-YYYY.");
                    return;
                }
               
                new Storing(clientID, jobHours, deadlineText);
                this.dispose();
                new UserInfoReceiver();
            } 
            catch (NumberFormatException ex) {
                System.out.println("Error: Invalid input! Ensure ID, Year, and Residency Time are numbers.");
            }
            }
    
			
		
		if (e.getSource() == back) {
			this.dispose();
			new UserInfoReceiver();
		}

	}
}
