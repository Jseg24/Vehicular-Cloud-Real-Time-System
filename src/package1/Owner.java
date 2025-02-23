package package1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Owner extends JFrame implements ActionListener {

	private JButton submit;
	private JButton back;
	private JTextField owner_ID;
	private JTextField Vehi_info;
	private JTextField Approx_residency;
	private JLabel owner;
	private JLabel vehi;
	private JLabel approx;
	private JPanel panel;
	private JPanel panelButton;
	private JLabel model;
	private JTextField model_text;
	private JLabel year;
	private JTextField year_text;
	
	
	public Owner() {

		createButton();
		createTextField();
		createPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("User Page");
		this.setLayout(new BorderLayout());
		this.setSize(500, 500);
		this.setResizable(false);
		panel.setBorder(new EmptyBorder(120,75,100,75));
		this.add(panel, BorderLayout.CENTER);
		this.add(panelButton, BorderLayout.SOUTH);

		this.setVisible(true);

	}

	public static void main(String[] args) {
		new Owner();
	}

	public void createButton() {
		submit = new JButton();
		submit.addActionListener(this);
		submit.setText("Submit");
		submit.setFocusable(false);

		back = new JButton();
		back.setText("back");
		back.addActionListener(this);
		back.setFocusable(false);

		panelButton = new JPanel();
		panelButton.setLayout(new BorderLayout());
		panelButton.add(submit, BorderLayout.EAST);
		panelButton.add(back, BorderLayout.WEST);

	}

	public void createTextField() {

		owner = new JLabel();
		owner.setText("Owner ID(6 digits): ");
		owner_ID = new JTextField();
		owner_ID.setPreferredSize(new Dimension(10,10));

		vehi = new JLabel();
		vehi.setText("Vehicle Make: ");
		Vehi_info = new JTextField();
		Vehi_info.setPreferredSize(new Dimension(10,10));

		
		model = new JLabel();
		model.setText("Vehicle Model: ");
		model_text = new JTextField();
		model_text.setPreferredSize(new Dimension(10,10));
		

		year = new JLabel();
		year.setText("Vehicle Year: ");
		year_text = new JTextField();
		year_text.setPreferredSize(new Dimension(10,10));


		approx = new JLabel();
		approx.setText("Approximate Residency Time Of The Vehicle: ");
		Approx_residency = new JTextField();
		Approx_residency.setPreferredSize(new Dimension(10,10));
		
		

	}

	public void createPanel() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.add(owner);
		panel1.add(owner_ID);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(vehi);
		panel2.add(Vehi_info);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.add(model);
		panel3.add(model_text);
		
		JPanel panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel4.add(year);
		panel4.add(year_text);

		JPanel panel5 = new JPanel();
		panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));
		panel5.add(approx);
		panel5.add(Approx_residency);
		
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		

		add(panel);
	}

	 @Override
	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == submit) {
	            try {
	                // Convert inputs from text fields to int
	                int ownerID = Integer.parseInt(owner_ID.getText());
	                int year = Integer.parseInt(year_text.getText());
	                int residencyTime = Integer.parseInt(Approx_residency.getText());

	                // Create a new Storing instance (automatically saves data if valid)
	                new Storing(ownerID, Vehi_info.getText(), model_text.getText(), year, residencyTime);
	                this.dispose();
	                new UserInfoReceiver();
	            } catch (NumberFormatException ex) {
	                System.out.println("Error: Invalid input! Ensure ID, Year, and Residency Time are numbers.");
	            }
	        }
		
		if (e.getSource() == back) {
			this.dispose();
			new UserInfoReceiver();
		}

	}
}

