package package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TitleBar extends JPanel{

	JLabel TitleText = new JLabel("ToDoList");
	public TitleBar() {
		this.setPreferredSize(new Dimension(200, 60));
		this.setBackground(Color.yellow);
		
		TitleText.setPreferredSize(new Dimension(200,40));
        TitleText.setFont(new Font("sans-serif", Font.BOLD, 20));
        TitleText.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.TitleText);
        
	}
	
	
}
