package ajeetmurty.reference.restapi;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class RestApiConfiguration extends Configuration {
	// variable and getter-setter names should match thsoe in yaml config file.
	@NotEmpty
	private String template;

	@NotEmpty
	private String defaultName;

	@NotEmpty
	private String defaultPhone = "1-000-000-0000";

	@NotEmpty
	private String mongodbhost;

	@NotEmpty
	@Max(65535)
	private String mongodbport;

	@NotEmpty
	private String mongodbdb;

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

	@JsonProperty
	public String getMongodbhost() {
		return mongodbhost;
	}

	@JsonProperty
	public void setMongodbhost(String mongodbhost) {
		this.mongodbhost = mongodbhost;
	}

	@JsonProperty
	public String getMongodbport() {
		return mongodbport;
	}

	@JsonProperty
	public void setMongodbport(String mongodbport) {
		this.mongodbport = mongodbport;
	}

	@JsonProperty
	public String getMongodbdb() {
		return mongodbdb;
	}

	@JsonProperty
	public void setMongodbdb(String mongodbdb) {
		this.mongodbdb = mongodbdb;
	}
}
