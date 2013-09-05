package ajeetmurty.reference.java.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Random {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new Random();
	}

	public Random() {
		doSomething();
	}

	private void doSomething() {
		logp.info("start");
		try {
			logp.info("generating random");
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}
}
