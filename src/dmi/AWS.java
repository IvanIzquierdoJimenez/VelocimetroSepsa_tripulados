package dmi;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AWS {
	
	JLabel AWSSunflower = new JLabel();
	Pantalla pantalla = new Pantalla(80);
	public AWS() {
		
		AWSSunflower.setIcon(new ImageIcon(getClass().getResource("/dmi/")));
		AWSSunflower.setVisible(true);
		pantalla.panel.add(AWSSunflower);
		AWSSunflower.setBounds(pantalla.getScale(370), pantalla.getScale(430), pantalla.getScale(60), pantalla.getScale(60));
		
	}
}
