package package1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class VC_Frame extends JFrame implements ActionListener{
	private JButton cal;
	private BackgroundPanel background;
	private JPanel selectionPanel;
	
	VC vc = VC.getInstance();
	
	
		public VC_Frame() {
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
		JLabel titleLabel = new JLabel("<html>Vehicular Cloud Console<br/>‎</html>");
		titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 18));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		selectionPanel.setLayout(new BorderLayout());
		selectionPanel.add(titleLabel, BorderLayout.NORTH);

		add(selectionPanel, BorderLayout.NORTH);
		

		// buttons
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		centerPanel.setOpaque(false);

		cal = new JButton("Job Completion Time");
		cal.setToolTipText("Click for Completion");
		cal.setPreferredSize(new Dimension(200, 50));
		cal.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		centerPanel.add(cal);
		add(centerPanel, BorderLayout.CENTER);

		cal.addActionListener(this);
		setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VC_Frame().setVisible(true));
	}
	
	
	
		@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cal) {
			 
	         vc.jobCompletion();
		}
		
	}
}
