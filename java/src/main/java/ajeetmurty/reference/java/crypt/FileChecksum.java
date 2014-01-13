package ajeetmurty.reference.java.crypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileChecksum {
	private final Logger logp = LoggerFactory.getLogger(this.getClass().getName());
	private final String inputFilePath = "src/main/resources/logback.xml";

	public static void main(String[] args) {
		new FileChecksum();
	}

	public FileChecksum() {
		logp.info("start");
		doFileChecksum();
		logp.info("stop");
	}

	private void doFileChecksum() {
		try {
			logp.info("input file path: " + inputFilePath);
			File file = new File(inputFilePath);
			if (file.isFile() && file.canRead()) {
				logp.info("input file SHA-256 checksum: " + generateSHA256Checksum(file.getAbsolutePath()));
			} else {
				logp.error("input file not found / not readable");
			}
		} catch (Exception e) {
			logp.error(e.getMessage(), e);
		}
	}

	public String generateSHA256Checksum(String filename) throws Exception {
		InputStream fileStream = new FileInputStream(filename);
		byte[] buffer = new byte[1024];
		// MessageDigest algorithms: http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#MessageDigest
		MessageDigest complete = MessageDigest.getInstance("SHA-256");
		int numRead;
		do {
			numRead = fileStream.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);
		fileStream.close();
		byte[] b = complete.digest();
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
		}
		return result.toString();
	}
}