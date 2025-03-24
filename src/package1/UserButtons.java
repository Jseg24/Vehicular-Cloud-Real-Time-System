package package1;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UserButtons extends JPanel {

	private JButton user;
	private JButton client;

	// Static block to set the Nimbus Look and Feel
	Border emptyborder = BorderFactory.createEmptyBorder();

	static {
		try {

			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public UserButtons() {
		this.setPreferredSize(new Dimension(400, 30));
		this.setBackground(Color.black);

		user = new JButton("OWNER");

		user.setToolTipText("Click if Owner");
		user.setPreferredSize(new Dimension(150, 50));
		this.add(user);
		user.setFont(new Font("Times New Roman", Font.BOLD, 14));

		user.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(5, 10, 5, 10)));

		this.add(Box.createHorizontalStrut(60));

		client = new JButton("CLIENT");

		client.setToolTipText("Click if Client");
		client.setPreferredSize(new Dimension(150, 50));
		this.add(client);

		client.setFont(new Font("Times New Roman", Font.BOLD, 14));

		// Add border
		client.setBorder(new CompoundBorder(new LineBorder(Color.WHITE, 2), new EmptyBorder(5, 10, 5, 10)));

	}

	public JButton getUserBtn() {
		return user;

	}

	public JButton getClientBtn() {
		return client;
	}
}
