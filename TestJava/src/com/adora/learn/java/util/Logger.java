package com.adora.test.java.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

public class Logger {
	private static Logger instance = new Logger();
	private final String userName = System.getProperty("user.name");
	
	private Logger() {
	}

	public static synchronized Logger getInstance() {
		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("ExecutionLog Class Cloning is not allowed");
	}

	private String getLogStamp() {
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.LOG_DATE_FORMAT);
		return (" ##" + userName + " - " + formatter.format(new java.util.Date()) + " - ");
	}

	public void log(String error, Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionAsStrting = sw.toString();
		System.out.println(getLogStamp() + "ERROR - " + error + " - " + exceptionAsStrting);
	}

	public void log(String mssg) {
		if (mssg != null) {
				System.out.println(getLogStamp() + mssg);
		} else {
			System.out.println(getLogStamp() + "ERROR - unable to log null value");
		}
	}
}
