package ajeetmurty.reference.java.crypt;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringBase64 {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
	private String inputString = "IN9UT_#$tr1ng-.2o14";

	public static void main(String[] args) {
		new StringBase64();
	}

	private StringBase64() {
		logp.info("start");
		encodeString();
		logp.info("stop");
	}

	private void encodeString() {
		try {
			logp.info("input String: " + inputString);
			// encoding
			byte[] encodedBytes = Base64.encodeBase64(inputString.getBytes());
			logp.info("base64 encoded string: " + new String(encodedBytes));
			// decoding
			byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
			logp.info("base64 decoded string: " + new String(decodedBytes));
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
	}
}
