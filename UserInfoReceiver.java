package package1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;





public class UserInfoReceiver extends JFrame {
	private JButton user;
	private JButton client;
	UserButtons usrbtn = new UserButtons();
	TitleBar title = new TitleBar();
	
	 private JTextField ownerIdField, vehicleInfoField, residencyTimeField;
	    private JTextField clientIdField, jobDurationField, jobDeadlineField;
	
	public UserInfoReceiver() {
		//creates basic Frame and title
		setTitle("Vehicular Cloud Console");
		setSize(500, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel selectionPanel = new JPanel();
		 //setbackgorund
		BackgroundPanel background = new BackgroundPanel();
		background.setLayout(new BorderLayout());
		//titlebar (used invisible lettering and html/br to create new line)
		selectionPanel.add(new JLabel("<html>Vehicular Cloud Console<br/>‎ ‎ ‎ ‎ ‎ Please Select Title:</html>"));
		this.setContentPane(background);
		add(selectionPanel, BorderLayout.NORTH);
		
		//buttons
		user = usrbtn.getUserBtn();
		client = usrbtn.getClientBtn();
		usrbtn.setOpaque(false);
		this.setVisible(true);
		add(usrbtn, BorderLayout.CENTER);

        // Panel for form inputs
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, -50, 25));
        inputPanel.setFont(new Font("Sans-serif", Font.BOLD, 90));

        // Owner Fields
        ownerIdField = new JTextField();
        vehicleInfoField = new JTextField();
        residencyTimeField = new JTextField();

        // Client Fields
        clientIdField = new JTextField();
        jobDurationField = new JTextField();
        jobDeadlineField = new JTextField();
       
        inputPanel.add(new JLabel("Owner ID:"));
        
        inputPanel.add(ownerIdField);
        inputPanel.add(new JLabel("Vehicle Information:"));
        inputPanel.add(vehicleInfoField);
        inputPanel.add(new JLabel("Residency Time:"));
        inputPanel.add(residencyTimeField);
        
        inputPanel.add(new JLabel("Client ID:"));
        inputPanel.add(clientIdField);
        inputPanel.add(new JLabel("Job Duration:"));
        inputPanel.add(jobDurationField);
        inputPanel.add(new JLabel("Job Deadline:"));
        inputPanel.add(jobDeadlineField);

        add(inputPanel, BorderLayout.SOUTH);
        inputPanel.setOpaque(false);
	}
	   public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new UserInfoReceiver().setVisible(true));
	    }
	}

