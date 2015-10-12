package ajeetmurty.reference.restapi.core;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Saying {
	private long id;

	@Length(max = 3)
	private String content;
	private String phone;

	public Saying() {
		// Jackson deserialization
	}

	public Saying(long id, String content, String phone) {
		this.id = id;
		this.content = content;
		this.phone = phone;
	}

	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public String getContent() {
		return content;
	}

	@JsonProperty
	public String getPhone() {
		return phone;
	}
}
