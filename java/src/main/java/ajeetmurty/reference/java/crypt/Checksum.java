package ajeetmurty.reference.java.crypt;

import java.io.File;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Checksum {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
	private final String filePath = "src/main/resources/logback.xml";

	public static void main(String[] args) {
		new Checksum();
	}

	public Checksum() {
		doChecksum();
	}

	private void doChecksum() {
		logp.info("start");
		try {
			logp.info("init checksum");
			logp.info("file: " + filePath);
			File file = new File(filePath);
			if (file.isFile() && file.canRead()) {
				logp.info("file found");
			}
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
		logp.info("stop");
	}

//	public static byte[] getDigest(InputStream in, String algorithm) throws Throwable {
//		MessageDigest md = MessageDigest.getInstance(algorithm);
//		try {
//			DigestInputStream dis = new DigestInputStream(in, md);
//			byte[] buffer = new byte[BUFFER_SIZE];
//			while (dis.read(buffer) != -1) {
//				//
//			}
//			dis.close();
//		} finally {
//			in.close();
//		}
//		return md.digest();
//	}
//
//	public static String getDigestString(InputStream in, String algorithm) throws Throwable {
//		byte[] digest = getDigest(in, algorithm);
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < digest.length; i++) {
//			sb.append(String.format("%x", digest[i]));
//		}
//		return sb.toString();
//	}

}