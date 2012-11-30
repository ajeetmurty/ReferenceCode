package com.adora.learn.java;

import java.util.Hashtable;

import com.adora.learn.java.util.Logger;

public class LearnMain {
	private Logger logp = Logger.getInstance();
	
	public static void main(String[] args) {
		new LearnMain();
	}

	public LearnMain(){
		doSomething();
	}
	
	private void doSomething(){
		logp.log("start");
		logp.log("stop");
	}
}