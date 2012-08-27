package com.adora.test.java.ds;

import com.adora.test.java.util.Logger;

public class HashMain {
	private Logger logp = Logger.getInstance();
	
	public static void main(String[] args) {
		new HashMain();
	}

	public HashMain(){
		buildHash();
	}
	
	private void buildHash(){
		logp.log("start");
		logp.log("building hash...");
		
		String str = "haha";
		String str1 = "haha";
		logp.log("str - "+str.hashCode());
		logp.log("str1 - "+str1.hashCode());
		
		logp.log("stop");
	}
}
