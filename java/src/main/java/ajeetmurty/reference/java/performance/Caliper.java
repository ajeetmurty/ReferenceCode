package ajeetmurty.reference.java.performance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Caliper {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new Caliper();
	}

	public Caliper() {
		doSomething();
	}

	//microbenchmark no bueno! - http://code.google.com/p/caliper/wiki/JavaMicrobenchmarks
	private void doSomething() {
		logp.info("start");
		try {
			logp.info("doing info");
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}
}