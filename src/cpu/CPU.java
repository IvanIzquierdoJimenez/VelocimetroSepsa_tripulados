package cpu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.EventListener;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import org.ini4j.InvalidFileFormatException;

import dmi.Archivo;
import dmi.Client;
import dmi.Pantalla;

public class CPU {
	
	public static Archivo arc = new Archivo();
	public CPU() throws NumberFormatException, InvalidFileFormatException, InterruptedException, IOException
	{
		cpu_main();
	}
	
	private static void cpu_main() throws InterruptedException, NumberFormatException, InvalidFileFormatException, IOException {
		final Pantalla p = new Pantalla(Integer.parseInt(arc.ReadConfig("Config", "VelMax")));
		Client c = new Client();
		c.sendData("register(speed)");
		c.sendData("register(cruise_speed)");
		c.sendData("register(mode)");
		c.sendData("register(symbol)");
		c.sendData("register(level)");
		c.sendData("register(ASFA_icon)");
		c.sendData("register(AWS_icon)");
		if(p.TestInit() == true);
		else return;
		Thread.sleep(100);
		p.updateModo(Integer.parseInt(arc.ReadConfig("Config", "Modo")));
		p.updateSymbol(0);
		p.updateLevel(0);
		while(true)
		{
			String s = c.readData();
			if(s==null) return;
			if(s.startsWith("speed="))
			{
				p.updateReal(Float.parseFloat(s.substring(6).replace(',', '.')));
			}
			else if(s.startsWith("cruise_speed="))
			{
				p.updatePrefijada(Float.parseFloat(s.substring(13).replace(',', '.')));
			}
			else if(s.startsWith("mode="))
			{
				p.updateModo(Integer.parseInt(s.substring(5)));
			}
			else if(s.startsWith("symbol="))
			{
				p.updateSymbol(Integer.parseInt(s.substring(7)));
			}
			else if(s.startsWith("level="))
			{
				p.updateLevel(Integer.parseInt(s.substring(6)));
			}
			else if(s.startsWith("ASFA_icon="))
			{
				String[] icono = s.substring(10).split("_");
				int iconoASFA = Integer.parseInt(icono[0]);
				int status = Integer.parseInt(icono[1]);
				p.updateASFA(iconoASFA, status);
			}
			else if(s.startsWith("AWS_icon="))
			{
				String[] icono = s.substring(9).split("");
				int iconoAWS = Integer.parseInt(icono[0]);
				int status = Integer.parseInt(icono[1]);
				p.updateAWS(iconoAWS, status);
			}
			
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, NumberFormatException, InvalidFileFormatException, IOException
	{
		CPU cpu = new CPU();
		
	}
}
