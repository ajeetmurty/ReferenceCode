package com.adora.learn.java;

import java.util.Hashtable;

import com.adora.learn.java.util.Logger;

public class LearnMain {
	private Logger logp = Logger.getInstance();
	
	public static void main(String[] args) {
		new LearnMain();
	}

	public LearnMain(){
		doit();
	}
	
	private void doSomething(){
		logp.log("start");
		logp.log("stop");
	}
	
	private void doit(){
		Hashtable<Integer, String> tbl = new Hashtable<Integer, String>();
		
		tbl.put(new Integer(1), "ajeet");
		tbl.put(new Integer(1), "ajeet");
		tbl.put(new Integer(2), "phil");
		
		if(tbl.contains("ajeet")){
			System.out.print("found");
		}else{
			System.out.print("fail");
		}
	}
}
