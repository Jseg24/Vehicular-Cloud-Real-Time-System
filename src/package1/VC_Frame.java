package package1;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class VC_Frame extends JFrame implements ActionListener{
	private JButton calc;
	private JButton cars;
	private JButton edit;
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

		calc = new JButton("Job Completion Time");
		calc.setToolTipText("Click for Completion");
		calc.setPreferredSize(new Dimension(200, 50));
		calc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		cars = new JButton("List of cars");
		cars.setToolTipText("Click for list of cars");
		cars.setPreferredSize(new Dimension(200, 50));
		cars.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		edit = new JButton("Click to Edit Entries");
		edit.setToolTipText("Edit entries here");
		edit.setPreferredSize(new Dimension(200, 50));
		edit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		centerPanel.add(cars);
		centerPanel.add(calc);
		centerPanel.add(edit);
		add(centerPanel, BorderLayout.CENTER);

		calc.addActionListener(this);
		cars.addActionListener(this);
		edit.addActionListener(this);
		setVisible(true);
		//Buttons to edit jobs and cars
		JButton manageBtn = new JButton("Manage Jobs/Cars");
		manageBtn.addActionListener(e -> new EditDataFrame());
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new VC_Frame().setVisible(true));
	}
	
	
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == calc) {
				vc.loadJobsFromFile();
				vc.jobCompletion();
				displayCompletionTimes(); 
		}
			if(e.getSource() == cars) {
				vc.loadCarsFromFile();
				vc.carCompletion();
				displayCarfax();
				}
			if (e.getSource() == edit) {
				new EditDataFrame();
			}

}

	         

		private void displayCarfax() {
			 StringBuilder carStr = new StringBuilder();
			try (BufferedReader reader = new BufferedReader(new FileReader("vcCars.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                carStr.append(line).append("\n");
	            }
	        } catch (IOException ex) {
	        }
			 JOptionPane.showMessageDialog(this, carStr.toString(), "Car Completion Times", JOptionPane.INFORMATION_MESSAGE);
		}
		
	
		

		private void displayCompletionTimes() {
		        StringBuilder result = new StringBuilder();
		        
		        try (BufferedReader reader = new BufferedReader(new FileReader("VC.txt"))) {
		            String line;
		            while ((line = reader.readLine()) != null) {
		                result.append(line).append("\n");
		            }
		        } catch (IOException ex) {
		            result.append("Error reading job completion times.");
		        }

		        JOptionPane.showMessageDialog(this, result.toString(), "Job Completion Times", JOptionPane.INFORMATION_MESSAGE);
		    }
		}
