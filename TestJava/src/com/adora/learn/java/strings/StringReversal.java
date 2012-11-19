package com.adora.learn.java.strings;

import com.adora.learn.java.util.Logger;

public class StringReversal {
	private Logger logp = Logger.getInstance();
	private String inputStr = "ABCDEFG";

	public static void main(String[] args) {
		new StringReversal();
	}

	public StringReversal() {
		doReversal(inputStr);
	}

	private void doReversal(String str) {
		logp.log("start");
		logp.log("input -- " + str);
		logp.log("output -- " + recReversal(str));
		logp.log("stop");
	}

	private String recReversal(String str) {
		if (str.length() <= 1) {
			return str;
		}
		return recReversal(str.substring(1, str.length())) + str.charAt(0);
	}
}
