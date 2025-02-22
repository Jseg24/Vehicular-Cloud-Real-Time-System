package package1;

import java.awt.Color;
import java.awt.Dimension;


import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class UserButtons extends JPanel {
	
private JButton user;
private JButton client;
Border emptyborder = BorderFactory.createEmptyBorder();

public UserButtons() {
	this.setPreferredSize(new Dimension (400,30));
	this.setBackground(Color.black);
	
	user = new JButton("USER");
	user.setBorder(emptyborder);
	user.setToolTipText("Click if User");
	user.setPreferredSize(new Dimension (150,50));

	
	this.add(user);
	this.add(Box.createHorizontalStrut(60));
	client = new JButton("CLIENT");
	client.setBorder(emptyborder);
	client.setToolTipText("Click if client");
	client.setPreferredSize(new Dimension (150,50));
	this.add(client);
}
public JButton getUserBtn() {
	return user;
	
}
public JButton getClientBtn() {
	return client;
}
}