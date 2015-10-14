package ajeetmurty.reference.restapi.core;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	private long pid;
	private String lastName;
	private String firstName;
	@Length(max = 12)
	private String phone;
	
	public Person() {
		// Jackson deserialization
	}

	public Person(long pid, String lastName, String firstName, String phone) {
		this.pid = pid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
	}

	@JsonProperty
	public long getPid() {
		return pid;
	}

	@JsonProperty
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty
	public String getLastName() {
		return lastName;
	}

	@JsonProperty
	public String getPhone() {
		return phone;
	}
}
