package package1;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
    private Image pic;

    public BackgroundPanel() {
        // Load the image
        pic = new ImageIcon("ezgif-5fa53dfaba6ada.jpg").getImage();
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the entire panel
        g.drawImage(pic, 0, 0, getWidth(), getHeight(), this);
    }
}
