package package1;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class UserInfoReceiver extends JFrame implements ActionListener {
	private JButton user;
	private JButton client;
	private BackgroundPanel background;
	UserButtons usrbtn = new UserButtons();
	private JPanel selectionPanel;

	public UserInfoReceiver() {
		// Creates basic JFrame and title
		setTitle("Vehicular Cloud Console");
		setSize(500, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Set Background
		BackgroundPanel background = new BackgroundPanel();
		background.setLayout(new BorderLayout());
		this.setContentPane(background);
		showSlectionScreen();

		setVisible(true);
	}

	private void showSlectionScreen() {
		JPanel selectionPanel = new JPanel(new BorderLayout());

		// Title bar (used invisible lettering and html/br to create new line)
		JLabel titleLabel = new JLabel("<html>Vehicular Cloud Console<br/>‎ ‎ ‎ ‎ ‎ ‎Please Select Title:</html>");
		titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		selectionPanel.setLayout(new BorderLayout());
		selectionPanel.add(titleLabel, BorderLayout.NORTH);

		add(selectionPanel, BorderLayout.NORTH);
		;

		// buttons
		user = usrbtn.getUserBtn();
		client = usrbtn.getClientBtn();
		usrbtn.setOpaque(false);
		this.setVisible(true);
		add(usrbtn, BorderLayout.CENTER);

		user.addActionListener(this); // TO SWITCH TO THE OTHER FRAMES
		client.addActionListener(this);

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new UserInfoReceiver().setVisible(true));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == user) {
			this.dispose();
			new Owner();
		}
		if (e.getSource() == client) {
			this.dispose();
			new Customer();
		}

	}
}
