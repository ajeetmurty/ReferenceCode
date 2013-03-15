package ajeetmurty.reference.java.patterns.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Singleton {

	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		new Singleton();
	}

	public Singleton() {
		doSingleton();
	}

	private void doSingleton() {
		logp.info("start");
		try {
			logp.info("init singleton");
			SingleClass sinClass = SingleClass.INSTANCE;
			sinClass.setAge(28);
			logp.info("age: " + sinClass.getAge());
			logp.info("birth year: " + sinClass.getBirthYear());
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}
}