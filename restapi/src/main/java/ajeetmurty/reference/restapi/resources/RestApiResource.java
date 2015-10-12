package ajeetmurty.reference.restapi.resources;

import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import ajeetmurty.reference.restapi.core.Saying;

@Path("/rest-api")
@Produces(MediaType.APPLICATION_JSON)
public class RestApiResource {
	private final String template;
	private final String defaultName;
	private final String defaultPhone;
	private final AtomicLong counter;

	public RestApiResource(String template, String defaultName, String defaultPhone) {
		this.template = template;
		this.defaultName = defaultName;
		this.defaultPhone = defaultPhone;
		this.counter = new AtomicLong();
	}

	@GET
	@Timed
	public Saying sayHello(@Context HttpServletRequest httpRequest, @QueryParam("name") Optional<String> name, @QueryParam("phone") Optional<String> phone) {
		dumpHttpGetRequest(httpRequest);
		final String finalName = String.format(template, name.or(defaultName));
		final String finalPhone = phone.or(defaultPhone);
		return new Saying(counter.incrementAndGet(), finalName, finalPhone);
	}

	private void dumpHttpGetRequest(HttpServletRequest httpRequest) {
		if (httpRequest != null) {
			StringBuilder headerVals = new StringBuilder();
			StringBuilder contentVals = new StringBuilder();

			Enumeration<String> headerNames = httpRequest.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				headerVals.append("|" + httpRequest.getHeader((String) headerNames.nextElement()));
			}

			Enumeration<String> params = httpRequest.getParameterNames();
			while (params.hasMoreElements()) {
				String paramName = (String) params.nextElement();
				contentVals.append("|" + paramName + ":" + httpRequest.getParameter(paramName));
			}

			System.out.println("HTTP GET REQ -- headers - " + headerVals.toString() + " || content - " + contentVals.toString());
		} else {
			System.out.println("null get request object received!");
		}
	}
}
