package com.adora.test.java;

import java.util.Date;

import com.adora.test.java.util.Logger;

public class TestMain {
	private Logger logp = Logger.getInstance();
	
	public static void main(String[] args) {
		new TestMain();
	}

	public TestMain(){
		doSomething();
	}
	
	private void doSomething(){
		logp.log("start");
		Date nowDt = new Date();
		logp.log("Now -- "+nowDt.toString());
		logp.log("stop");
	}
}
