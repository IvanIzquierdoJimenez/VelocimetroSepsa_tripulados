package dmi;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;

import org.ini4j.InvalidFileFormatException;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.TimerTask;

public class Pantalla extends JFrame{

	Archivo archivo = new Archivo();
	
	int AngIni = -29;
	int Ang = 238;
	int div = 1;
	float v_pref = 0;
	static float v_act = 0;
	public int v_max = 160;
	int centx = 225;
	int centy = 175+40;
	Velocidad vel;
	JLabel spd;
	JLabel modo;
	JLabel symb;
	JLabel dr = new JLabel();
	JLabel lv = new JLabel();
	Border c;
	Border rect;
	JPanel panel = new JPanel();
	Icon[] iconmodo;
	Icon[] level_s;
	Icon[] ASFA;
	Icon[] AWS;
	JLabel AWS_Sunflower = new JLabel();
	JLabel ASFA_Frenar = new JLabel();
	JLabel ASFA_VLC = new JLabel();
	JLabel ASFA_Parada = new JLabel();
	JLabel ASFA_VL = new JLabel();
	JLabel ASFA_CV = new JLabel();
	JLabel ASFA_Eficacia = new JLabel(); 
	JLabel ASFA_Rec = new JLabel();
	JLabel ASFA_Alarma = new JLabel();
	JLabel ASFA_Rearme = new JLabel();
	JLabel ASFA_RebaseAut = new JLabel();
	JLabel ASFA_Soneria = new JLabel();
	JLabel ASFA_Conex = new JLabel();
	//float scale = /*1f*/ 1f;
	float scale = 0;
	int getScale(double val)
	{
		return (int)Math.round(val*scale);
	}
	public Pantalla(int vmax) throws InvalidFileFormatException, InterruptedException, IOException {
		 setTitle("VELOCIMETRO");
		 scale = Float.parseFloat(archivo.ReadConfig("Config", "Escala"));
		 setSize(getScale(465), getScale(550));
		 setBackground(Color.BLACK);
		 setUndecorated(Boolean.parseBoolean(archivo.ReadConfig("Config", "PantOcup")));
		 setVisible(true);
		 setResizable(false);
		 if(Boolean.parseBoolean(archivo.ReadConfig("Config", "PantComp")) == true) setExtendedState(JFrame.MAXIMIZED_BOTH);
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 v_max = vmax;
		 add(panel);
		 PanelDisplay();
	}
	public void updateReal(float curr)
	{
		v_act = curr;
		spd.setText(Integer.toString(Math.round(v_act)));
		repaint();
	}
	public void updatePrefijada(float curr)
	{
		v_pref = curr;
		revalidate();
		repaint();
	}
	String modos[] = {"MANUAL","VEL. PREFIJADA", "TALLER", "ENGANCHE"};
	public void updateModo(int curr)
	{
		modo.setText(modos[curr]);
	}
	public void updateSymbol(int curr)
	{
		dr.setIcon(iconmodo[curr]);
		dr.setBounds(getScale(300), getScale(300), getScale(32), getScale(32));
	}
	public void updateLevel(int curr)
	{
		lv.setIcon(level_s[curr]);
		lv.setHorizontalAlignment(JLabel.CENTER);
	    lv.setVerticalAlignment(JLabel.CENTER);
		lv.setBounds(getScale(195), getScale(300), getScale(62), getScale(32));
	}
	public void updateASFA(int curr, int status)
	{
		switch(curr) 
		{
		case 0:
			if(status == 1)
			{
				ASFA_Frenar.setIcon(ASFA[0]);
				ASFA_Frenar.setVisible(true);
				panel.add(ASFA_Frenar);
				ASFA_Frenar.setBounds(getScale(20), getScale(360), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_Frenar.setVisible(false);
			break;
		case 1:
			if(status == 1)
			{
				ASFA_VLC.setIcon(ASFA[1]);
				ASFA_VLC.setVisible(true);
				panel.add(ASFA_VLC);
				ASFA_VLC.setBounds(getScale(90), getScale(360), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_VLC.setVisible(false);
			break;
		case 2:
			if(status == 1)
			{
				ASFA_Parada.setIcon(ASFA[2]);
				ASFA_Parada.setVisible(true);
				panel.add(ASFA_Parada);
				ASFA_Parada.setBounds(getScale(160), getScale(360), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_Parada.setVisible(false);
			break;
		case 3:
			if(status == 1)
			{
				ASFA_VL.setIcon(ASFA[3]);
				ASFA_VL.setVisible(true);
				panel.add(ASFA_VL);
				ASFA_VL.setBounds(getScale(230), getScale(360), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_VL.setVisible(false);
			break;
		case 4:
			if(status == 1)
			{
				ASFA_CV.setIcon(ASFA[4]);
				ASFA_CV.setVisible(true);
				panel.add(ASFA_CV);
				ASFA_CV.setBounds(getScale(300), getScale(360), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_CV.setVisible(false);
			break;
			
		case 5:
			if(status == 1)
			{
				ASFA_Eficacia.setIcon(ASFA[5]);
				ASFA_Eficacia.setVisible(true);
				panel.add(ASFA_Eficacia);
				ASFA_Eficacia.setBounds(getScale(370), getScale(360), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_Eficacia.setVisible(false);
			break;
			
		case 6:
			if(status == 1)
			{
				ASFA_Rec.setIcon(ASFA[6]);
				ASFA_Rec.setVisible(true);
				panel.add(ASFA_Rec);
				ASFA_Rec.setBounds(getScale(20), getScale(430), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_Rec.setVisible(false);
			break;
		
		case 7:
			if(status == 1)
			{
				ASFA_Alarma.setIcon(ASFA[7]);
				ASFA_Alarma.setVisible(true);
				panel.add(ASFA_Alarma);
				ASFA_Alarma.setBounds(getScale(90), getScale(430), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_Alarma.setVisible(false);
			break;
			
		case 8:
			if(status == 1)
			{
				ASFA_Rearme.setIcon(ASFA[8]);
				ASFA_Rearme.setVisible(true);
				panel.add(ASFA_Rearme);
				ASFA_Rearme.setBounds(getScale(160), getScale(430), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_Rearme.setVisible(false);
			break;
			
		case 9:
			if(status == 1)
			{
				ASFA_RebaseAut.setIcon(ASFA[9]);
				ASFA_RebaseAut.setVisible(true);
				panel.add(ASFA_RebaseAut);
				ASFA_RebaseAut.setBounds(getScale(230), getScale(430), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_RebaseAut.setVisible(false);
			break;
			
		case 10:
			if(status == 1)
			{
				ASFA_Soneria.setIcon(ASFA[10]);
				ASFA_Soneria.setVisible(true);
				panel.add(ASFA_Soneria);
				ASFA_Soneria.setBounds(getScale(300), getScale(430), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_Soneria.setVisible(false);
			break;
			
		case 11:
			if(status == 1)
			{
				ASFA_Conex.setIcon(ASFA[11]);
				ASFA_Conex.setVisible(true);
				panel.add(ASFA_Conex);
				ASFA_Conex.setBounds(getScale(370), getScale(430), getScale(60), getScale(60));
			}
			else if(status == 0) ASFA_Conex.setVisible(false);
			break;
		}
		
	}
	
	public void updateAWS(int curr, int status) 
	{
		switch (curr) {
		case 0:
			if(status == 1)
			{
				AWS_Sunflower.setIcon(AWS[0]);
				AWS_Sunflower.setVisible(true);
				panel.add(AWS_Sunflower);
				AWS_Sunflower.setBounds(165, 350, 150, 150);
			}
			else if(status == 0) AWS_Sunflower.setVisible(false);
			break;
		case 1:
			if(status == 1)
			{
				AWS_Sunflower.setIcon(AWS[1]);
				AWS_Sunflower.setVisible(true);
				panel.add(AWS_Sunflower);
				AWS_Sunflower.setBounds(165, 350, 150, 150);
			}
			else if(status == 0) AWS_Sunflower.setVisible(false);
			break;
		case 2:
			if(status == 1)
			{
				AWS_Sunflower.setIcon(AWS[2]);
				AWS_Sunflower.setVisible(true);
				panel.add(AWS_Sunflower);
				AWS_Sunflower.setBounds(165, 350, 150, 150);
			}
			else if(status == 0) AWS_Sunflower.setVisible(false);
			break;
		}
	}
	
	public boolean TestInit(Pantalla p) throws InterruptedException
	{
		for(int i = 0; i <= v_max; i++)
		{
			p.updateReal(i);
			p.updatePrefijada(i);
			Thread.sleep(6);
		}
		for(int i = v_max; i >= 0; i--)
		{
			p.updateReal(i);
			p.updatePrefijada(i);
			Thread.sleep(6);
		}
		for(int y = 0; y <= modos.length - 1; y++)
		{
			p.updateModo(y);
			Thread.sleep(350);
		}
		return true;
	}
	public void PanelDisplay()
	{
		 panel.setBackground(Color.BLACK);
		 panel.setSize(getScale(465), getScale(550));
		 vel = new Velocidad(this);
		 panel.setLayout(null);
		 panel.add(vel);
		 vel.setBounds(0, 0, getScale(450), getScale(350));
		 for(int i=0; i<=v_max; i+=10)
		 {
			 JLabel j = new JLabel(Integer.toString(i));
			 j.setFont(new Font("Arial", Font.PLAIN, getScale(20)));
			 j.setForeground(Color.YELLOW);
			 j.setHorizontalAlignment(SwingConstants.CENTER);
			 int r = 125;
			 if(i<100) r = 130;
			 if(i==0) r = 135;
			 double cx = centx - r*Math.cos(Math.toRadians(AngIni+i/(float)v_max*Ang));
			 double cy = centy - r*Math.sin(Math.toRadians(AngIni+i/(float)v_max*Ang));
			 panel.add(j);
			 j.setBounds(getScale(cx-25), getScale(cy-10), getScale(50), getScale(20));
		 }
		 spd = new JLabel(Integer.toString(Math.round(v_act)));
		 spd.setFont(new Font("Arial", Font.PLAIN, getScale(26)));
		 spd.setForeground(Color.GREEN);
		 spd.setHorizontalAlignment(SwingConstants.CENTER);
		 Border b = BorderFactory.createLineBorder(Color.RED);
		 spd.setBorder(b);
		 panel.add(spd);
		 spd.setBounds(getScale(centx-30), getScale(centy-70), getScale(60), getScale(30));
		 
		 JLabel unit = new JLabel("km/h");
		 unit.setFont(new Font("Arial", Font.PLAIN, getScale(20)));
		 unit.setForeground(Color.BLUE);
		 unit.setHorizontalAlignment(SwingConstants.CENTER);
		 panel.add(unit);
		 unit.setBounds(getScale(centx-40), getScale(centy-40), getScale(80), getScale(30));
		 
		 modo = new JLabel();
		 modo.setFont(new Font("Arial", Font.PLAIN, getScale(14)));
		 modo.setForeground(Color.YELLOW);
		 modo.setHorizontalAlignment(SwingConstants.CENTER);
		 modo.setBorder(b);
		 updateModo(0);
		 panel.add(modo);
		 modo.setBounds(getScale(centx-60), getScale(centy), getScale(120), getScale(30));
		 
		 Border sl = BorderFactory.createLineBorder(Color.WHITE);
		 JLabel level = new JLabel();
		 level.setBorder(sl);
		 panel.add(level);
		 level.setBounds(getScale(195), getScale(300), getScale(62), getScale(32));
		 
		 Border sm = BorderFactory.createLineBorder(Color.WHITE);
		 JLabel symb = new JLabel();
		 symb.setBorder(sm);
		 panel.add(symb);
		 symb.setBounds(getScale(300), getScale(300), getScale(32), getScale(32));
		 
		 level_s = new ImageIcon[2];
		 level_s[0] = new ImageIcon(getClass().getResource("/dmi/LE_01.jpg"));
		 level_s[1] = new ImageIcon(getClass().getResource("/dmi/LE_02.jpg"));
		 panel.add(lv);
		 
		 iconmodo = new ImageIcon[3];
		 iconmodo[0] = new ImageIcon(getClass().getResource("/dmi/MO_13.jpg"));
		 iconmodo[1] = new ImageIcon(getClass().getResource("/dmi/MO_16.jpg"));
		 iconmodo[2] = new ImageIcon(getClass().getResource("/dmi/MO_19.jpg"));
		 panel.add(dr);
		 
		 ASFA = new ImageIcon[12];
		 ASFA[0] = new ImageIcon(getClass().getResource("/dmi/Frenar.jpg"));
		 ASFA[1] = new ImageIcon(getClass().getResource("/dmi/VLC.jpg"));
		 ASFA[2] = new ImageIcon(getClass().getResource("/dmi/Parada.jpg"));
		 ASFA[3] = new ImageIcon(getClass().getResource("/dmi/VL.jpg"));
		 ASFA[4] = new ImageIcon(getClass().getResource("/dmi/CV.jpg"));
		 ASFA[5] = new ImageIcon(getClass().getResource("/dmi/Eficacia.jpg"));
		 ASFA[6] = new ImageIcon(getClass().getResource("/dmi/Rec.jpg"));
		 ASFA[7] = new ImageIcon(getClass().getResource("/dmi/Alarma.jpg"));
		 ASFA[8] = new ImageIcon(getClass().getResource("/dmi/RearmeFreno.jpg"));
		 ASFA[9] = new ImageIcon(getClass().getResource("/dmi/RebaseAuto.jpg"));
		 ASFA[10] = new ImageIcon(getClass().getResource("/dmi/Soneria.jpg"));
		 ASFA[11] = new ImageIcon(getClass().getResource("/dmi/Conex.jpg"));
		 
		 AWS = new ImageIcon[3];
		 AWS[0] = new ImageIcon(getClass().getResource("/dmi/AWS_Sunflower_Isolated.png"));
		 AWS[1] = new ImageIcon(getClass().getResource("/dmi/AWS_Sunflower_Reminder.png"));
		 AWS[2] = new ImageIcon(getClass().getResource("/dmi/AWS_Sunflower_Clear.png"));
		 
		 JLabel vigil = new JLabel();
		 vigil.setIcon(new ImageIcon(getClass().getResource("../dmi/vigilancia.JPG")));
		 panel.add(vigil);
		 vigil.setBounds(getScale(10), getScale(10), getScale(50), getScale(44));
		 
	}
}
