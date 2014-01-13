package ajeetmurty.reference.java.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rest {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
	// NOAA NDFD rest api - http://graphical.weather.gov/xml/rest.php
	private final String url = "http://graphical.weather.gov/xml/sample_products/browser_interface/ndfdXMLclient.php?lat=%1$s&lon=%2$s&product=time-series&begin=%3$s&end=%4$s&maxt=maxt&mint=mint";
	private final String longitude = "38.89";
	private final String latitude = "-77.21";
	private final String beginDate = "2014-01-13T06:00:00";
	private final String endDate = "2014-01-13T07:00:00";

	public static void main(String[] args) {
		new Rest();
	}

	public Rest() {
		logp.info("start");
		sendRestRequest();
		logp.info("stop");
	}

	private void sendRestRequest() {
		try {
			logp.info("input url: " + url);
			URL someUrl = new URL(String.format(url, longitude, latitude, beginDate, endDate));
			HttpURLConnection connection = (HttpURLConnection) someUrl.openConnection();
			// to circumvent bad ssl certs
			TrustModifier.relaxHostChecking(connection);
			// to set auth credentials
			byte[] encoded = Base64.encodeBase64(("username" + ":" + "password").getBytes());
			connection.setRequestProperty("Authorization", "Basic " + new String(encoded));
			// fire rest request
			connection.connect();

			logp.info("rest request url: " + connection.getURL());
			if (connection.getResponseCode() == 200) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				logp.info("rest response xml: " + sb.toString());
			} else {
				logp.error("failed response code: " + connection.getResponseCode());
			}
			connection.disconnect();
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
	}
}
