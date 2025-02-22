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
	private JTextField Vech_info;
	private JTextField Approx_residency;
	private JLabel owner;
	private JLabel vech;
	private JLabel approx;
	private JPanel panel;
	private JPanel panelButton;
	
	
	public Owner() {

		createButton();
		createTextField();
		createPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Client Page");
		this.setLayout(new BorderLayout());
		this.setSize(500, 500);
		this.setResizable(false);
		panel.setBorder(new EmptyBorder(150,75,150,75));
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
		owner.setText("Owner ID: ");
		owner_ID = new JTextField();
		owner_ID.setPreferredSize(new Dimension(10,10));

		vech = new JLabel();
		vech.setText("Vehicle Information: ");
		Vech_info = new JTextField();
		Vech_info.setPreferredSize(new Dimension(10,10));


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
		panel2.add(vech);
		panel2.add(Vech_info);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
		panel3.add(approx);
		panel3.add(Approx_residency);
		
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		

		add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			
		}
		
		if (e.getSource() == back) {
			this.dispose();
			new UserInfoReceiver();
		}

	}
}
