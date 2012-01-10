package com.adora.learn.tree.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private final static String dateFormat = "yyyy:MMM:dd-HH:mm:ss:SSS";
	private final static SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
	private final static String userName = System.getProperty("user.name");
	
	public static void logp(String mssg){
		if(mssg != null){
			System.out.println(formatter.format(new Date()) + " - " + userName + " - "+mssg);
		}else{
			System.out.println(formatter.format(new Date()) + " - " + userName + " - ERROR - UNABLE TO PRINT NULL STRING");
		}
	}
}
