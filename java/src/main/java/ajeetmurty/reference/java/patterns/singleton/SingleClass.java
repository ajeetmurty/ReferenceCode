package ajeetmurty.reference.java.patterns.singleton;

import java.util.Calendar;

public enum SingleClass {
	INSTANCE;

	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBirthYear() {
		return ((Calendar.getInstance().get(Calendar.YEAR)) - age);
	}
}