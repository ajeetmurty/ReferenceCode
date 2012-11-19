package com.adora.learn.java.math;

import com.adora.learn.java.util.Logger;

public class CalculateFib {
	private Logger logp = Logger.getInstance();
	private int inputNum = 6;

	public static void main(String[] args) {
		new CalculateFib();
	}

	public CalculateFib(){
		doFib(inputNum);
	}
	
	private void doFib(int start) {
		logp.log("start");
		logp.log("input -- "+start);
		logp.log("output -- "+doRecFib(inputNum));
		logp.log("stop");
	}
	
	private long doRecFib(int n){
		if(n <= 1){
			return n;
		}else{
			return doRecFib(n-1) + doRecFib(n-2);
		}
	}
	
}
