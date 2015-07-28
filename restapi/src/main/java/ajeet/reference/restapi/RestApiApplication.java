package ajeet.reference.restapi;

import ajeet.reference.restapi.health.TemplateHealthCheck;
import ajeet.reference.restapi.resources.RestApiResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestApiApplication extends Application<RestApiConfiguration> {
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

		final RestApiResource resource = new RestApiResource(configuration.getTemplate(),
				configuration.getDefaultName());

		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);

		environment.jersey().register(resource);

	}

}