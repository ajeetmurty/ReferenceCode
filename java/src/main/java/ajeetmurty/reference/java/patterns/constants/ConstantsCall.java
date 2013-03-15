package ajeetmurty.reference.java.patterns.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstantsCall {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new ConstantsCall();
	}

	public ConstantsCall() {
		doConstants();
	}

	private void doConstants() {
		logp.info("start");
		try {
			logp.info("init constants");
			logp.info("color: " + Constants.COLOR_A);
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}
}