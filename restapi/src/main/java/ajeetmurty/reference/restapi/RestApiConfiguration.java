package ajeetmurty.reference.restapi;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class RestApiConfiguration extends Configuration {
	@NotEmpty
	private String template;

	@NotEmpty
	private String defaultName;

	@NotEmpty
	private String defaultPhone = "1-000-000-0000";

	@JsonProperty
	public String getTemplate() {
		return template;
	}

	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonProperty
	public String getDefaultName() {
		return defaultName;
	}

	@JsonProperty
	public void setDefaultName(String name) {
		this.defaultName = name;
	}

	@JsonProperty
	public String getDefaultPhone() {
		return defaultPhone;
	}

	@JsonProperty
	public void setDefaultPhone(String phone) {
		this.defaultPhone = phone;
	}

}
