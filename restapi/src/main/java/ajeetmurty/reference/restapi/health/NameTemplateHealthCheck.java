package ajeetmurty.reference.restapi.health;

import com.codahale.metrics.health.HealthCheck;

public class NameTemplateHealthCheck extends HealthCheck {
	private final String template;

	public NameTemplateHealthCheck(String template) {
		this.template = template;
	}

	@Override
	protected Result check() throws Exception {
		final String saying = String.format(template, "TEST");
		if (!saying.contains("TEST")) {
			return Result.unhealthy("restapi name template failed");
		}
		return Result.healthy();
	}
}