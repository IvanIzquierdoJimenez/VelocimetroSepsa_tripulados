package cpu;
import dmi.Archivo;
import dmi.Client;
import dmi.Pantalla;

public class CPU {
	
	public static Archivo arc = new Archivo();
	public CPU()
	{
		
	}
	
	private static void cpu_main() throws InterruptedException {
		String[] Conf = arc.ReadConfig("../VelocimetroSepsa_tripulados/config.txt");
		//p.v_max = Integer.parseInt(Conf[0]);
		Pantalla p = new Pantalla(Integer.parseInt(Conf[0]));
		Client c = new Client();
		p.updateModo(0);
		c.sendData("register(speed)");
		c.sendData("register(cruise_speed)");
		c.sendData("register(mode)");
		c.sendData("register(symbol)");
		if(p.TestInit(p) == true);
		else return;
		Thread.sleep(100);
		p.updateModo(Integer.parseInt(Conf[1]));
		p.updateSymbol(0);
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
		}
	}
	
	public static void main(String[] args)
	{
		try {
			cpu_main();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
