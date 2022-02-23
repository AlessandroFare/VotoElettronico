package com.alessandrofare.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
 * Classe singleton per il sistema di logging. 
 * Viene scritto nel file log.txt tutto ciò che avviene nella classe controller, in modo da tenere sotto occhio come si comporta l'applicazione.
 */
public class Logger {
	
	private static Logger logger = null;
	
	private final String logFile = "log.txt";
	private PrintWriter writer;
	
	public Logger() {
		try {
			FileWriter fw = new FileWriter(logFile, true);
		    writer = new PrintWriter(fw, true);
		} catch (IOException e) {}
	}
	
	public static synchronized Logger getInstance(){
		if(logger == null)
			logger = new Logger();
		return logger;
	}
	
	public void log (String logging) {
		writer.println(logging + "\n");
	}
	

}
