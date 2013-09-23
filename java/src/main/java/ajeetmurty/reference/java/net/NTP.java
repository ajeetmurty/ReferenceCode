package ajeetmurty.reference.java.net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NTP {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new NTP();
	}

	public NTP() {
		doNTP();
	}

	private void doNTP() {
		logp.info("start");
		try {
			logp.info("doing ntp");
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}
}