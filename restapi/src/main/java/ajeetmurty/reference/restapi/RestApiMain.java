package ajeetmurty.reference.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ajeetmurty.reference.restapi.health.MongodbHealthCheck;
import ajeetmurty.reference.restapi.health.NameTemplateHealthCheck;
import ajeetmurty.reference.restapi.resources.RestApiResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestApiMain extends Application<RestApiConfiguration> {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) throws Exception {
		new RestApiMain().run(args);
	}

	@Override
	public String getName() {
		return "restapi";
	}

	@Override
	public void initialize(Bootstrap<RestApiConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(RestApiConfiguration configuration, Environment environment) {
		final RestApiResource resource = new RestApiResource(configuration.getTemplate(), configuration.getDefaultName(), configuration.getDefaultPhone());
		final NameTemplateHealthCheck nameTemplateHealthCheck = new NameTemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("nametemplate", nameTemplateHealthCheck);
		
		final MongodbHealthCheck mongodbHealthCheck = new MongodbHealthCheck(configuration.getMongodbhost(), configuration.getMongodbport(), configuration.getMongodbdb());
		environment.healthChecks().register("mongodb", mongodbHealthCheck);
		
		environment.jersey().register(resource);
		logp.info("to access application hit this url: http://localhost:8800/rest-api");
	}
}