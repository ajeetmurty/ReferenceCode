package ajeetmurty.reference.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ajeetmurty.reference.restapi.health.TemplateHealthCheck;
import ajeetmurty.reference.restapi.resources.RestApiResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestApiApplication extends Application<RestApiConfiguration> {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) throws Exception {
		new RestApiApplication().run(args);
	}

	@Override
	public String getName() {
		return "rest-api";
	}

	@Override
	public void initialize(Bootstrap<RestApiConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(RestApiConfiguration configuration, Environment environment) {
		final RestApiResource resource = new RestApiResource(configuration.getTemplate(), configuration.getDefaultName());
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
		logp.info("to access application hit this url: http://localhost:8800/rest-api");
	}
}