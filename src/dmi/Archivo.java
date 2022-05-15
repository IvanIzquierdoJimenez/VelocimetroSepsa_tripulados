package dmi;

import java.io.File;
import java.io.IOException;

import org.ini4j.*;

public class Archivo {
	
	Wini wini;
	
	public String ReadConfig(String group, String parameter) throws InterruptedException, InvalidFileFormatException, IOException
	{
		wini = new Wini(new File("../VelocimetroSepsa_tripulados/config.ini"));
		return wini.get(group, parameter);
	}
}
