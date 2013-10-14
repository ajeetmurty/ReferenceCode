package ajeetmurty.reference.java;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Template {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new Template();
	}

	public Template() {
		doSomething();
	}

	private void doSomething() {
		logp.info("start");
		try {
			logp.info("doing info");
			logp.debug("doing debug");
			logp.error("doing error");
			throw new IOException("throw io exception");
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}
}