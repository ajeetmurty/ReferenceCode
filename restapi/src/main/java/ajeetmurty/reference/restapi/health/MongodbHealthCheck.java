package ajeetmurty.reference.restapi.health;

import com.codahale.metrics.health.HealthCheck;

public class MongodbHealthCheck extends HealthCheck {
	private final String mongodbHost, mongodbPort, mongodbDb;

	public MongodbHealthCheck(String mongodbHost, String mongodbPort, String mongodbDb) {
		this.mongodbHost = mongodbHost;
		this.mongodbPort = mongodbPort;
		this.mongodbDb = mongodbDb;
	}

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}
}