package dmi;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AWS extends JPanel{
	
	JLabel AWSSunflower = new JLabel();
	public float scale = 0;
	
	int getScale(double val)
	{
		return (int)Math.round(val*scale);
	}
	
	public AWS() {
		setSize(getScale(200), getScale(100));
		setBackground(Color.BLACK);
		AWS_Sunflower();
	}
	
	public void AWS_Sunflower() {
		AWSSunflower.setIcon(new ImageIcon(getClass().getResource("/dmi/AWS_Sunflower_Isolated.png")));
		AWSSunflower.setVisible(true);
		add(AWSSunflower);
		AWSSunflower.setBounds(getScale(100), getScale(50), getScale(60), getScale(60));
	}
}
