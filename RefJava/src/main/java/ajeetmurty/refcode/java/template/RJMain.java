package ajeetmurty.refcode.java.template;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ajeetmurty.refcode.java.template.io.RJFile;

public class RJMain {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new RJMain();
	}

	public RJMain() {
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